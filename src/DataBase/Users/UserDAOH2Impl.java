package DataBase.Users;

import DataBase.Exceptions.DAOException;
import DataBase.DBManager;
import DataBase.Exceptions.H2ExceptionRollback;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UserDAOH2Impl implements UserDAO {
    private static final Logger LOGGER = Logger.getLogger(UserDAOH2Impl.class.getName());

    public void create(User user) throws DAOException {
        String name = user.getName();
        String email = user.getEmail();
        String pass = user.getPass();

        Connection connection = DBManager.connect();
        String sql = "INSERT INTO users (name, email, pass) VALUES ('" + name + "', '" + email + "', '" + pass + "')";

        try {
            executeSQLStatement(connection, user, sql);
        } catch (SQLException e) {
            if (e.getErrorCode() == 23505) {
                throw new UserDAOExceptionUserDuplicated(user);
            }
            // something else went wrong
            throw new DAOException("Could not add user");
        } catch (H2ExceptionRollback e) {
            LOGGER.severe("Error on rollback");
        }
    }

    public void delete(User user) throws DAOException {
        String sql = "DELETE FROM users WHERE name = '" + user.getName() + "'";
        Connection connection = DBManager.connect();

        try {
            var affectedRows = executeSQLStatement(connection, user, sql);

            if (affectedRows == 0) {
                throw new UserDAOExceptionUserNotFound(user);
            }
        } catch (SQLException e) {
            throw new DAOException("Could not delete user");
        } catch (H2ExceptionRollback e) {
            LOGGER.severe("Error on rollback");
        }
    }

    // Updates user's email and pass
    public void update(User user) throws DAOException {
        String name = user.getName();
        String email = user.getEmail();
        String pass = user.getPass();

        String sql = "UPDATE users set email = '" + email + "', pass = '" + pass + "' WHERE name = '" + name + "'";
        Connection connection = DBManager.connect();

        try {
            var affectedRows = executeSQLStatement(connection, user, sql);

            if (affectedRows == 0) {
                throw new UserDAOExceptionUserNotFound(user);
            }
        } catch (SQLException e) {
            throw new DAOException("Something went wrong then updating user");
        } catch (H2ExceptionRollback e) {
            LOGGER.severe("Error on rollback");
        }
    }

    public List<User> getList() {
        List<User> ret = new ArrayList<>();
        String sql = "SELECT * FROM users";
        Connection connection = DBManager.connect();

        try {
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String mail = rs.getString("email");
                String pass = rs.getString("pass");
                User u = new User(name, mail, pass);
                ret.add(u);
            }
        } catch (SQLException sqlException) {
            try {
                rollbackDatabase(connection);
            } catch (H2ExceptionRollback e) {
                LOGGER.severe("Error on rollback");
            }
        } finally {
            closeConnection(connection);
        }
        return ret;
    }

    public User getUserByName(String name) {
        String sql = "SELECT * FROM users WHERE name = '" + name + "'";
        Connection connection = DBManager.connect();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String userName = resultSet.getString("name");
                String mail = resultSet.getString("email");
                String pass = resultSet.getString("pass");
                return new User(userName, mail, pass);
            }
        } catch (SQLException sqlException) {
            try {
                rollbackDatabase(connection);
            } catch (H2ExceptionRollback e) {
                LOGGER.severe("Error on rollback");
            }
        } finally {
            closeConnection(connection);
        }

        return null;
    }

    private int executeSQLStatement(Connection connection, User user, String sqlStatement) throws SQLException, H2ExceptionRollback {
        int affectedRows = 0;

        try {
            Statement s = connection.createStatement();
            affectedRows = s.executeUpdate(sqlStatement);
            connection.commit();
        } catch (SQLException sqlException) {
            rollbackDatabase(connection);
            throw sqlException;
        } finally {
            closeConnection(connection);
        }

        return affectedRows;
    }

    private void rollbackDatabase(Connection connection) throws H2ExceptionRollback {
        try {
            connection.rollback();
        } catch (SQLException sqlException1) {
            throw new H2ExceptionRollback();
        }
    }

    private void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException sqlException) {
            LOGGER.severe("Error when closing H2 connection");
        }
    }
}
