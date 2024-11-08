package Panels.Projects;

import DataBase.Projects.Ticket;
import DataBase.Projects.TicketService;
import DataBase.Users.UserService;
import Panels.MainFrame;
import Panels.PanelAbstract;
import Panels.PanelList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public abstract class PanelProjectsAbstract extends PanelAbstract {
    private PanelList<Ticket> panelTickets;
    private PanelTicketDetail panelTicketDetail;

    public PanelProjectsAbstract(MainFrame mainFrame) {
        super(mainFrame);
        build();
    }

    public void build() {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        this.panelTickets = new PanelList<Ticket>(this.mainFrame);
        this.panelTickets.build(new TicketService().getList());

        this.panelTicketDetail = new PanelTicketDetail(this.mainFrame);

        this.add(this.panelTickets);
        this.add(this.panelTicketDetail);

        this.panelTickets.getLabelList().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                inTicketSelection();
            }
        });
    }

    private void inTicketSelection() {
        var selectedTicket = this.panelTickets.getSelectedItem();
        this.panelTicketDetail.showTicket(selectedTicket);
    }
}
