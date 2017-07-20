package Models;

/**
 * Created by Michael on 7/13/2017.
 */
public class MostRecentVisitor implements Visitor {

    private User mostRecentUser;


    public MostRecentVisitor() {
        mostRecentUser = new User("");
        mostRecentUser.setLastUpdate(0);
    }


    public String getMostRecent() {
        if (mostRecentUser.getLastUpdate() == 0) {
            return "no updates from any users no most recent.";
        } else {
            return "Most recent user:  "+mostRecentUser.getUserName();
        }
    }

    @Override
    public void visit(User user) {
        if (user.getLastUpdate() >mostRecentUser.getLastUpdate() ){
            mostRecentUser = user;
        }
    }


}
