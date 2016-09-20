package processor;

/**
 * Clase Nodo.
 * @author felipemartinez
 */

import javax.swing.tree.DefaultMutableTreeNode;

import annotation.Feature;

@SuppressWarnings("serial")
public class FeatureNode extends DefaultMutableTreeNode {

	public FeatureNode(Feature data) {
		super(data);
	}

	public void setUserObject(Feature userObject) {
		super.setUserObject(userObject);
	}

	@Override
	public Feature getUserObject() {
		return (Feature) super.getUserObject();
	}

	public Feature getFeature() {
		return (Feature) super.getUserObject();
	}

}
