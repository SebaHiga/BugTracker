package DataBase.Users;

import DataBase.DAOException;
import DataBase.ExceptionObjectDuplicated;

import java.util.List;

public interface UserDAO {

    void create(User user) throws ExceptionObjectDuplicated;

    void delete(User user) throws DAOException;

    void update(User user);

    User getUserByName(String username);

    List<User> getList();

}
