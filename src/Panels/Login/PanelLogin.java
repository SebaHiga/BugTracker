package Panels.Login;

import DataBase.Users.UserService;
import Panels.PanelAbstract;
import Panels.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelLogin extends PanelAbstract implements ActionListener {

    private PanelFormLogin formLogin;
    private PanelOkCancel panelOkCancel;

    public PanelLogin(MainFrame mainFrame) {
        super(mainFrame);
        build();
    }

    public void build() {
        this.formLogin = new PanelFormLogin();
        this.panelOkCancel = new PanelOkCancel();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(this.formLogin);
        this.add(this.panelOkCancel);

        this.panelOkCancel.getOkBtn().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.panelOkCancel.getOkBtn()) {
            UserService service = new UserService();
            String user = this.formLogin.getUsernameTxt().getText();
            String pass = this.formLogin.getPasswordTxt().getText();

            if (service.verifyUserIdentity(user, pass)) {
                if (service.verifyUserPrivileges(user)) {
                    this.mainFrame.displayPanelAdmin();
                } else {
                    this.mainFrame.displayPanelProjectsUser();
                }
            }
        }
    }
}
