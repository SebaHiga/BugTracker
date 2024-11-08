package Panels.Admin;

import DataBase.ServiceException;
import DataBase.Users.User;
import DataBase.Users.UserService;
import Panels.MainFrame;
import Panels.PanelAbstract;
import Panels.PanelList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PanelAdmin extends PanelAbstract {
    private PanelList<User> panelUsers;

    public PanelAdmin(MainFrame mainFrame) {
        super(mainFrame);
        build();
    }

    private void build() {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        this.panelUsers = new PanelList<User>(this.mainFrame);
        this.panelUsers.build(new UserService().getList());

        this.add(this.panelUsers);

        this.panelUsers.getLabelList().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                inTicketSelection();
            }
        });
    }

    private void inTicketSelection() {
        var selectedTicket = this.panelUsers.getSelectedItem();
    }
}
