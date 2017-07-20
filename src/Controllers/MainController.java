package Controllers;

import Models.*;
import Views.MainView;
import Views.UserProfileView;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class MainController {
    private MainView mainView;
    private static Map<String, TreeComponent> users;
    private List<User> userList = new ArrayList<>();
    private List<UserGroup> groupList = new ArrayList<>();
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
//                        if (isValidUser(newUserName)) {
                        User newUser = new User(newUserName);
                        users.put(newUserName, newUser);
                        userList.add(newUser);
                        mainView.addToJTree(selectionNode, newUser);
                        mainView.getUserIDTextfield().setText("");
//                        } else {
//                            mainView.displayErrorMessage("User name is already taken, please enter a unique user name");
//                        }
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
                        //if (isUniqueGroup(newGroupName)) {
                        UserGroup group = new UserGroup(newGroupName);
                        groups.put(newGroupName, group);
                        groupList.add(group);
                        mainView.addToJTree(selectionNode, group);
                        mainView.getGroupIDTextfield().setText("");
//                        } else {
//                            mainView.displayErrorMessage("Group is already taken, please enter a unique group name");
//                        }
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
                displayMessage("Number of total group: " + groups.size());
            }
        });

        mainView.getShowUserTotalButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                displayMessage("Number of total users: " + users.size());
            }
        });

        mainView.getValidIDsButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Iterator userItr = userList.iterator();
                Iterator groupItr = groupList.iterator();

                HashSet<String> userNames = new HashSet<>();
                HashSet<String> groupNames = new HashSet<>();
                int nonUniqueUserCtr = 0;
                int nonUniqueGroupCtr = 0;
                int spacesCtr = 0;
                while (userItr.hasNext()) {
                    String userName = userItr.next().toString();

                    if (!userNames.contains(userName)) {
                        if (userName.contains(" ")) {
                            spacesCtr++;
                        }
                        userNames.add(userName);
                    } else if (userNames.contains(userName)) {
                        nonUniqueUserCtr = nonUniqueUserCtr + 2;
                    } else if (userName.contains(" ")) {
                        spacesCtr++;
                    }
                }

                while (groupItr.hasNext()) {
                    String key = groupItr.next().toString();


                    if (!groupNames.contains(key)) {
                        if (key.contains(" ")) {
                            spacesCtr++;
                        }
                        groupNames.add(key);
                    } else if (groupNames.contains(key)) {
                        nonUniqueGroupCtr = nonUniqueGroupCtr + 2;
                    } else if (key.contains(" ")) {
                        spacesCtr++;
                    }
                }


                displayMessage("Number of non-unique users: " + nonUniqueUserCtr
                        + "\n Number of non-unique groups: " + nonUniqueGroupCtr
                        + "\n Number of users and groups with spaces: " + spacesCtr);
            }
        });


        mainView.getLastUpdateButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MostRecentVisitor mostRecentVisitor = new MostRecentVisitor();
                for (User user: userList){
                    user.accept(mostRecentVisitor);
                }
                displayMessage(mostRecentVisitor.getMostRecent());
            }
        });

    } // end of listeners

    private void displayMessage(String msg) {
        JOptionPane.showMessageDialog(mainView.getFrame(), msg);
    }

//    //private boolean isUniqueGroup(String newGroupName) {
//        return !(groups.containsKey(newGroupName.toLowerCase()));
//    }

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

