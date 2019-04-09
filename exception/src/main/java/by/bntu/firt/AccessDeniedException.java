package by.bntu.firt;

public class AccessDeniedException extends AbstractException {

    public AccessDeniedException() {
        super();
    }

    public AccessDeniedException(String message) {
        super(message);
    }

    public AccessDeniedException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public AccessDeniedException(Throwable throwable) {
        super(throwable);
    }
}
