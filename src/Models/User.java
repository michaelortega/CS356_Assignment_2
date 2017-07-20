package Models;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.*;


public class User extends Observable implements TreeComponent, Observer, Visitable {
    private String userName;
    private List<User> followingList;
    private List<String> newsFeedList;
    private List<User> observers;
    private int positiveCount;
    private final String[] positiveWords = {"smile", "happy", "love"};
    private Set<String> positiveSet;
    private long lastUpdate;
    private DefaultListModel listModel;
    private long timeCreated;

    public long getLastUpdate() {
        return lastUpdate;
    }

    public String getLastUpdateString() {
        if (lastUpdate == -9999) {
            return "No last update - User as not posted anything.";
        } else {
            Date date = new Date();
            date.setTime(lastUpdate);
            SimpleDateFormat output = new SimpleDateFormat("M/dd/yyyy hh:mm:ss aa");
            return "Last Update: " + output.format(date);
        }
    }

    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }


    public String getTimeCreated() {
        Date date = new Date();
        date.setTime(timeCreated);
        SimpleDateFormat output = new SimpleDateFormat("hh:mm:ss aa");
        return output.format(date);
    }




    public User(String userName) {
        this.userName = userName;
        followingList = new ArrayList<>();
        newsFeedList = new ArrayList<>();
        observers = new ArrayList<>();
        positiveCount = 0;
        lastUpdate = -9999;
        positiveSet = new HashSet<>(Arrays.asList(positiveWords));
        timeCreated = System.currentTimeMillis();
    }

    public int getPositiveCount() {
        return positiveCount;
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
    public String toString() {
        return displayOnJTree();
    }


    public void addNewsFeedTweet(String tweet, String tweetMsg) {
        checkPositive(tweetMsg);
        newsFeedList.add(tweet);
    }

    private void checkPositive(String s) {
        String[] tokens = s.split(" ");
        for (String tok : tokens) {
            if (positiveSet.contains(tok.toLowerCase())) {
                positiveCount++;
            }
        }
    }

    public void follow(User userRequested) {
        System.out.println(toString() + " added " + userRequested.displayID() + " to followingList");
        followingList.add(userRequested);
    }


    public void setListModel(DefaultListModel listModel) {
        this.listModel = listModel;
    }

    @Override
    public void addObserver(User user) {
        observers.add(user);
        System.out.println(toString() + " added " + user.displayID() + " to followers");
    }

    @Override
    public void notifyObservers() {
        System.out.println(observers.size());
        for (User myFollowers : observers) {
            myFollowers.update(newsFeedList.get(newsFeedList.size() - 1));
        }
    }

    @Override
    public void update(String tweet) {
        listModel.addElement(tweet);
        newsFeedList.add(tweet);
    }


    public int getMessageCount() {
        return newsFeedList.size();
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }


    public boolean isFollowing(String userName) {
        for (User user : followingList) {
            if (user.displayID().toLowerCase().equals(userName.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public String getUserName() {
        return userName;
    }
}
