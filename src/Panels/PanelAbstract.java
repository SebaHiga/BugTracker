package Panels;

import javax.swing.*;

public abstract class PanelAbstract extends JPanel {

    protected MainFrame mainFrame;

    public PanelAbstract(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }
}
