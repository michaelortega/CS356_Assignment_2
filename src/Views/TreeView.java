package Views;

import Models.TreeComponent;
import Models.User;
import Models.UserGroup;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.awt.*;

public class TreeView implements View {

    private JPanel treePanel;
    private JTree userTree;
    private JScrollPane scrollPaneTree;
    private DefaultMutableTreeNode mainRootGroup; //Main root in the JTree

    public TreeView() {


        mainRootGroup = new DefaultMutableTreeNode(new UserGroup("Root"));
        userTree = new JTree(mainRootGroup);
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

    public void addNewUser(String newUserName) {
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) userTree.getLastSelectedPathComponent();
        if (selectedNode == null) {
            throw new NullPointerException("No User was selected");
        } else if (selectedNode.getUserObject() instanceof User) {
            throw new IllegalArgumentException("A user was selected, please select a group");
        } else {
            selectedNode.add(new DefaultMutableTreeNode(new User(newUserName)));
        }
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


    @Override
    public JPanel getView() {
        return treePanel;
    }
}

