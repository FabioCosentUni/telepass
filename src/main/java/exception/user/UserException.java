package exception.user;

public class UserException extends Exception {

    private final UserError cause;

    public UserException(UserError cause) {
        super(cause.getErrorMessage());
        this.cause = cause;
    }

    public UserError getErrorCause() {
        return cause;
    }
}
