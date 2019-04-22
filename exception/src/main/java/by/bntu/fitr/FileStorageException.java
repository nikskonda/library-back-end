package by.bntu.fitr;

public class FileStorageException extends AbstractException {

    public FileStorageException() {
        super();
    }

    public FileStorageException(String message) {
        super(message);
    }

    public FileStorageException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public FileStorageException(Throwable throwable) {
        super(throwable);
    }
}
