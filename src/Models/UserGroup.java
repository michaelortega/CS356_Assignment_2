package Models;

public class UserGroup implements TreeComponent {
    private String groupID;
    private long createdTime;


    public UserGroup(String groupID) {
        this.groupID = groupID;
        createdTime = System.currentTimeMillis();
    }


    @Override
    public String toString() {
        return displayOnJTree();
    }

    @Override
    public String displayOnJTree() {
        return groupID;
    }

    @Override
    public String displayID() {
        return groupID;
    }



}
