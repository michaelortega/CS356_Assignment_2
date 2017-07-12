package Models;

import java.util.ArrayList;
import java.util.List;

public class User implements TreeComponent{
    private String userName;
    private List<User> followingList;

    public User(String userName) {
        this.userName = userName;
        followingList = new ArrayList<>();
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
