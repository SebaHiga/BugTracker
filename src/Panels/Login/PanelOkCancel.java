package Panels.Login;

import javax.swing.*;

public class PanelOkCancel extends JPanel {

    private JButton okBtn;
    private JButton cancelBtn;

    public PanelOkCancel() {
        build();
    }

    private void build() {
        this.okBtn = new JButton("ACCEPT");
        this.cancelBtn = new JButton("CANCEL");
        this.add(okBtn);
        this.add(cancelBtn);
    }

    public JButton getOkBtn() {
        return okBtn;
    }

    public void setOkBtn(JButton okBtn) {
        this.okBtn = okBtn;
    }

    public JButton getCancelBtn() {
        return cancelBtn;
    }

    public void setCancelBtn(JButton cancelBtn) {
        this.cancelBtn = cancelBtn;
    }
}