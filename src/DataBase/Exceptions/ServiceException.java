package DataBase.Exceptions;

public class ServiceException extends Exception {
    public ServiceException(Exception e) {
        super(e);
    }

    public ServiceException(String s) {
        super(s);
    }
}
