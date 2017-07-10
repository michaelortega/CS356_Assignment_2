package Views;

import Models.TreeComponent;
import Models.User;
import Models.UserGroup;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import java.awt.*;

public class TreeView  {

    private JPanel treePanel;
    private JTree userTree;
    private JScrollPane scrollPaneTree;
    private DefaultMutableTreeNode mainRootGroup; //Main root in the JTree
    private DefaultTreeModel treeModel;

    public TreeView() {


        mainRootGroup = new DefaultMutableTreeNode(new UserGroup("Root"));
         treeModel = new DefaultTreeModel(mainRootGroup);
        userTree = new JTree(mainRootGroup);
        userTree.setSize(100,150);
        scrollPaneTree = new JScrollPane(userTree);
        initComponent();





    }

    private void initComponent() {
        treePanel = new JPanel();
        treePanel.setLayout(new BorderLayout());
        treePanel.setBorder(new EmptyBorder(13, 5, 15, 5));
        scrollPaneTree.setBorder(new EmptyBorder(5, 5, 5, 5));
        treePanel.add(scrollPaneTree);

    }

    public void add(DefaultMutableTreeNode selection, User type) {
        DefaultMutableTreeNode selectedNode = selection;
        selectedNode.add(new DefaultMutableTreeNode(type));
        treeModel.reload();



    }

    public void addNewGroup(String newUserName) {
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) userTree.getLastSelectedPathComponent();
        if (selectedNode == null) {
            throw new NullPointerException("No Group was selected");
        } else if (selectedNode.getUserObject() instanceof User) {
            throw new IllegalArgumentException("A user was selected, please select a group");
        } else {
            selectedNode.add(new DefaultMutableTreeNode(new User(newUserName)));
        }
    }

    public JTree getUserTree(){
        return userTree;
    }

    public TreeModel getModel() {
        return userTree.getModel();
    }

    public JPanel getTreeView(){
        return treePanel;
    }
}

