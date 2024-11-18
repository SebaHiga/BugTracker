package DataBase.Users;

import DataBase.Exceptions.DAOException;
import DataBase.Exceptions.H2ExceptionRollback;

import java.util.List;

public interface UserDAO {

    void create(User user) throws DAOException;

    void delete(User user) throws DAOException;

    void update(User user) throws DAOException;

    User getUserByName(String username) throws  DAOException;

    List<User> getList();

}
