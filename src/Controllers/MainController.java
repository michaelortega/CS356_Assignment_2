package Controllers;

import Models.TreeComponent;
import Models.User;
import Views.MainView;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class MainController {
    private MainView mainVi;
    private Map<String, TreeComponent> users;
    private Map<String, TreeComponent> groups;
    private TreeModel treeModel;

    public MainController(MainView mainView, TreeModel treeViewModel) {
        mainVi = mainView;
        treeModel = treeViewModel;
        users = new HashMap<>();
        groups = new HashMap<>();
        initiListners();

    }

    private void initiListners() {
        mainVi.getAddUserButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {


                if (isSelectionNull()) {
                    mainVi.noGroupSelectedError("Nothing was selected");
                } else {
                    Class selectionType = mainVi.getJtree().getLastSelectedPathComponent().getClass();
                    if (selectionType == User.class) {
                        mainVi.wrongComponentSelectedErr("A user was selected please select a Group to add a user to.");
                    } else {
                        String newUserName = mainVi.getUserID();
                        if (isUniqueUser(newUserName)) {

                            users.put(newUserName, new User(newUserName));
                            mainVi.addToJTree((DefaultMutableTreeNode) mainVi.getJtree().getLastSelectedPathComponent(), new User(newUserName));
                        } else {
                            mainVi.noneUniqueUserNameError("User name is already taken, please enter a unique user name");
                        }
                    }
                }

            }
        });
    }


    private boolean isUniqueUser(String newUserName) {
        return !(users.containsKey(newUserName.toLowerCase()));
    }

    private boolean isSelectionNull() {
        return mainVi.getJtree().getLastSelectedPathComponent() == null;

    }


}
