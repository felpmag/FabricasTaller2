package util;

import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

@SuppressWarnings("serial")
public class DisplayTree extends JFrame {
    private JTree tree;
    public DisplayTree(DefaultMutableTreeNode treeContent) {
        tree = new JTree(treeContent);
        add(tree);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Annotation Tree");
        this.pack();
        this.setVisible(true);
    }
}
