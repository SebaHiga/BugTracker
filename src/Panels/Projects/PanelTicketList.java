package Panels.Projects;

import DataBase.Projects.Ticket;
import DataBase.Projects.TicketService;
import Panels.MainFrame;
import Panels.PanelAbstract;

import javax.swing.*;
import java.util.List;
import java.util.stream.Collectors;

public class PanelTicketList extends PanelAbstract {
    private JList<String> ticketList;
    private List<Ticket> tickets;

    PanelTicketList(MainFrame mainFrame){
        super(mainFrame);
        build();
    }

    private void build() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        var ticketService = new TicketService();

        var defList = new DefaultListModel<String>();

        this.tickets = ticketService.getList();

        for(var t : this.tickets) {
            defList.addElement(t.getInternalId());
        }

        this.ticketList = new JList<String>(defList);

        this.add(this.ticketList);
    }

    public JList<String> getTicketList(){
        return this.ticketList;
    }

    public Ticket getSelectedTicket(){
        var selectedItem = this.ticketList.getSelectedValue();

        var selected = this.tickets.stream().filter(ticket -> ticket.getInternalId().equals(selectedItem)).toList();

        return selected.getFirst();
    }
}
