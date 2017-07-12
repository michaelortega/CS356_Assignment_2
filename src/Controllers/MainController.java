package Controllers;

import Models.TreeComponent;
import Models.User;
import Models.UserGroup;
import Views.MainView;
import Views.UserProfileView;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class MainController {
    private MainView mainView;
    private Map<String, TreeComponent> users;
    private Map<String, TreeComponent> groups;
    private TreeModel treeModel;

    public MainController(MainView mainView, TreeModel treeViewModel) {
        this.mainView = mainView;
        treeModel = treeViewModel;
        users = new HashMap<>();
        groups = new HashMap<>();
        initListeners();

    }


    private void initListeners() {
        mainView.getAddUserButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                if (isSelectionNull()) {
                    mainView.displayErrorMessage("Nothing was selected");
                } else {
                    DefaultMutableTreeNode selectionNode = (DefaultMutableTreeNode) mainView.getJtree().getLastSelectedPathComponent();
                    TreeComponent selectionType = (TreeComponent) ((DefaultMutableTreeNode) mainView.getJtree().getLastSelectedPathComponent()).getUserObject();
                    if (selectionType instanceof User) {
                        mainView.displayErrorMessage("A user was selected please select a Group to add a user to.");
                    } else {
                        String newUserName = mainView.getUserID();
                        if (isUniqueUser(newUserName)) {

                            users.put(newUserName, new User(newUserName));
                            mainView.addToJTree(selectionNode, new User(newUserName));
                        } else {
                            mainView.displayErrorMessage("User name is already taken, please enter a unique user name");
                        }
                    }
                }

            }
        });


        // Listener to add a group to JTree
        mainView.getAddGroupButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isSelectionNull()) {
                    mainView.displayErrorMessage("Nothing was selected");
                } else {
                    DefaultMutableTreeNode selectionNode = (DefaultMutableTreeNode) mainView.getJtree().getLastSelectedPathComponent();
                    TreeComponent selectionType = (TreeComponent) ((DefaultMutableTreeNode) mainView.getJtree().getLastSelectedPathComponent()).getUserObject();
                    if (selectionType instanceof User) {
                        mainView.displayErrorMessage("A user was selected please select a Root group.");
                    } else {
                        String newGroupName = mainView.getGroupID();
                        if (isUniqueGroup(newGroupName)) {
                            groups.put(newGroupName, new UserGroup(newGroupName));
                            mainView.addToJTree(selectionNode, new UserGroup(newGroupName));
                        } else {
                            mainView.displayErrorMessage("Group is already taken, please enter a unique group name");
                        }
                    }
                }
            }
        });

        mainView.getUserProfileButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TreeComponent selectionType = (TreeComponent) ((DefaultMutableTreeNode) mainView.getJtree().getLastSelectedPathComponent()).getUserObject();
                if (selectionType instanceof User) {
                    new UserProfileView((User) selectionType, users,groups);
                } else {
                    mainView.displayErrorMessage("Please Select a User not a Group or Root. ");
                }
            }
        });
    }

    private boolean isUniqueGroup(String newGroupName) {
        return !(groups.containsKey(newGroupName.toLowerCase()));
    }


    private boolean isUniqueUser(String newUserName) {
        return !(users.containsKey(newUserName.toLowerCase()));
    }

    private boolean isSelectionNull() {
        return mainView.getJtree().getLastSelectedPathComponent() == null;

    }

}
