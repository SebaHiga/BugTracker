package DataBase.Users;

import DataBase.ExceptionObjectDuplicated;
import DataBase.ServiceException;

import java.util.List;

public class UserService {
    private final UserDAO dao;

    public UserService(){
        this.dao = new UserDAOH2Impl();
    }

    public List<User> getList() throws ServiceException {
        return this.dao.getList();
    }

    public void add(User user) throws ServiceException {
        try {
            this.dao.create(user);
        } catch (ExceptionObjectDuplicated e) {
            throw new ServiceException(e);
        }
    }

    public void edit(User user) throws ServiceException {
        this.dao.update(user);
    }

    public boolean verifyUserIdentity(String userName, String userPass){
        var user = this.dao.getUserByName(userName);

        if (user == null){
            return false;
        }

        return user.getName().equals(userName) && user.getPass().equals(userPass);
    }
}
