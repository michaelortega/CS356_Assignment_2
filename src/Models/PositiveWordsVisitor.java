package Models;

/**
 * Created by Michael on 7/13/2017.
 */
public class PositiveWordsVisitor implements Visitor {

    private int positiveCount;

    public PositiveWordsVisitor() {
        positiveCount = 0;
    }

    public int getPositiveCount() {
        return positiveCount;
    }

    @Override
    public void visit(User user) {
        positiveCount += user.getPositiveCount();
    }

}
