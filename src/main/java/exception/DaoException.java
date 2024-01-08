package exception;

/**
 * Eccezione personalizzata per gestire errori nel livello di accesso ai dati (DAO).
 */
public class DaoException extends Exception {

    /**
     * Costruttore con un messaggio specifico per l'eccezione.
     *
     * @param message Il messaggio associato all'eccezione.
     */
    public DaoException(String message) {
        super(message);
    }

    /**
     * Costruttore con un messaggio specifico e una causa per l'eccezione.
     *
     * @param message Il messaggio associato all'eccezione.
     * @param cause La causa dell'eccezione.
     */
    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
