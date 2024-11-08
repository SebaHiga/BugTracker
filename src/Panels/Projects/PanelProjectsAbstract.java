package Panels.Projects;

import DataBase.Projects.Ticket;
import Panels.MainFrame;
import Panels.PanelAbstract;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class PanelProjectsAbstract extends PanelAbstract {
    private PanelTicketList panelTickets;
    private PanelTicketDetail panelTicketDetail;

    public PanelProjectsAbstract(MainFrame mainFrame) {
        super(mainFrame);
        build();
    }

    public void build() {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        this.panelTickets = new PanelTicketList(this.mainFrame);
        this.panelTicketDetail = new PanelTicketDetail(this.mainFrame);

        this.add(this.panelTickets);
        this.add(this.panelTicketDetail);

        this.panelTickets.getTicketList().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                inTicketSelection();
            }
        });
    }

    private void inTicketSelection(){
        var selectedTicket = this.panelTickets.getSelectedTicket();
        this.panelTicketDetail.showTicket(selectedTicket);
    }
}
