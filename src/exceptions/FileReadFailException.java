package exceptions;

public class FileReadFailException extends RuntimeException {
    public FileReadFailException(String message) {
        super(message);
    }
}
