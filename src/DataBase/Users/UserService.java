package DataBase.Users;

import DataBase.Exceptions.DAOException;
import DataBase.Exceptions.ServiceException;

import java.util.List;
import java.util.logging.Logger;

public class UserService {
    private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());
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
        } catch (DAOException e) {
            LOGGER.warning(e.toString());
            throw new ServiceException(e); // placeholder
        }
    }

    public void delete(User user) throws ServiceException {
        try {
            this.dao.delete(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public void update(User user) throws ServiceException {
        try {
            this.dao.update(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public boolean verifyUserIdentity(String userName, String userPass) throws ServiceException {
        User user = null;
        try {
            user = this.dao.getUserByName(userName);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        if (user == null) {
            return false;
        }

        return user.getName().equals(userName) && user.getPass().equals(userPass);
    }

    public boolean verifyUserPrivileges(String userName) {
        return userName.equals("admin");
    }

    public boolean verifyUserPrivileges(User user) {
        return this.verifyUserPrivileges(user.getName());
    }
}
