package Panels.Projects;

import DataBase.Projects.Ticket;
import DataBase.Projects.TicketService;
import Panels.MainFrame;
import Panels.PanelAbstract;

import javax.swing.*;

public abstract class PanelProjectsAbstract extends PanelAbstract {
    private JList ticketList;

    public PanelProjectsAbstract(MainFrame mainFrame) {
        super(mainFrame);
        build();
    }


    public void build() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.ticketList = new JList();

        var ticketService = new TicketService();

        var tickets = ticketService.getList();

        var defList = new DefaultListModel<String>();

        for(var t : tickets) {
            defList.addElement(t.getInternalId());
        }

        this.ticketList = new JList(defList);

        this.add(this.ticketList);
    }
}
