package Panels.Admin;

import DataBase.Users.User;
import DataBase.Users.UserService;
import Panels.PanelList;

import javax.swing.*;

public class PanelAdminUsersView extends JPanel {
    private PanelList<User> panelUsers;

    PanelAdminUsersView(){
        build();
    }

    private void build(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.panelUsers = new PanelList<User>();
        this.panelUsers.build(new UserService().getList());

        this.add(panelUsers);
    }

    public PanelList<User> getPanel(){
        return this.panelUsers;
    }

    public JList<String> getPanelList(){
        return this.panelUsers.getLabelList();
    }
}
