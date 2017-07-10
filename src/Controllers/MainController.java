package Controllers;

import Views.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController {
    private MainView homeScreenView;

    public MainController(MainView mainView) {
        homeScreenView = mainView;
        homeScreenView.getButtonsView().userButtonListener(new addUserButtonListener());

    }

    private class addUserButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
           // homeScreenView.getButtonsView().get

        }
    }
}
