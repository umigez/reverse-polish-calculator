package gezer.calc;

public class NotEnoughNumbersException extends Exception {
    public NotEnoughNumbersException(String message) {
        super(message);
    }

    public NotEnoughNumbersException(String message, Throwable cause) {
        super(message, cause);
    }
}
