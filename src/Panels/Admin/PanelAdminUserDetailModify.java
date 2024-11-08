package Panels.Admin;

import DataBase.ServiceException;
import DataBase.Users.User;
import DataBase.Users.UserService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelAdminUserDetailModify extends PanelAdminUserDetail implements ActionListener {
    private JButton startEditButton;
    private User currentUser;

    public PanelAdminUserDetailModify(PanelAdminUsersView parentPanel) {
        super(parentPanel);

        this.panelOkCancel.getOkBtn().addActionListener(this);
        this.panelOkCancel.getCancelBtn().addActionListener(this);
        this.startEditButton.addActionListener(this);
    }

    public void build() {
        this.startEditButton = new JButton("Start edit");

        super.build();

        this.removeAll();

        this.add(this.getFormPane());
        this.add(this.startEditButton);
        this.add(this.panelOkCancel);
    }

    public void populate(User user) {
        this.currentUser = user;

        this.nameTF.setText(user.getName());
        this.emailTF.setText(user.getEmail());
        this.passwordTF.setText(user.getPass());
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        var name = this.nameTF.getText();
        var email = this.emailTF.getText();
        var password = this.passwordTF.getText();

        if (actionEvent.getSource() == this.startEditButton) {
            if (name.isEmpty() || email.isEmpty() || password.isEmpty()){
                return;
            }

            setOnEditMode(true);

            this.startEditButton.setVisible(false);
            this.panelOkCancel.setVisible(true);
        }

        if (actionEvent.getSource() == this.panelOkCancel.getOkBtn()) {
            try {
                new UserService().update(new User(name, email, password));
            } catch (ServiceException e) {
                // Couldn't add user, name already taken
            }

            this.panelOkCancel.setVisible(false);
            this.startEditButton.setVisible(true);

            setOnEditMode(false);

            this.panelParent.refreshPanelUsers();
            this.panelParent.showNormal();
        }
        if (actionEvent.getSource() == this.panelOkCancel.getCancelBtn()) {
            this.populate(this.currentUser);

            this.panelOkCancel.setVisible(false);
            this.startEditButton.setVisible(true);

            setOnEditMode(false);
        }
    }
}
