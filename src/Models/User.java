package Models;

import java.util.ArrayList;
import java.util.List;

public class User implements TreeComponent{
    private String userName;
    private List<User> followingList;
    private List<String> newsFeedList;
    private final String defaultFollwingList = "Not currently following any users.";
    private final String defaultNewsFeedList = "News Feed is currently empty.";

    public User(String userName) {
        this.userName = userName;
        followingList = new ArrayList<>();
        followingList.add(defaultFollwingList);
        newsFeedList.add(defaultNewsFeedList);
        newsFeedList = new ArrayList<>();
    }
    public List<User> getFollowingList(){
        return followingList;
    }

    public List<String> getNewsFeed() {
        return newsFeedList;
    }
    @Override
    public String displayOnJTree() {
        return userName;
    }

    @Override
    public String displayID() {
        return userName;
    }

    @Override
    public Class getType() {
        return User.class;
    }

    @Override
    public String toString() {
        return displayOnJTree();
    }


}
