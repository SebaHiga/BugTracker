import java.util.List;

public interface UserDAO {

    void userCreate(User unUser) throws ExceptionObjectDuplicated;

    void userDelete(String username) throws DAOException;

    void userUpdate(User unUser);

    User getUserByName(String username);

    List<User> getUserList();

}
