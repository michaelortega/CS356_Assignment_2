package Views;

import Models.TreeComponent;
import Models.User;
import Models.UserGroup;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;

public class TreeView {

    private JPanel treePanel;
    private JTree userTree;
    private JScrollPane scrollPaneTree;
    private DefaultMutableTreeNode mainRootGroup; //Main root in the JTree
    private DefaultTreeModel treeModel;


    public TreeView() {

        treeModel = new DefaultTreeModel(mainRootGroup);
        treeModel.setAsksAllowsChildren(true);
        mainRootGroup = new DefaultMutableTreeNode(new UserGroup("Root"));
        userTree = new JTree(mainRootGroup);
        userTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        CustomIconRenderer renderer = new CustomIconRenderer();
        userTree.setCellRenderer(renderer);








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

