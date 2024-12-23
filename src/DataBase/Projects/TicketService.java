package DataBase.Projects;

import DataBase.Exceptions.ExceptionObjectDuplicated;
import DataBase.Exceptions.ServiceException;

import java.util.List;

public class TicketService {
    private final TicketDAO dao;

    public TicketService(){
        this.dao = new TicketDAOH2Impl();
    }

    public List<Ticket> getList() {
        return this.dao.getList();
    }

    public void add(Ticket ticket) throws ServiceException {
        try {
            this.dao.create(ticket);
        } catch (ExceptionObjectDuplicated e) {
//            throw new ServiceException(e);
        }
    }

    public void edit(Ticket ticket) throws ServiceException {
        this.dao.update(ticket);
    }
}
