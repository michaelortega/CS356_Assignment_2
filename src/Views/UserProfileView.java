package Views;

import Models.User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserProfileView {
    private List<User> followingList;
    private List<String> newsFeedList;
    private User currentUser;
    private JList newsFeed;
    private JList followingJList;
    private final List<String> defaultFollowingList = (Arrays.asList("Not currently following any users."));

    public UserProfileView(User currentUser) {
        this.currentUser = currentUser;
        followingList = currentUser.getFollowingList();
        newsFeedList = currentUser.getNewsFeed();
        initJLists();
        initView(this.currentUser);
    }

    private void initJLists() {
        if (followingList.size() < 0) {
            List<String> s = new ArrayList<>();
            followingJList = new JList(s.toArray());
        } else {
            followingJList = new JList(followingList.toArray());
        }
    }

    private void initView(User user) {
        JFrame userProfileFrame = new JFrame(user.displayID() + " Profile");
        userProfileFrame.setVisible(true);
        userProfileFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        JTextField userNameTextField = new JTextField();
        JButton followUserButton = new JButton("Follow User");
        userProfileFrame.setSize(700, 400);
        userProfileFrame.setResizable(false);

        JTextField tweetTextField = new JTextField();
        JButton postTweetButton = new JButton("Post Tweet");


        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.add(userNameTextField, BorderLayout.NORTH);
        topPanel.add(followUserButton);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(followingJList, BorderLayout.CENTER);

        JPanel tweetPanel = new JPanel();
        tweetPanel.setLayout(new BorderLayout());
        tweetPanel.add(tweetTextField, BorderLayout.WEST);
        tweetPanel.add(postTweetButton, BorderLayout.EAST);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(topPanel);
        mainPanel.add(centerPanel);
        mainPanel.add(tweetPanel);

        userProfileFrame.add(mainPanel);
    }
}
