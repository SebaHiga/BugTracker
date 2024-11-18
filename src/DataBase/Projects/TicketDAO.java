package DataBase.Projects;

import DataBase.Exceptions.DAOException;
import DataBase.Exceptions.ExceptionObjectDuplicated;

import java.util.List;

public interface TicketDAO {
    void create(Ticket ticket) throws ExceptionObjectDuplicated;

    void delete(java.lang.String internalId) throws DAOException;

    void update(Ticket ticket);

    Ticket getTicketById(java.lang.String internalId);

    List<Ticket> getList();
}
