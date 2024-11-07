import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelLogin extends JPanel implements ActionListener {

    private FormLogin formLogin;
    private PanelOkCancel panelOkCancel;


    public PanelLogin() {
        build();
    }
    public void build() {
        this.formLogin = new FormLogin();
        this.panelOkCancel = new PanelOkCancel();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(this.formLogin);
        this.add(this.panelOkCancel);

        this.panelOkCancel.getOkBtn().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == this.panelOkCancel.getOkBtn()) {
//            UsuarioDAO d = new UsuarioDAoImpl();
//            String user = this.loginFormPanel.getUsernameTxt().getText();
//            String pass = this.loginFormPanel.getPasswordTxt().getText();
//            User u = d.findByUserAndPass(user, pass);
//            if(u != null) {
//                System.out.println("LOGIN EXITOSO");
//            }
        }
    }
}
