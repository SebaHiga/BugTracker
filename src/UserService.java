import java.util.List;

public class UserService {
    private UserDAO dao;

    UserService(UserDAO dao){
        this.dao = dao;
    }

    public List<User> getUserList() throws ServiceException {
        return this.dao.getUserList();
    }

    public void addUser(User user) throws ServiceException {
        try {
            this.dao.userCreate(user);
        } catch (ExceptionObjectDuplicated e) {
            throw new ServiceException(e);
        }
    }

    public void editUser(User user) throws ServiceException {
        this.dao.userUpdate(user);
    }
}
