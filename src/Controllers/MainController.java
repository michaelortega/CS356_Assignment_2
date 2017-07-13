package Controllers;

import Models.MessagesTotalVisitor;
import Models.PositiveWordsVisitor;
import Models.TreeComponent;
import Models.User;
import Models.UserGroup;
import Models.Visitor;
import Views.MainView;
import Views.UserProfileView;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MainController {
    private MainView mainView;
    private static Map<String, TreeComponent> users;
    private static Map<String, TreeComponent> groups;
    private static MainController instance = null;

    private MainController() {
        mainView = new MainView();
        users = new HashMap<>();
        groups = new HashMap<>();
        initListeners();

    }

    public static MainController getInstance() {
        if (instance == null) {
            return new MainController();
        } else {
            return instance;
        }
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


        // UserGroup Listener
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
                            mainView.getGroupIDTextfield().setText("");
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
                    new UserProfileView((User) selectionType);
                } else {
                    mainView.displayErrorMessage("Please Select a User not a Group or Root. ");
                }
            }
        });

        mainView.getShowMessagesTotalButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MessagesTotalVisitor visitor = new MessagesTotalVisitor();
                User u = null;
                Iterator itr = users.entrySet().iterator();
                while (itr.hasNext()) {
                    Map.Entry pair = (Map.Entry) itr.next();
                    u = (User) pair.getValue();
                    u.accept(visitor);
                }
                String count = String.valueOf(visitor.getMessagesCount());
                displayMessage("Number of total messages: " + count);
            }
        });

        mainView.getShowPositiveTotalButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                PositiveWordsVisitor positiveWordsVisitor = new PositiveWordsVisitor();
                iterateHashMap(positiveWordsVisitor);
                String count = String.valueOf(positiveWordsVisitor.getPositiveCount());
                displayMessage("Total number of positive words: " + count);

            }
        });

        mainView.getShowGroupTotalButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                displayMessage("Number of total group: "+ groups.size());
            }
        });

        mainView.getShowUserTotalButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                displayMessage("Number of total users: "+ users.size());
            }
        });
    }

    private void displayMessage(String msg) {
        JOptionPane.showMessageDialog(mainView.getFrame(), msg);
    }

    private boolean isUniqueGroup(String newGroupName) {
        return !(groups.containsKey(newGroupName.toLowerCase()));
    }

    private boolean isSelectionNull() {
        return mainView.getJtree().getLastSelectedPathComponent() == null;

    }

    public static boolean isValidUser(String request) {
        return !(users.containsKey(request.toLowerCase()));
    }

    public static User getUserFromKey(String key) {
        return (User) users.get(key);
    }

    public static Map<String, TreeComponent> getUsers() {
        return users;
    }

    public static Map<String, TreeComponent> getGroups() {
        return groups;
    }

    public void iterateHashMap(Visitor v) {
        User u = null;
        Iterator itr = users.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry pair = (Map.Entry) itr.next();
            u = (User) pair.getValue();
            u.accept(v);
        }
    }
}

