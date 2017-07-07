package Views;

import Models.TreeComponent;
import Models.UserGroup;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

public class TreeView implements View {

    private JPanel treePanel;
    private JTree userTree;
    private JScrollPane scrollPaneTree;
    private DefaultMutableTreeNode mainRootGroup; //Main root in the JTree

    public TreeView() {
        mainRootGroup = new DefaultMutableTreeNode(new UserGroup("Root"));
        DefaultMutableTreeNode p = new DefaultMutableTreeNode(new UserGroup("x-class"));
        mainRootGroup.add(new DefaultMutableTreeNode(new UserGroup("Same fisher")));
        mainRootGroup.add(new DefaultMutableTreeNode(new UserGroup("mike fisher")));
        mainRootGroup.add(new DefaultMutableTreeNode(new UserGroup("mike fisher")));
        mainRootGroup.add(new DefaultMutableTreeNode(new UserGroup("mike fisher")));
        mainRootGroup.add(new DefaultMutableTreeNode(new UserGroup("mike fisher")));
        mainRootGroup.add(new DefaultMutableTreeNode(new UserGroup("mike fisher")));
        mainRootGroup.add(new DefaultMutableTreeNode(new UserGroup("mike fisher")));
        mainRootGroup.add(new DefaultMutableTreeNode(new UserGroup("mike fisher")));
        mainRootGroup.add(new DefaultMutableTreeNode(new UserGroup("mike fisher")));
        mainRootGroup.add(new DefaultMutableTreeNode(new UserGroup("mike fisher")));
        mainRootGroup.add(new DefaultMutableTreeNode(new UserGroup("mike fisher")));
        mainRootGroup.add(new DefaultMutableTreeNode(new UserGroup("mike fisher")));
        mainRootGroup.add(new DefaultMutableTreeNode(new UserGroup("mike fisher")));
        mainRootGroup.add(new DefaultMutableTreeNode(new UserGroup("mike fisher")));
        mainRootGroup.add(new DefaultMutableTreeNode(new UserGroup("mike fisher")));
        mainRootGroup.add(new DefaultMutableTreeNode(new UserGroup("mike fisher")));
        mainRootGroup.add(new DefaultMutableTreeNode(new UserGroup("mike fisher")));
        mainRootGroup.add(new DefaultMutableTreeNode(new UserGroup("mike fisher")));
        mainRootGroup.add(new DefaultMutableTreeNode(new UserGroup("mike fisher")));
        mainRootGroup.add(new DefaultMutableTreeNode(new UserGroup("mike fisher")));
        mainRootGroup.add(new DefaultMutableTreeNode(new UserGroup("mike fisher")));
        mainRootGroup.add(new DefaultMutableTreeNode(new UserGroup("mike fisher")));

        p.add(new DefaultMutableTreeNode(new UserGroup("xxxx")));
        p.add(new DefaultMutableTreeNode(new UserGroup("xxxxx")));
        p.add(new DefaultMutableTreeNode(new UserGroup("x")));

        userTree = new JTree(mainRootGroup);
        scrollPaneTree = new JScrollPane(userTree);
        initComponent();


    }

    private void initComponent() {
        treePanel = new JPanel();
        treePanel.setLayout(new BorderLayout());
        treePanel.setBorder(new EmptyBorder(13,5,15,5));
        scrollPaneTree.setBorder(new EmptyBorder(5,5,5,5));
        treePanel.add(scrollPaneTree);

    }

    //Returns the root selected from JTree, used for addUserListener
    public TreeComponent getRootSelected() {
        return (TreeComponent) userTree.getLastSelectedPathComponent();
    }

    //Returns the root selected from JTree, used for addUserListener
    public String getRootSelectedTest() {
        return ((TreeComponent)userTree.getLastSelectedPathComponent()).toString();
    }

    @Override
    public JPanel getView() {
        return treePanel;
    }

//    public void addNewTreeUser(TreeComponent selectedTreeComponent, String newUserName) {
//        if (selectedTreeComponent instanceof User){
//            selectedTreeComponent.add(new DefaultMutableTreeNode(new User(newUserName)));
//        }
//        else if (selectedTreeComponent instanceof UserGroup){
//
//        }
//        selectedTreeComponent.add(new DefaultMutableTreeNode(new User(newUserName)));
//
//    }





}
