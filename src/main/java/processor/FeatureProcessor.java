package processor;

import java.util.HashMap;
import java.util.Map;

import annotation.Feature;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtAnnotation;
import util.DisplayTree;
import util.FeatureIdeModelBuilder;

public class FeatureProcessor extends AbstractProcessor<CtAnnotation<Feature>> {

    private Map<String, FeatureNode> features = new HashMap<>();
    private FeatureNode root;

    @Override
    public void init() {
        System.out.println("Empieza procesamiento");
        super.init();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void process(CtAnnotation<Feature> annotation) {
        if (!(annotation.getActualAnnotation() instanceof Feature)) {
            System.out.println("excluida " + annotation.getSignature());
            return;
        }

        Feature annFeature = annotation.getActualAnnotation();
        String nombre = annFeature.nombre();

        FeatureNode feature = features.getOrDefault(nombre, new FeatureNode(annFeature));
        features.put(nombre, feature);

        checkEmptyNode(feature, annFeature);

        if (annFeature.padre().equals("")) {
            handleRoot(feature);
        } else {
            addToParent(feature);
        }

    }

    private void addToParent(FeatureNode feature) {
        String nombrePadre = feature.getFeature().padre();
        FeatureNode padre = features.getOrDefault(nombrePadre, new FeatureNode(null));
        features.put(nombrePadre, padre);
        padre.add(feature);

    }

    private void checkEmptyNode(FeatureNode treeNode, Feature nodeContent) {
        if (treeNode.getFeature() == null) {
            treeNode.setUserObject(nodeContent);
        }
    }

    private void handleRoot(FeatureNode treeNode) {
        if (root == null) {
            // No parent, nodo raiz
            root = treeNode;
        } else {
            throw new IllegalStateException("El nodo padre ya se encuentra definido: " + root.getFeature().nombre());
        }
    }

    @Override
    public void processingDone() {
        super.processingDone();
        new DisplayTree(root);
        System.out.println("Generando modelo");
        new FeatureIdeModelBuilder(root).generateFeatureIDEModel("./target/model-generated.xml");
        System.out.println("Termina procesamiento");
    }

}
