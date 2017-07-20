package Views;

import Models.TreeComponent;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;
import java.awt.*;


public class MainView {
    private TreeView treeView;

    private JButton addUserButton;
    private JButton addGroupButton;
    private JButton openUserButton;

    private JTextField userIDTextfield;
    private JTextField groupIDTextfield;

    private JButton showUserTotalButton;
    private JButton showMessagesTotalButton;
    private JButton showGroupTotalButton;
    private JButton showPositiveTotalButton;
    private JPanel buttonsPanel;
    private JButton validIDsButton;


    private JButton lastUpdateButton;


    private JFrame frame;

    public JButton getAddUserButton() {
        return addUserButton;
    }

    public JButton getAddGroupButton() {
        return addGroupButton;
    }

    public JButton getOpenUserButton() {
        return openUserButton;
    }

    public JTextField getUserIDTextfield() {
        return userIDTextfield;
    }

    public JTextField getGroupIDTextfield() {
        return groupIDTextfield;
    }

    public JButton getShowUserTotalButton() {
        return showUserTotalButton;
    }

    public JButton getShowMessagesTotalButton() {
        return showMessagesTotalButton;
    }

    public JButton getShowGroupTotalButton() {
        return showGroupTotalButton;
    }

    public JButton getShowPositiveTotalButton() {
        return showPositiveTotalButton;
    }

    public JPanel getButtonsPanel() {
        return buttonsPanel;
    }

    public JButton getValidIDsButton() {
        return validIDsButton;
    }

    public MainView() {
        treeView = new TreeView();
        initButtons();
        initView();
    }

    private void initView() {
        frame = new JFrame("Mini Twitter - Michael Ortega - CS 365");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(700, 400);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        frame.add(panel);
        panel.add(treeView.getTreeView(), BorderLayout.WEST);
        panel.add(buttonsPanel, BorderLayout.EAST);

    }

    private void initButtons() {
        //init top buttons
        addUserButton = new JButton("Add User");
        addGroupButton = new JButton("Add Group");
        openUserButton = new JButton("Open User View");
        openUserButton.setSize(304, 30);
        validIDsButton = new JButton("Check Valid ID's");
        lastUpdateButton = new JButton("Recent User");
        //init top text fields
        userIDTextfield = new JTextField();
        groupIDTextfield = new JTextField();

        //init bottom buttons
        showUserTotalButton = new JButton("Show user total");
        showMessagesTotalButton = new JButton("Show messages total");
        showGroupTotalButton = new JButton("Show group total");
        showPositiveTotalButton = new JButton("Show positive percentage");
        //Top Panel UI
        JPanel topButtonPanel = new JPanel();
        topButtonPanel.setLayout(new GridLayout(2, 2, 5, 5));
        topButtonPanel.setBorder(new EmptyBorder(18, 0, 0, 15));
        topButtonPanel.add(userIDTextfield);
        topButtonPanel.add(addUserButton);
        //Inner top panel UI
        topButtonPanel.add(groupIDTextfield);
        topButtonPanel.add(addGroupButton);

        //Center Panel UI
        JPanel centerButtonPanel = new JPanel();
        centerButtonPanel.setLayout(new BorderLayout());
        centerButtonPanel.setBorder(new EmptyBorder(5, 0, 5, 15));

        JPanel innerCenterPanel = new JPanel();
        centerButtonPanel.setLayout(new GridLayout(1,3));
        innerCenterPanel.add(openUserButton);
        innerCenterPanel.add(validIDsButton);
        innerCenterPanel.add(lastUpdateButton);


        centerButtonPanel.add(innerCenterPanel);

        //Bottom Panel UI
        JPanel bottomButtonPanel = new JPanel();
        bottomButtonPanel.setLayout(new GridLayout(2, 2, 5, 5));
        bottomButtonPanel.setBorder(new EmptyBorder(0, 0, 15, 15));
        bottomButtonPanel.add(showGroupTotalButton);
        bottomButtonPanel.add(showUserTotalButton);
        bottomButtonPanel.add(showMessagesTotalButton);
        bottomButtonPanel.add(showPositiveTotalButton);

        //Main panel
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BorderLayout());
        buttonsPanel.add(topButtonPanel, BorderLayout.NORTH);
        buttonsPanel.add(centerButtonPanel, BorderLayout.CENTER);
        buttonsPanel.add(bottomButtonPanel, BorderLayout.SOUTH);


    }

    public JTree getJtree() {
        return treeView.getUserTree();
    }


    public void displayErrorMessage(String err) {
        JOptionPane.showMessageDialog(frame, err);
    }

    public String getUserID() {
        return userIDTextfield.getText();
    }

    public void addToJTree(DefaultMutableTreeNode lastSelectedPathComponent, TreeComponent treeComponent) {


        treeView.add(lastSelectedPathComponent, treeComponent);
    }

    public JFrame getFrame() {
        return frame;
    }

    public String getGroupID() {
        return getGroupIDTextfield().getText();
    }

    public JButton getUserProfileButton() {
        return openUserButton;
    }
    public JButton getLastUpdateButton() {
        return lastUpdateButton;
    }
}
