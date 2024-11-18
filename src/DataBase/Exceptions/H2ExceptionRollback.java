package DataBase.Exceptions;

public class H2ExceptionRollback extends DAOException {
    public H2ExceptionRollback() {
        super("Could not rollback database");
    }
}
