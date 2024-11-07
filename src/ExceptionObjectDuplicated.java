public class ExceptionObjectDuplicated extends Exception {
    public ExceptionObjectDuplicated() {
    }

    public ExceptionObjectDuplicated(String message) {
        super(message);
    }

    public ExceptionObjectDuplicated(String message, Throwable cause) {
        super(message, cause);
    }

    public ExceptionObjectDuplicated(Throwable cause) {
        super(cause);
    }
}
