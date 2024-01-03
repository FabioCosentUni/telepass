package exception;

public class CommandExecutorException extends Exception {
    public CommandExecutorException(String message) {
        super(message);
    }

    public CommandExecutorException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandExecutorException(Throwable cause) {
        super(cause);
    }
}