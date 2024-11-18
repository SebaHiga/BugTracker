package DataBase.Users;

import DataBase.Exceptions.ExceptionObjectNotFound;

public class UserDAOExceptionUserNotFound extends ExceptionObjectNotFound {
    public UserDAOExceptionUserNotFound(User user) {
        super("User " + user.getName() + " was not found!");
    }
}
