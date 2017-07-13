package Views;

import Models.TreeComponent;
import Models.User;
import Models.UserGroup;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import java.awt.*;

public class TreeView {

    private JPanel treePanel;
    private JTree userTree;
    private JScrollPane scrollPaneTree;
    private DefaultMutableTreeNode mainRootGroup; //Main root in the JTree
    private DefaultTreeModel treeModel;


    public TreeView() {

        treeModel = new DefaultTreeModel(mainRootGroup);
        mainRootGroup = new DefaultMutableTreeNode(new UserGroup("Root"));
        mainRootGroup.add(new DefaultMutableTreeNode(new User("d")));
        userTree = new JTree(mainRootGroup);
        treeModel.setRoot(mainRootGroup);
        scrollPaneTree = new JScrollPane(userTree);
        scrollPaneTree.setPreferredSize(new Dimension(300, 500));

        initComponent();


    }

    private void initComponent() {
        treePanel = new JPanel();
        treePanel.setLayout(new BorderLayout());

        treePanel.setBorder(new EmptyBorder(13, 5, 15, 5));
        scrollPaneTree.setBorder(new EmptyBorder(5, 5, 5, 5));
        treePanel.add(scrollPaneTree);


    }


    public void add(DefaultMutableTreeNode selection, TreeComponent treeComponent) {
        DefaultTreeModel tm = (DefaultTreeModel) userTree.getModel();
        if (treeComponent instanceof User) {
            tm.insertNodeInto(new DefaultMutableTreeNode(treeComponent), selection, selection.getChildCount());
            userTree.expandRow(selection.getLevel());
        } else {
            DefaultMutableTreeNode newRoot = new DefaultMutableTreeNode(treeComponent);
            selection.add(newRoot);
            tm.reload();
        }

    }


    public JTree getUserTree() {
        return userTree;
    }

    public TreeModel getModel() {
        return userTree.getModel();
    }


    public JPanel getTreeView() {
        return treePanel;
    }
}

