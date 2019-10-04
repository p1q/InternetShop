package internetshop.exceptions;

public class PasswordsDontMatchException extends Exception {
    public PasswordsDontMatchException(String message) {
        super(message);
    }
}
