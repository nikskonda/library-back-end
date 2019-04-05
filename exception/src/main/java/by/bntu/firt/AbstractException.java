package by.bntu.firt;

public class AbstractException extends RuntimeException {

    public AbstractException() {
        super();
    }

    public AbstractException(String message) {
        super(message);
    }

    public AbstractException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public AbstractException(Throwable throwable) {
        super(throwable);
    }
}
