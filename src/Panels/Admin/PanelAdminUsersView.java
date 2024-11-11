package Panels.Admin;

import DataBase.ServiceException;
import DataBase.Users.User;
import DataBase.Users.UserService;
import Panels.PanelList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelAdminUsersView extends JPanel implements ActionListener, ListSelectionListener {
    private PanelList<User> panelUsers;
    private JButton userAddButton;
    private JButton userRemoveButton;

    private JPanel panelDetails;
    private PanelAdminUserDetailAdd panelAdminUserDetailAdd;
    private JPanel leftBar;

    private PanelAdminUserDetailModify panelAdminUserDetailModify;

    private UserService userService;

    PanelAdminUsersView() {
        build();
    }

    private void build() {
        this.userService = new UserService();

        this.setLayout(new BorderLayout());

        this.userAddButton = new JButton("+");
        this.userAddButton.addActionListener(this);

        this.userRemoveButton = new JButton("-");
        this.userRemoveButton.addActionListener(this);

        this.panelUsers = new PanelList<User>();
        this.panelUsers.build(new UserService().getList());

        var leftBarBottom = new JPanel();

        leftBarBottom.add(this.userAddButton);
        leftBarBottom.add(this.userRemoveButton);

        this.leftBar = new JPanel();

        this.leftBar.setLayout(new BoxLayout(this.leftBar, BoxLayout.Y_AXIS));

        this.leftBar.add(new JLabel("User List"));
        this.leftBar.add(this.panelUsers);
        this.leftBar.add(leftBarBottom);

        this.add(this.leftBar, BorderLayout.WEST);

        var centerPanel = new JPanel();
        this.panelAdminUserDetailAdd = new PanelAdminUserDetailAdd(this);

        this.panelAdminUserDetailModify = new PanelAdminUserDetailModify(this);

        centerPanel.add(this.panelAdminUserDetailAdd);
        centerPanel.add(this.panelAdminUserDetailModify);
        this.add(centerPanel, BorderLayout.CENTER);

        showNormal();
    }

    public void showNormal(){
        this.refreshPanelUsers();

        this.leftBar.setVisible(true);

        this.panelAdminUserDetailAdd.setVisible(false);

        this.panelAdminUserDetailModify.setOnEditMode(false);
        this.panelAdminUserDetailModify.setVisible(true);
    }

    @Override
    public void valueChanged(ListSelectionEvent event) {
        if(event.getSource() == this.panelUsers.getLabelList()){
            var selected = this.panelUsers.getSelectedItem();

            this.panelAdminUserDetailModify.populate(selected);
        }
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.userAddButton) {
            displayPanelUserAdd();
        }
        else if (actionEvent.getSource() == this.userRemoveButton) {
            var selectedUser = this.panelUsers.getSelectedItem();

            if(selectedUser == null){
                return;
            }

            String msg = "Are you sure you want to remove {USER}?";

            var result = JOptionPane.showConfirmDialog(this,
                    msg.replace("{USER}", selectedUser.getName()), "Remove user",
                    JOptionPane.YES_NO_OPTION);

            if(result == JOptionPane.YES_OPTION) {
                try {
                    this.userService.delete(selectedUser);
                    this.showNormal();
                } catch (ServiceException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void displayPanelUserAdd() {
        this.leftBar.setVisible(false);
        this.panelAdminUserDetailModify.setVisible(false);
        this.panelAdminUserDetailAdd.setVisible(true);
    }

    public void onAdminUserAddFinish() {
        refreshPanelUsers();
        this.showNormal();
    }

    public void refreshPanelUsers(){
        this.panelUsers.setVisible(false);
        this.panelUsers.rebuild(this.userService.getList());
        this.panelUsers.getLabelList().addListSelectionListener(this);
        this.panelUsers.setVisible(true);
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
