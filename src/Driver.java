import Views.MainView;

import javax.swing.*;

/**
 * Created by Michael on 7/7/2017.
 */
public class Driver {
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainView();
            }
        });
    }
}
