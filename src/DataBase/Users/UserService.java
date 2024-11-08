package DataBase.Users;

import DataBase.DAOException;
import DataBase.ExceptionObjectDuplicated;
import DataBase.ServiceException;

import java.util.List;

public class UserService {
    private final UserDAO dao;

    public UserService() {
        this.dao = new UserDAOH2Impl();
    }

    public List<User> getList() {
        var list = this.dao.getList();

        var ret = list.stream().filter(user -> !this.verifyUserPrivileges(user)).toList();
        return ret;
    }

    public void add(User user) throws ServiceException {
        try {
            this.dao.create(user);
        } catch (ExceptionObjectDuplicated e) {
            throw new ServiceException(e);
        }
    }

    public void delete(User user) throws ServiceException {
        try {
            this.dao.delete(user);
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(User user) throws ServiceException {
        this.dao.update(user);
    }

    public boolean verifyUserIdentity(String userName, String userPass) {
        var user = this.dao.getUserByName(userName);

        if (user == null) {
            return false;
        }

        return user.getName().equals(userName) && user.getPass().equals(userPass);
    }

    public boolean verifyUserPrivileges(String userName) {
        return userName.equals("admin");
    }

    public boolean verifyUserPrivileges(User user) {
        return user.getName().equals("admin");
    }
}
