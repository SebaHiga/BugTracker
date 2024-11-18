package DataBase.Users;

import DataBase.Exceptions.ExceptionObjectDuplicated;

public class UserDAOExceptionUserDuplicated extends ExceptionObjectDuplicated {
    public UserDAOExceptionUserDuplicated(User user) {
        super("User " + user.getName() + " already exists!");
    }
}
