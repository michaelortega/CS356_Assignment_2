package Models;

/**
 * Created by Michael on 7/12/2017.
 */
public abstract class Observable {
    abstract public void addObserver(User user);
    abstract public void notifyObservers();


}
