package DataBase.Projects;

import DataBase.DAOException;
import DataBase.ExceptionObjectDuplicated;

import java.util.List;

public interface TicketDAO {
    void create(Ticket ticket) throws ExceptionObjectDuplicated;

    void delete(String internalId) throws DAOException;

    void update(Ticket ticket);

    Ticket getTicketById(String internalId);

    List<Ticket> getList();
}
