package DataBase.Projects;

import DataBase.DAOException;
import DataBase.DBManager;
import DataBase.ExceptionObjectDuplicated;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TicketDAOH2Impl implements TicketDAO{
    public void create(Ticket ticket) throws ExceptionObjectDuplicated {
        Connection connection = DBManager.connect();
        try {
            Statement s = connection.createStatement();
            String sql = "INSERT INTO tickets (internal_id, description) VALUES ('" + ticket.getInternalId() + "', '" + ticket.getDescription() + "')";
            s.executeUpdate(sql);
            connection.commit();
        } catch (SQLException sqlException) {
            try {
                connection.rollback();
                sqlException.printStackTrace();
                if(sqlException.getErrorCode() == 23505) {
                    throw new ExceptionObjectDuplicated();
                }
            } catch (SQLException sqlException1) {
                sqlException1.printStackTrace();
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }

    public void delete(String internalId) throws DAOException {
        String sql = "DELETE FROM tickets WHERE internal_id = '" + internalId + "'";
        Connection connection = DBManager.connect();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.commit();
        } catch (SQLException sqlException) {
            try {
                connection.rollback();
            } catch (SQLException sqlException1) {
                // TODO: Handle when rollback fails
            }
            throw new DAOException();
        } finally {
            try {
                connection.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }

    // Updates user's email and pass
    public void update(Ticket ticket) {
        String sql = "UPDATE ticket set description = '" + ticket.getDescription() + "' WHERE internal_id = '" + ticket.getInternalId() + "'";
        Connection connection = DBManager.connect();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.commit();
        } catch (SQLException sqlException) {
            try {
                connection.rollback();
                sqlException.printStackTrace();
            } catch (SQLException sqlException1) {
                sqlException1.printStackTrace();
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }

    public List<Ticket> getList() {
        List<Ticket> ret = new ArrayList<>();
        String sql = "SELECT * FROM tickets";
        Connection connection = DBManager.connect();

        // TODO: Create new connection with safe statement creation
        try {
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                Ticket t = new Ticket(rs.getString("internal_id"), rs.getString("description"));
                ret.add(t);

            }
        } catch (SQLException sqlException) {
            try {
                connection.rollback();
            } catch (SQLException sqlException1) {
                sqlException1.printStackTrace();
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
        return ret;
    }

    public Ticket getTicketById(String internalId) {
        String sql = "SELECT * FROM tickets WHERE internal_id = '" + internalId + "'";
        Connection connection = DBManager.connect();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {
                return new Ticket(rs.getString("internal_id"), rs.getString("description"));
            }

        } catch (SQLException sqlException) {
            try {
                connection.rollback();
                sqlException.printStackTrace();
            } catch (SQLException sqlException1) {
                sqlException1.printStackTrace();
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }

        return null;
    }
}
