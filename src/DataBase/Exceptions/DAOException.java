package DataBase.Exceptions;

public class DAOException extends Exception {
    public DAOException(Exception e) {
        super(e);
    }

    public DAOException(String s) {
        super(s);
    }
}
