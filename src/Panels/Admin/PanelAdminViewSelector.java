package Panels.Admin;

import javax.swing.*;

public class PanelAdminViewSelector extends JPanel {
    private JButton usersButton;
    private JButton projectsButton;

    PanelAdminViewSelector(){
        this.usersButton = new JButton("Users");
        this.projectsButton = new JButton("Projects");

        this.add(this.usersButton);
        this.add(this.projectsButton);
    }
}
