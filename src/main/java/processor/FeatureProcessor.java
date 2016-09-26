package processor;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtAnnotation;
import annotation.Feature;
import featureide.And;
import featureide.Constraints;
import featureide.FeatureModel;
import featureide.FeatureOrder;
import featureide.Imp;
import featureide.Parent;
import featureide.Rule;
import featureide.Struct;

public class FeatureProcessor extends AbstractProcessor<CtAnnotation<Feature>> {

	private Map<String, FeatureNode> features = new HashMap<>();
	private FeatureNode root;
	private Constraints constraint = new Constraints();

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
		System.out.println("Generando modelo");
		generateFeatureIDEModel();
		System.out.println("Termina procesamiento");
	}

	private void generateFeatureIDEModel() {
		FeatureModel model = buildModel();
		model.getStruct().setAnd(new And(root.getFeature().nombre(), null, true));

		processChild(root, model.getStruct().getAnd());
		model.setConstraints(constraint);
		
		JaxbWriterReader.jaxbWriterNoSchema(model, "./src/main/resources/model.xml");
	}

	@SuppressWarnings("unchecked")
	private void processChild(FeatureNode node, Parent modelNode) {
		Enumeration<FeatureNode> children = node.children();
		while (children.hasMoreElements()) {
			FeatureNode child = children.nextElement();
			///---
			getConstraint(child);
			
			if (isModelFeature(child)) {
				addFeature(modelNode, child);
			} else {
				addNode(modelNode, child);
			}
		}
	}
	
	/**
	 * Get Constraint.
	 * @param child
	 */
	private void getConstraint(FeatureNode child){
		if(!child.getFeature().requiero().isEmpty()){
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

	private void addNode(Parent parent, FeatureNode node) {
		// TODO no siempre es un And, puede ser Alt o Or
		And modelNode = new And(node.getFeature().nombre(), null, node.getFeature().requerido());
		parent.getAndOrAltOrOr().add(modelNode);
		processChild(node, modelNode);
	}

	private void addFeature(Parent modelNode, FeatureNode node) {
		modelNode.getAndOrAltOrOr().add(
				new featureide.Feature(node.getFeature().nombre(), null, node.getFeature().requerido()));
	}

	private boolean isModelFeature(FeatureNode node) {
		return node.getDepth() == 0 ? true : false;
	}

	private FeatureModel buildModel() {
		return new FeatureModel(new Struct(), new Constraints(), "", new FeatureOrder(false), null);
	}

}
