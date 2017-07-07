package Views;

import javax.swing.*;
import java.awt.*;


public class MainView {
    private View treeView;
    private View buttonsView;

    public MainView(){
        treeView = new TreeView();
        buttonsView = new ButtonsView();
        initView();
    }

    private void initView() {
        JFrame frame = new JFrame("Mini Twitter - Michael Ortega - CS 365");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700,400);
        frame.setResizable(false);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        frame.add(panel);
        panel.add(treeView.getView(),BorderLayout.WEST);
        panel.add(buttonsView.getView(),BorderLayout.EAST);

    }
}
