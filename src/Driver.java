import Controllers.MainController;
import Views.MainView;
import sun.applet.Main;

import javax.swing.*;

/**
 * Created by Michael on 7/7/2017.
 */
public class Driver {
    public static void main(String[] args)
    {


        SwingUtilities.invokeLater(new Runnable() {
            @Override public void run() {
                MainController mainController = MainController.getInstance();
            }
        });
    }
}
