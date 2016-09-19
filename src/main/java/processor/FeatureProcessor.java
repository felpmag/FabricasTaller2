package processor;

import java.util.HashMap;
import java.util.Map;

import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtAnnotation;
import annotation.Feature;

public class FeatureProcessor extends AbstractProcessor<CtAnnotation<Feature>> {

	private Map<String, FeatureNode> features = new HashMap<>();
	private FeatureNode root;

	@Override
	public void init() {
		System.out.println("Empieza procesamiento");
		super.init();
	}

	@Override
	public void process(CtAnnotation<Feature> annotation) {
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
		System.out.println("Termina procesamiento");
		// generateFeatureIDEModel();
		System.out.println("Raiz:" + root);
		System.out.println("Contenido:" + features);
		generateFeatureIDEModel();
		super.processingDone();
	}

	private void generateFeatureIDEModel() {
		// FeatureModel model = buildModel();
		// Struct value = new Struct();
		// model.setStruct(value);
		//
		// And rootFeat = new And();
		// value.setAnd(rootFeat);
		//
		// rootFeat.setName(root.getFeature().nombre());
	}

}
