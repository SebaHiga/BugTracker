package Panels.Projects;

import Panels.MainFrame;
import Panels.PanelAbstract;

import javax.swing.*;

public abstract class PanelProjectsAbstract extends PanelAbstract {

    public PanelProjectsAbstract(MainFrame mainFrame) {
        super(mainFrame);
        build();
    }

    public void build() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }
}
