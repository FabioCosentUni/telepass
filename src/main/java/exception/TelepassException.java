package exception;

/**
 * Eccezione personalizzata per gestire gli errori specifici del sistema Telepass.
 */
public class TelepassException extends Exception {

    private final TelepassError cause;

    /**
     * Costruttore che prende come parametro un'istanza di {@link TelepassError} come causa dell'eccezione.
     *
     * @param cause L'errore di Telepass che ha causato l'eccezione.
     */
    public TelepassException(TelepassError cause) {
        super(cause.getErrorMessage());
        this.cause = cause;
    }

    /**
     * Costruttore che prende come parametro un'istanza di {@link TelepassError} come causa dell'eccezione e una causa radice.
     *
     * @param cause L'errore di Telepass che ha causato l'eccezione.
     * @param err   La causa radice dell'eccezione.
     */
    public TelepassException(TelepassError cause, Throwable err) {
        super(cause.getErrorMessage(), err);
        this.cause = cause;
    }

    /**
     * Restituisce l'errore di Telepass che ha causato l'eccezione.
     *
     * @return L'errore di Telepass.
     */
    public TelepassError getErrorCause() {
        return cause;
    }
}
