package Models;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;


public class User extends Observable implements TreeComponent,Observer{
    private String userName;
    private List<User> followingList;
    private List<String> newsFeedList;
    private List<User> observers;


    private DefaultListModel listModel;

    public User(String userName) {
        this.userName = userName;
        followingList = new ArrayList<>();
        newsFeedList = new ArrayList<>();
        observers = new ArrayList<>();

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
//
//    @Override
//    public synchronized void addObserver(Observer o) {
//        observers.add((User) o);
//    }



//    @Override
//    public void notifyObservers() {
//        for (User followers : observers) {
//            followers.update(newsFeedList.get(newsFeedList.size() - 1));
//        }
//    }

    public void addNewsFeedTweet(String tweet) {
        newsFeedList.add(tweet);
    }

    public void follow(User userRequested) {
        System.out.println(toString()+" added "+userRequested.displayID() + " to followingList");
        followingList.add(userRequested);
    }



    public void setListModel(DefaultListModel listModel) {
        this.listModel = listModel;
    }

    @Override
    public void addObserver(User user) {
        observers.add(user);
        System.out.println(toString() + " added "+ user.displayID()+" to followers");
    }

    @Override
    public void notifyObservers() {
        System.out.println(observers.size());
        for (User myFollowers: observers){
            myFollowers.update(newsFeedList.get(newsFeedList.size()-1));
        }
    }

    @Override
    public void update(String tweet) {
        listModel.addElement(tweet);
        newsFeedList.add(tweet);
    }
}
