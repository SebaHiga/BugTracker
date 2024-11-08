package Panels.Admin;

import DataBase.ServiceException;
import DataBase.Users.User;
import DataBase.Users.UserService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelAdminUserDetailAdd extends PanelAdminUserDetail implements ActionListener {
    public PanelAdminUserDetailAdd(PanelAdminUsersView parentPanel) {
        super(parentPanel);
        this.panelOkCancel.getOkBtn().addActionListener(this);
        this.panelOkCancel.getCancelBtn().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.panelOkCancel.getOkBtn()) {
            var name = this.nameTF.getText();
            var email = this.emailTF.getText();
            var password = this.passwordTF.getText();

            try {
                new UserService().add(new User(name, email, password));
            } catch (ServiceException e) {
                // Couldn't add user, name already taken
            }
        }

        this.panelParent.onAdminUserAddFinish();
        this.clearAllTextFields();
    }
}
