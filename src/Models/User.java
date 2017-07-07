package Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 7/7/2017.
 */
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
    public String toString() {
        return displayOnJTree();
    }
}
