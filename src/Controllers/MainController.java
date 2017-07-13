package Controllers;

import Models.TreeComponent;
import Models.User;
import Models.UserGroup;
import Views.MainView;
import Views.UserProfileView;

import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class MainController {
    private MainView mainView;
    private static Map<String, TreeComponent> users;
    private static Map<String, TreeComponent> groups;
    private static MainController instance = null;

    private MainController(){
        mainView = new MainView();
        users = new HashMap<>();
        groups = new HashMap<>();
        initListeners();

    }

    public static MainController getInstance(){
        if (instance == null){
            return new MainController();
        } else {
            return instance;
        }
    }

    //add user
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
                        String newUserName = mainView.getUserID().toLowerCase();
                        if (isValidUser(newUserName)) {
                            User newUser = new User(newUserName);
                            users.put(newUserName, newUser);
                            mainView.addToJTree(selectionNode, newUser);
                            mainView.getUserIDTextfield().setText("");
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
                System.out.println(selectionType.displayID()+"  "+ selectionType.hashCode());
                if (selectionType instanceof User) {
                    new UserProfileView((User) selectionType);
                } else {
                    mainView.displayErrorMessage("Please Select a User not a Group or Root. ");
                }
            }
        });
    }

    private boolean isUniqueGroup(String newGroupName) {
        return !(groups.containsKey(newGroupName.toLowerCase()));
    }

    private boolean isSelectionNull() {
        return mainView.getJtree().getLastSelectedPathComponent() == null;

    }
    public static boolean isValidUser(String request){
        return !(users.containsKey(request.toLowerCase()));
    }

    public static User getUserFromKey(String key){
        return (User)users.get(key);
    }

}
