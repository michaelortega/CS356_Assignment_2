package Views;

import Models.TreeComponent;
import Models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class UserProfileView {
    private List<User> followingList;
    private List<String> newsFeedList;
    private User currentUser;
    private JList newsFeedJList;
    private JList followingJList;
    private final List<String> defaultFollowingList = (Arrays.asList("Not currently following any users."));
    private final List<String> defaultNewsFeed = (Arrays.asList("No News Feed to display"));
    private JButton postTweetButton;
    private JTextField tweetTextField;
    private Map<String,TreeComponent> users;
    private Map<String,TreeComponent> groups;
    private JFrame userProfileFrame;
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
        postTweetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = tweetTextField.getText();
                if (!(users.containsKey(userName))){
                    JOptionPane.showMessageDialog(,"Not a valid user");
                }
            }
        });
    }

    private void initJLists() {
        if (followingList.size() <= 0) {
            followingJList = new JList(defaultFollowingList.toArray());
        } else {
            followingJList = new JList(followingList.toArray());
        }

        if (newsFeedList.size() <= 0) {
            newsFeedJList = new JList(defaultFollowingList.toArray());
        } else {
            newsFeedJList = new JList(newsFeedList.toArray());
        }

    }

    private void initView(User user) {
        userProfileFrame = new JFrame(user.displayID() + " Profile");
        userProfileFrame.setVisible(true);
        userProfileFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        JTextField userNameTextField = new JTextField();
        JButton followUserButton = new JButton("Follow User");
        userProfileFrame.setSize(700, 400);
        userProfileFrame.setResizable(false);

        tweetTextField = new JTextField();
        postTweetButton = new JButton("Post Tweet");


        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1,2));
        topPanel.add(userNameTextField);
        topPanel.add(followUserButton);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(5,5));
        centerPanel.add(followingJList);

        JPanel tweetPanel = new JPanel();
        tweetPanel.setLayout(new GridLayout());
        tweetPanel.add(tweetTextField);
        tweetPanel.add(postTweetButton);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(newsFeedJList,BorderLayout.CENTER);

        JPanel mainPanel = new JPanel();

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(topPanel);
        mainPanel.add(centerPanel);
        mainPanel.add(tweetPanel);
        mainPanel.add(bottomPanel);

        userProfileFrame.add(mainPanel);
    }
}
