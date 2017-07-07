package Views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created by Michael on 7/7/2017.
 */
public class ButtonsView implements View {

    private JButton addUserButton;
    private JButton addGroupButton;
    private JButton openUserButton;

    private JTextField userIDTextfield;
    private JTextField groupIDTextfield;

    private JButton showUserTotalButton;
    private JButton showMessagesTotalButton;
    private JButton showGroupTotalButton;
    private JButton showPositiveTotalButton;
    private JPanel buttonsPanel;

    public ButtonsView() {
        initComponents();
    }

    private void initComponents() {
        //init top buttons
        addUserButton = new JButton("Add User");
        addGroupButton = new JButton("Add Group");
        openUserButton = new JButton("Open User View");
        openUserButton.setSize(304,30);

        //init top text fields
        userIDTextfield = new JTextField();
        groupIDTextfield = new JTextField();

        //init bottom buttons
        showUserTotalButton = new JButton("Show user total");
        showMessagesTotalButton = new JButton("Show messages total");
        showGroupTotalButton = new JButton("Show group total");
        showPositiveTotalButton = new JButton("Show positive percentage");

        //Top Panel UI
        JPanel topButtonPanel = new JPanel();
        topButtonPanel.setLayout(new GridLayout(2,2,5,5));
        topButtonPanel.setBorder(new EmptyBorder(18,0,0,15));
        topButtonPanel.add(userIDTextfield);
        topButtonPanel.add(addUserButton);
            //Inner top panel UI
        topButtonPanel.add(groupIDTextfield);
        topButtonPanel.add(addGroupButton);

        //Center Panel UI
        JPanel centerButtonPanel = new JPanel();
        centerButtonPanel.setLayout(new BorderLayout());
        centerButtonPanel.setBorder(new EmptyBorder(5,0,5,15));

        JPanel innerCenterPanel = new JPanel();
        centerButtonPanel.setLayout(new BorderLayout());
        innerCenterPanel.add(openUserButton,BorderLayout.CENTER);

        centerButtonPanel.add(innerCenterPanel);

        //Bottom Panel UI
        JPanel bottomButtonPanel = new JPanel();
        bottomButtonPanel.setLayout(new GridLayout(2,2,5,5));
        bottomButtonPanel.setBorder(new EmptyBorder(0,0,15,15));
        bottomButtonPanel.add(showGroupTotalButton);
        bottomButtonPanel.add(showUserTotalButton);
        bottomButtonPanel.add(showMessagesTotalButton);
        bottomButtonPanel.add(showPositiveTotalButton);

        //Main panel
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BorderLayout());
        buttonsPanel.add(topButtonPanel, BorderLayout.NORTH);
        buttonsPanel.add(centerButtonPanel,BorderLayout.CENTER);
        buttonsPanel.add(bottomButtonPanel, BorderLayout.SOUTH);


    }

    @Override
    public JPanel getView(){
        return buttonsPanel;
    }
}
