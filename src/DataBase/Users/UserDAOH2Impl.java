package DataBase.Users;

import DataBase.DAOException;
import DataBase.DBManager;
import DataBase.ExceptionObjectDuplicated;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOH2Impl implements UserDAO {

    public void userCreate(User user) throws ExceptionObjectDuplicated {
        String name = user.getName();
        String email = user.getEmail();
        String pass = user.getPass();

        Connection connection = DBManager.connect();
        try {
            Statement s = connection.createStatement();
            String sql = "INSERT INTO users (name, email, pass) VALUES ('" + name + "', '" + email + "', '" + pass + "')";
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

    public void userDelete(String userName) throws DAOException {
        String sql = "DELETE FROM users WHERE name = '" + userName + "'";
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
    public void userUpdate(User user) {
        String name = user.getName();
        String email = user.getEmail();
        String pass = user.getPass();

        String sql = "UPDATE users set email = '" + email + "', pass = '" + pass + "' WHERE name = '" + name + "'";
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

    public List<User> getUserList() {
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
                return  new User(userName, mail, pass);
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
