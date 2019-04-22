package by.bntu.fitr;

public class FileNotFoundException extends AbstractException {

    public FileNotFoundException() {
        super();
    }

    public FileNotFoundException(String message) {
        super(message);
    }

    public FileNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public FileNotFoundException(Throwable throwable) {
        super(throwable);
    }
}
