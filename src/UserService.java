import java.util.List;

public class UserService {
    private UserDAO dao;

    UserService(){
        this.dao = new UserDAOH2Impl();
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

    public boolean verifyUserIdentity(String userName, String userPass){
        var user = this.dao.getUserByName(userName);

        if (user == null){
            return false;
        }

        return user.getName().equals(userName) && user.getPass().equals(userPass);
    }
}
