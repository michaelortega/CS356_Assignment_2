package Views;

import Models.TreeComponent;
import Models.User;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.util.Observable;

public class UserProfileView extends Observable {
    private List<User> followingList;
    private DefaultListModel<User> userDefaultListModel;
    private DefaultListModel<String> newsFeedDefaultListModel;
    private List<String> newsFeedList;
    private User currentUser;
    private JList newsFeedJList;
    private JList followingJList;
    private JButton postTweetButton;
    private JTextField tweetTextField;
    private Map<String, TreeComponent> users;
    private Map<String, TreeComponent> groups;
    private JFrame userProfileFrame;
    private JButton followUserButton;
    private JTextField userNameTextField;

    public UserProfileView(User currentUser, Map<String, TreeComponent> users, Map<String, TreeComponent> groups) {
        this.currentUser = currentUser;
        followingList = currentUser.getFollowingList();
        newsFeedList = currentUser.getNewsFeed();
        this.users = users;
        this.groups = groups;
        initJLists();
        initView(this.currentUser);
        initListeners();
    }

    private void initListeners() {
        followUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = userNameTextField.getText().toLowerCase();
                if (!(users.containsKey(userName))|| currentUser.displayID().equals(userName) ) {
                    JOptionPane.showMessageDialog(userProfileFrame, "Not a valid user");
                } else {
                    User userRequested = (User) users.get(userName);
                    System.out.println(userRequested);
                    userDefaultListModel.addElement(userRequested);
                    //currentUser.follow(userRequested);

                }
            }
        });

        //Post tweet action listener
        postTweetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tweet = currentUser.displayID()+" : "+tweetTextField.getText();

                newsFeedDefaultListModel.addElement(tweet);

            }
        });
    }

    private void initJLists() {
        userDefaultListModel = new DefaultListModel<>();
        for (User user : followingList) {
            userDefaultListModel.addElement(user);
        }
        followingJList = new JList(userDefaultListModel);

        newsFeedDefaultListModel = new DefaultListModel<>();
        for (String feed : newsFeedList) {
            newsFeedDefaultListModel.addElement(feed);
        }
        newsFeedJList = new JList(newsFeedDefaultListModel);
    }

    private void initView(User user) {
        userProfileFrame = new JFrame(user.displayID() + " Profile");
        userProfileFrame.setVisible(true);
        userProfileFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        userNameTextField = new JTextField();
        followUserButton = new JButton("Follow User");
        userProfileFrame.setSize(700, 400);
        userProfileFrame.setResizable(false);

        tweetTextField = new JTextField();
        postTweetButton = new JButton("Post Tweet");


        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 2));
        topPanel.add(userNameTextField);
        topPanel.add(followUserButton);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(5, 5));
        centerPanel.add(followingJList);

        JPanel tweetPanel = new JPanel();
        tweetPanel.setLayout(new GridLayout());
        tweetPanel.add(tweetTextField);
        tweetPanel.add(postTweetButton);


        JPanel bottomPanel = new JPanel();
        bottomPanel.add(newsFeedJList, BorderLayout.CENTER);

        JPanel mainPanel = new JPanel();

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(topPanel);
        mainPanel.add(centerPanel);
        mainPanel.add(tweetPanel);
        mainPanel.add(bottomPanel);

        userProfileFrame.add(mainPanel);
    }
}
