package Panels.Projects;

import DataBase.Projects.Ticket;
import Panels.MainFrame;
import Panels.PanelAbstract;

import javax.swing.*;

public class PanelTicketDetail extends PanelAbstract {
    private Ticket ticket;

    private JTextField descriptionTF;

    PanelTicketDetail(MainFrame mainFrame){
        super(mainFrame);
    }

    public void clean() {
        this.removeAll();
    }

    public void showTicket(Ticket ticket){
        clean();

        if(this.descriptionTF == null){
            this.descriptionTF = new JTextField(ticket.getDescription());
        }
        else{
            this.descriptionTF.setText(ticket.getDescription());
        }
        
        this.add(this.descriptionTF);
    }
}
