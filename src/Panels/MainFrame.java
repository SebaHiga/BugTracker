package Panels;

import Panels.Login.PanelLogin;
import Panels.Projects.PanelProjectsUser;

import javax.swing.*;

public class MainFrame extends JFrame{
    private JFrame mainFrame;

    private PanelLogin panelLogin;
    private PanelProjectsUser panelProjectsUser;

    public MainFrame(){
        this.mainFrame = new JFrame();
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.panelLogin = new PanelLogin(this);
        this.panelProjectsUser = new PanelProjectsUser(this);
        displayLoginPane();
    }

    public void displayLoginPane() {
        this.mainFrame.getContentPane().removeAll();
        this.mainFrame.getContentPane().add(this.panelLogin);
        this.mainFrame.pack();
        this.mainFrame.setVisible(true);
    }

    public void displayPanelProjectsUser(){
        this.mainFrame.getContentPane().removeAll();
        this.mainFrame.getContentPane().add(this.panelProjectsUser);
        this.mainFrame.pack();
        this.mainFrame.setVisible(true);

    }
}
