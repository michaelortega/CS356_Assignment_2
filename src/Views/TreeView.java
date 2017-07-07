package Views;

import Models.TreeComponent;
import Models.UserGroup;


import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

public class TreeView implements View {

    private JPanel treePanel;
    private JTree userTree;
    private DefaultMutableTreeNode mainRootGroup; //Main root in the JTree

    public TreeView() {
        mainRootGroup = new DefaultMutableTreeNode(new UserGroup("Root"));
        userTree = new JTree(mainRootGroup);
        initComponent();


    }

    private void initComponent() {
        treePanel = new JPanel();
        treePanel.setSize(80,300);
        treePanel.add(userTree);
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
