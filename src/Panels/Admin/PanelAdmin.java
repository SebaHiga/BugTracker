package Panels.Admin;

import Panels.MainFrame;
import Panels.PanelAbstract;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PanelAdmin extends PanelAbstract {
    private PanelAdminViewSelector panelViewSelector;
    private PanelAdminUsersView panelUserView;

    public PanelAdmin(MainFrame mainFrame) {
        super(mainFrame);
        build();
    }

    private void build() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.panelUserView = new PanelAdminUsersView();

        this.panelViewSelector = new PanelAdminViewSelector();

        this.add(this.panelViewSelector);
        this.add(this.panelUserView);

        this.panelUserView.getPanelList().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                inTicketSelection();
            }
        });
    }

    private void inTicketSelection() {
        var selectedTicket = this.panelUserView.getPanel().getSelectedItem();
    }
}
