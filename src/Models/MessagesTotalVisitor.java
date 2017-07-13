package Models;

/**
 * Created by Michael on 7/13/2017.
 */
public class MessagesTotalVisitor implements Visitor {


    private int messagesCount;


    public MessagesTotalVisitor() {
        messagesCount = 0;
    }



    public int getMessagesCount() {
        return messagesCount;
    }

    @Override
    public void visit(User user) {
        messagesCount+=user.getMessageCount();
    }


}
