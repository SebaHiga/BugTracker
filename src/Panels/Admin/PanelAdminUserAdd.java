package Panels.Admin;

import DataBase.ServiceException;
import DataBase.Users.User;
import DataBase.Users.UserService;
import Panels.PanelOkCancel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelAdminUserAdd extends JPanel implements ActionListener {
    private PanelAdminUsersView panelParent;

    private JLabel nameLabel;
    private JLabel emailLabel;
    private JLabel passwordLabel;

    private JTextField nameTF;
    private JTextField emailTF;
    private JTextField passwordTF;

    private PanelOkCancel panelOkCancel;

    public PanelAdminUserAdd(PanelAdminUsersView parentPanel) {
        this.panelParent = parentPanel;

        build();
    }

    private void build() {
        this.panelOkCancel = new PanelOkCancel();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(getFormPane());
        this.add(panelOkCancel);

        this.panelOkCancel.getOkBtn().addActionListener(this);
        this.panelOkCancel.getCancelBtn().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.panelOkCancel.getOkBtn()) {
            var name = this.nameTF.getText();
            var email = this.emailTF.getText();
            var password = this.passwordTF.getText();

            try{
                new UserService().add(new User(name, email, password));
            }
            catch (ServiceException e){
                // Couldn't add user, name already taken
            }

            this.panelParent.onAdminUserAddFinish(true);
        }
        if (actionEvent.getSource() == this.panelOkCancel.getCancelBtn()) {
            this.panelParent.onAdminUserAddFinish(false);
        }
    }

    JPanel getFormPane(){
        var formGrid = new JPanel();
        formGrid.setLayout(new GridLayout(3, 3));

        this.nameLabel = new JLabel("Name:");
        this.nameTF = new JTextField();
        formGrid.add(this.nameLabel);
        formGrid.add(this.nameTF);

        this.emailLabel = new JLabel("Email:");
        this.emailTF = new JTextField();
        formGrid.add(this.emailLabel);
        formGrid.add(this.emailTF);

        this.passwordLabel = new JLabel("Password:");
        this.passwordTF = new JTextField();
        formGrid.add(this.passwordLabel);
        formGrid.add(this.passwordTF);

        return formGrid;
    }
}
