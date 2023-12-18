package exception;

public class TelepassException extends Exception {

    private final TelepassError cause;

    public TelepassException(TelepassError cause) {
        super(cause.getErrorMessage());
        this.cause = cause;
    }

    public TelepassException(TelepassError cause, Throwable err) {
        super(cause.getErrorMessage(), err);
        this.cause = cause;
    }

    public TelepassError getErrorCause() {
        return cause;
    }
}
