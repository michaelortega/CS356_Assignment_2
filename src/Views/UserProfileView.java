package Views;

import Models.User;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Vector;


/**
 * Created by Michael on 7/11/2017.
 */
public class UserProfileView {
    private List<User> followingList;
    private User user;
    private JList newsFeed;
    private JList followingJList;

    public UserProfileView(User user) {
        this.user = user;
        initJLists(user.getFollowingList(),user.getNewsFeed());
        initView(this.user);
    }

    private void initJLists(List<User> followingList, List<String> newsFeed) {
        if (followingList.size() < 0){
            followingJList = new JList(followingList.toArray());
        }
        followingJList = new JList(followingList.toArray());
    }

    public void initView(User user) {
        JFrame userProfileFrame = new JFrame(user.displayID() + " Profile");
        userProfileFrame.setVisible(true);
        userProfileFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        JTextField userNameTextField = new JTextField();
        JButton followUserButton = new JButton("Follow User");
        userProfileFrame.setSize(700, 400);
        userProfileFrame.setResizable(false);

        JTextField tweetTextField = new JTextField();
        JButton postTweetButton = new JButton("Post Tweet");

        newsFeed = new JList();
        followingJList = new JList( followingList.toArray());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        topPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        topPanel.add(userNameTextField,BorderLayout.WEST);
        topPanel.add(followUserButton,BorderLayout.EAST);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(followingJList);

        userProfileFrame.add(topPanel);
     //   userProfileFrame.add(centerPanel);
    }
}
