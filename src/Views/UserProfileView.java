package Views;

import Models.TreeComponent;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Michael on 7/11/2017.
 */
public class UserProfileView {
    private JList followingList;
    private TreeComponent user;
    private JList newsFeed;

    public UserProfileView(TreeComponent user) {
        this.user = user;
        initView(this.user);
    }

    public void initView(TreeComponent user) {
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

        JPanel profilePanel = new JPanel();
        profilePanel.setLayout(new BorderLayout());
        
    }
}
