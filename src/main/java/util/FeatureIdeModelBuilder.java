package util;

import java.util.Enumeration;

import annotation.Feature;
import annotation.Feature.RestriccionHijos;
import featureide.Alt;
import featureide.And;
import featureide.Constraints;
import featureide.FeatureModel;
import featureide.FeatureOrder;
import featureide.Imp;
import featureide.Or;
import featureide.Parent;
import featureide.Rule;
import featureide.Struct;
import processor.FeatureNode;
import processor.JaxbWriterReader;

public class FeatureIdeModelBuilder {

    private FeatureNode root;
    private Constraints constraint = new Constraints();

    public FeatureIdeModelBuilder(FeatureNode root) {
        this.root = root;
    }

    public void generateFeatureIDEModel(String path) {
        FeatureModel model = buildModel();

        Parent concreteRoot = buildConcreteParent(root);
        if (concreteRoot instanceof And)
            model.getStruct().setAnd((And) concreteRoot);
        else if (concreteRoot instanceof Or)
            model.getStruct().setOr((Or) concreteRoot);
        else if (concreteRoot instanceof Alt)
            model.getStruct().setAlt((Alt) concreteRoot);
        else
            throw new IllegalStateException("Tipo inesperado para el nodo raiz");
        
        processChild(root, concreteRoot);
        model.setConstraints(constraint);

        JaxbWriterReader.jaxbWriterNoSchema(model, path);
    }

    private FeatureModel buildModel() {
        return new FeatureModel(new Struct(), new Constraints(), "", new FeatureOrder(false), null);
    }

    @SuppressWarnings("unchecked")
    private void processChild(FeatureNode node, Parent modelNode) {
        Enumeration<FeatureNode> children = node.children();
        while (children.hasMoreElements()) {
            FeatureNode child = children.nextElement();
            /// ---
            getConstraint(child);

            if (isModelFeature(child)) {
                addFeature(modelNode, child);
            } else {
                addNode(modelNode, child);
            }
        }
    }

    private void addNode(Parent parent, FeatureNode node) {
        Parent wrapper = buildConcreteParent(node);
        parent.getAndOrAltOrOr().add(wrapper);
        processChild(node, wrapper);
    }

    private void getConstraint(FeatureNode child) {
        if (!child.getFeature().requiero().isEmpty()) {
            Rule rule = new Rule();
            Imp imple = new Imp();
            /// B implies A
            imple.getVarOrNot().add(child.getFeature().nombre());
            rule.getImp().add(imple);

            imple.getVarOrNot().add(child.getFeature().requiero());
            rule.getImp().add(imple);

            constraint.getRule().add(rule);
        }
    }

    private void addFeature(Parent modelNode, FeatureNode node) {
        modelNode.getAndOrAltOrOr()
                .add(new featureide.Feature(node.getFeature().nombre(), null, node.getFeature().requerido()));
    }

    private boolean isModelFeature(FeatureNode node) {
        return node.getDepth() == 0 ? true : false;
    }

    private Parent buildConcreteParent(FeatureNode node) {
        Parent wrapper = null;
        String nombre = node.getFeature().nombre();
        boolean requerido = node.getFeature().requerido();
        if (isModelFeature(node)) {
            wrapper = new And(nombre, null, requerido);
        } else {
            Feature childFeat = ((FeatureNode) node.getChildAt(0)).getFeature();
            if (childFeat.relacion().equals(RestriccionHijos.OR)) {
                wrapper = new Or(nombre, null, requerido);
            } else if (childFeat.relacion().equals(RestriccionHijos.ALT)) {
                wrapper = new Alt(nombre, null, requerido);
            } else {
                wrapper = new And(nombre, null, requerido);
            }
        }
        return wrapper;
    }
}
