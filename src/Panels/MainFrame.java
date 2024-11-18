package Panels;

import Panels.Admin.PanelAdmin;
import Panels.Login.PanelLogin;
import Panels.Projects.PanelProjectsUser;

import javax.swing.*;

public class MainFrame extends JFrame {
    private JFrame mainFrame;

    private PanelLogin panelLogin;
    private PanelProjectsUser panelProjectsUser;
    private PanelAdmin panelAdmin;

    public MainFrame() {
        this.mainFrame = new JFrame();
        this.mainFrame.setSize(700, 800);

        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.panelLogin = new PanelLogin(this);
        this.panelProjectsUser = new PanelProjectsUser(this);
        this.panelAdmin = new PanelAdmin(this);

        displayLoginPane();
//        displayPanelProjectsUser();
//        displayPanelAdmin();
        this.mainFrame.setVisible(true);
    }

    public void displayLoginPane() {
        this.mainFrame.getContentPane().removeAll();
        this.mainFrame.getContentPane().add(this.panelLogin);
        this.mainFrame.pack();
        this.mainFrame.setVisible(true);
    }

    public void displayPanelProjectsUser() {
        this.mainFrame.getContentPane().removeAll();
        this.mainFrame.getContentPane().add(this.panelProjectsUser);
        this.mainFrame.pack();
        this.mainFrame.setVisible(true);
    }

    public void displayPanelAdmin() {
        this.mainFrame.getContentPane().removeAll();
        this.mainFrame.getContentPane().add(this.panelAdmin);
        this.mainFrame.pack();
        this.mainFrame.setVisible(true);
    }
}
