package command;

import exception.CommandExecutorException;
import model.BusinessObject;

/**
 * Classe che funge da esecutore per le operazioni specifiche delle autostrade.
 * Questa classe fornisce un metodo statico per eseguire un comando specifico,
 * passando un oggetto di input e restituendo un oggetto di output.
 */
public class AutostradeExecutor {

    /**
     * Esegue un comando specifico per le autostrade, fornendo un oggetto di input
     * e restituendo un oggetto di output.
     *
     * @param command Oggetto di tipo {@link AutostradeCommand} da eseguire.
     * @param input   Oggetto di tipo {@link BusinessObject} che rappresenta l'input per il comando.
     * @return Oggetto di tipo {@link BusinessObject} che rappresenta l'output del comando eseguito.
     * @throws CommandExecutorException se si verifica un'eccezione durante l'esecuzione del comando.
     */
    public static BusinessObject execute(AutostradeCommand command, BusinessObject input) throws CommandExecutorException {
        return command.execute(input);
    }
}
