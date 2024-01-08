package exception;

/**
 * Eccezione personalizzata per gestire errori nell'esecuzione dei comandi.
 * Utilizzato per gestire errori nel livello di business (Command).
 */
public class CommandExecutorException extends Exception {

    /**
     * Costruttore con un messaggio specifico per l'eccezione.
     *
     * @param message Il messaggio associato all'eccezione.
     */
    public CommandExecutorException(String message) {
        super(message);
    }

    /**
     * Costruttore con un messaggio specifico e una causa per l'eccezione.
     *
     * @param message Il messaggio associato all'eccezione.
     * @param cause La causa dell'eccezione.
     */
    public CommandExecutorException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Costruttore con una causa per l'eccezione.
     *
     * @param cause La causa dell'eccezione.
     */
    public CommandExecutorException(Throwable cause) {
        super(cause);
    }
}
