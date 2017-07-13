package Views;

import Models.TreeComponent;
import Models.User;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

public class CustomIconRenderer extends DefaultTreeCellRenderer {
    private ImageIcon groupIcon;
    private ImageIcon singleUserIcon;

    public CustomIconRenderer() {
        groupIcon = new ImageIcon("src/group.png");
        singleUserIcon = new ImageIcon("src/user.png");
    }

    public Component getTreeCellRendererComponent(JTree tree,
                                                  Object value, boolean sel, boolean expanded, boolean leaf,
                                                  int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel,
                expanded, leaf, row, hasFocus);


        DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) value;
        TreeComponent nodeType = (TreeComponent) treeNode.getUserObject();
        if (nodeType instanceof User) {
            setIcon(singleUserIcon);
        } else {
            setIcon(groupIcon);
        }
        return this;
    }
}

