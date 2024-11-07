import java.util.List;

public class UserService {

    public List<User> getUserList() throws ServiceException {
        UserDAO userDAOH = new UserDAOH2Impl();
        return userDAOH.getUserList();
    }

    public void addUser(User user) throws ServiceException {
        UserDAO userDAO = new UserDAOH2Impl();
        try {
            userDAO.userCreate(user);
        } catch (ExceptionObjectDuplicated e) {
            throw new ServiceException(e);
        }
    }

    public void editUser(User u) throws ServiceException {
        UserDAO d = new UserDAOH2Impl();
        d.userUpdate(u);
    }
}
