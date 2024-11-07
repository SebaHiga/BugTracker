package DataBase.Users;

import DataBase.DAOException;
import DataBase.ExceptionObjectDuplicated;

import java.util.List;

public interface UserDAO {

    void userCreate(User user) throws ExceptionObjectDuplicated;

    void userDelete(String username) throws DAOException;

    void userUpdate(User user);

    User getUserByName(String username);

    List<User> getUserList();

}
