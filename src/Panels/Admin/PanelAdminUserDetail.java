package Panels.Admin;

import DataBase.ServiceException;
import DataBase.Users.User;
import DataBase.Users.UserService;
import Panels.PanelOkCancel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelAdminUserDetail extends JPanel {
    protected PanelAdminUsersView panelParent;

    private JLabel nameLabel;
    private JLabel emailLabel;
    private JLabel passwordLabel;

    protected JTextField nameTF;
    protected JTextField emailTF;
    protected JTextField passwordTF;

    protected PanelOkCancel panelOkCancel;

    public PanelAdminUserDetail(PanelAdminUsersView parentPanel) {
        this.panelParent = parentPanel;

        build();
    }

    private void build() {
        this.panelOkCancel = new PanelOkCancel();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(getFormPane());
        this.add(panelOkCancel);

    }

    public void populateWithUser(User user) {
        this.nameTF.setText(user.getName());
        this.emailTF.setText(user.getEmail());
        this.passwordTF.setText(user.getPass());
    }

    public void cleanTF(){
        this.populateWithUser(new User("", "", ""));
    }

    public void setOnEditMode(boolean editable) {
        this.nameTF.setEditable(editable);
        this.emailTF.setEditable(editable);
        this.passwordTF.setEditable(editable);
        this.panelOkCancel.setVisible(editable);

        if (!editable){
            setTFDisabled(this.nameTF);
            setTFDisabled(this.emailTF);
            setTFDisabled(this.passwordTF);
        }
    }

    private void setTFDisabled(JTextField tf){
        tf.setBackground(new Color(240, 240, 240));
        tf.setForeground(Color.gray);
    }

    JPanel getFormPane() {
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
