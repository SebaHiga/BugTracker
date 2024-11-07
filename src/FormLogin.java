import javax.swing.*;
import java.awt.*;

public class FormLogin extends JPanel {

    private JLabel usernameLbl;
    private JLabel passwordLbl;
    private JTextField usernameTxt;
    private JTextField passwordTxt;

    public FormLogin() {
        build();
    }

    public void build() {
        this.setLayout(new GridLayout(2,2));

        this.usernameLbl = new JLabel("Username");
        this.passwordLbl = new JLabel("Password");
        this.usernameTxt = new JTextField();
        this.passwordTxt = new JTextField();

        this.add(usernameLbl);
        this.add(usernameTxt);
        this.add(passwordLbl);
        this.add(passwordTxt);
    }

    public JLabel getUsernameLbl() {
        return usernameLbl;
    }

    public void setUsernameLbl(JLabel usernameLbl) {
        this.usernameLbl = usernameLbl;
    }

    public JLabel getPasswordLbl() {
        return passwordLbl;
    }

    public void setPasswordLbl(JLabel passwordLbl) {
        this.passwordLbl = passwordLbl;
    }

    public JTextField getUsernameTxt() {
        return usernameTxt;
    }

    public void setUsernameTxt(JTextField usernameTxt) {
        this.usernameTxt = usernameTxt;
    }

    public JTextField getPasswordTxt() {
        return passwordTxt;
    }

    public void setPasswordTxt(JTextField passwordTxt) {
        this.passwordTxt = passwordTxt;
    }
}
