package Panels.Admin;

import DataBase.Users.User;
import DataBase.Users.UserService;
import Panels.PanelList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelAdminUsersView extends JPanel implements ActionListener {
    private PanelList<User> panelUsers;
    private JButton userAddButton;

    private JPanel panelDetails;
    private PanelAdminUserAdd panelAdminUserAdd;
    private JPanel leftBar;

    PanelAdminUsersView() {
        build();
    }

    private void build() {
        this.userAddButton = new JButton("+");
        this.userAddButton.addActionListener(this);

        var leftBarTop = new JPanel();
        leftBarTop.add(new JLabel("User List"));
        leftBarTop.add(this.userAddButton);

        this.panelUsers = new PanelList<User>();
        this.panelUsers.build(new UserService().getList());

        this.leftBar = new JPanel();
        this.leftBar.setLayout(new BoxLayout(this.leftBar, BoxLayout.Y_AXIS));

        this.leftBar.add(leftBarTop);
        this.leftBar.add(this.panelUsers);

        this.add(this.leftBar);

        this.panelAdminUserAdd = new PanelAdminUserAdd(this);
        this.panelAdminUserAdd.setVisible(false);
        this.add(this.panelAdminUserAdd);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.userAddButton) {
            displayPanelUserAdd();
        }
    }

    private void displayPanelUserAdd() {
        this.leftBar.setVisible(false);
        this.panelAdminUserAdd.setVisible(true);
    }

    public void onAdminUserAddFinish(boolean refresh) {
        this.leftBar.setVisible(true);
        this.panelAdminUserAdd.setVisible(false);
        this.panelUsers.rebuild(new UserService().getList());
    }

    public PanelList<User> getPanel() {
        return this.panelUsers;
    }

    public JList<String> getPanelList() {
        return this.panelUsers.getLabelList();
    }

    private void onAddButton() {

    }
}
