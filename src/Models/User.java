package Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class User extends Observable implements TreeComponent, Observer {
    private String userName;
    private List<User> followingList;
    private List<String> newsFeedList;

    public User(String userName) {
        this.userName = userName;
        followingList = new ArrayList<>();
        newsFeedList = new ArrayList<>();

    }

    public List<User> getFollowingList() {
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

    @Override
    public synchronized void addObserver(Observer o) {
        followingList.add((User)o);
    }

    @Override
    public void update(Observable o, Object arg) {
        this.newsFeedList.add((String)arg);
    }

    @Override
    public void notifyObservers() {
        for (User following: followingList){
            following.update(following,newsFeedList.get(newsFeedList.size() - 1));
        }
    }

    public void addNewsFeedTweet(String tweet) {
        newsFeedList.add(tweet);
    }
}
