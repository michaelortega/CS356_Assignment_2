package Views;

import Controllers.MainController;
import Models.User;


import javax.swing.*;
import java.awt.*;
import java.util.List;

public class UserProfileView {
    private List<User> followingList;
    private DefaultListModel<User> userDefaultListModel;
    private DefaultListModel<String> newsFeedDefaultListModel;
    private List<String> newsFeedList;
    private User currentUser;
    private JList newsFeedJList;
    private JList followingJList;
    private JButton postTweetButton;
    private JTextField tweetTextField;
    private JFrame userProfileFrame;
    private JButton followUserButton;
    private JTextField userNameTextField;
    private JScrollPane scrollPane;
    private JScrollPane scrollPane2;
    private JTextField lastUpdateJText;

    public UserProfileView(User currentUser) {
        this.currentUser = currentUser;
        followingList = currentUser.getFollowingList();
        newsFeedList = currentUser.getNewsFeed();
        initJLists();
        initView(this.currentUser);
        initListeners();
        this.currentUser.setListModel(newsFeedDefaultListModel);
    }

    private void initListeners() {

        followUserButton.addActionListener(e -> {
            String userName = userNameTextField.getText().toLowerCase();


//            if (MainController.isValidUser(userName)) {
//                JOptionPane.showMessageDialog(userProfileFrame, "Error: Not a valid User ");
//            } else
            if (currentUser.displayID().equals(userName)) {
                JOptionPane.showMessageDialog(userProfileFrame, "Error: You Cannot follow yourself");
            } else if (currentUser.isFollowing(userName)) {
                JOptionPane.showMessageDialog(userProfileFrame, "Error: Already following " + userName);

            } else {
                User userRequested = MainController.getUserFromKey(userName);
                userDefaultListModel.addElement(userRequested);
                currentUser.follow(userRequested);
                userRequested.addObserver(currentUser);
                userNameTextField.setText("");
            }
        });

        //Post tweet action listener
        postTweetButton.addActionListener(e -> {
            String tweetMsg = tweetTextField.getText();
            String tweet = currentUser.displayID() + " : " + tweetMsg;
            if (!tweet.equals("")) {
                newsFeedDefaultListModel.addElement(tweet);
                currentUser.addNewsFeedTweet(tweet, tweetMsg);
                currentUser.setLastUpdate(System.currentTimeMillis());
                currentUser.notifyObservers();
                tweetTextField.setText("");
                lastUpdateJText.setText(currentUser.getLastUpdateString());

            } else {
                JOptionPane.showMessageDialog(userProfileFrame, "Tweet can not be empty");
            }


        });
    }

    private void initJLists() {
        userDefaultListModel = new DefaultListModel<>();
        for (User user : followingList) {
            userDefaultListModel.addElement(user);
        }
        followingJList = new JList(userDefaultListModel);
        scrollPane = new JScrollPane(followingJList);

        newsFeedDefaultListModel = new DefaultListModel<>();
        for (String feed : newsFeedList) {
            newsFeedDefaultListModel.addElement(feed);
        }
        newsFeedJList = new JList(newsFeedDefaultListModel);
        scrollPane2 = new JScrollPane(newsFeedJList);
    }

    private void initView(User user) {
        userProfileFrame = new JFrame(user.displayID() + " Profile");
        userProfileFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        userNameTextField = new JTextField();
        followUserButton = new JButton("Follow User");
        userProfileFrame.setSize(600, 400);
        userProfileFrame.setResizable(false);

        tweetTextField = new JTextField();
        postTweetButton = new JButton("Post Tweet");

        JTextField timeField = new JTextField("Created: " + currentUser.getTimeCreated());
        timeField.setEditable(false);
        lastUpdateJText = new JTextField(currentUser.getLastUpdateString());
        lastUpdateJText.setEditable(false);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(2, 2, 5, 5));
        topPanel.add(timeField);
        topPanel.add(lastUpdateJText);
        topPanel.add(userNameTextField);
        topPanel.add(followUserButton);

        JPanel centerPanel = new JPanel();
        centerPanel.add(scrollPane);

        JPanel tweetPanel = new JPanel();
        tweetPanel.setLayout(new GridLayout());
        tweetPanel.add(tweetTextField);
        tweetPanel.add(postTweetButton);


        JPanel bottomPanel = new JPanel();
        bottomPanel.add(scrollPane2);

        JPanel mainPanel = new JPanel();

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(topPanel);
        mainPanel.add(centerPanel);
        mainPanel.add(tweetPanel);
        mainPanel.add(bottomPanel);

        userProfileFrame.add(mainPanel);
        userProfileFrame.setVisible(true);
    }


}
