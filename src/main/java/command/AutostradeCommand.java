package command;

import exception.CommandExecutorException;
import model.BusinessObject;

/**
 * Interfaccia rappresentante il pattern Command per l'esecuzione di operazioni su oggetti di business.
 */
public interface AutostradeCommand {

    /**
     * Esegue un operazione specifica sull'oggetto di business in input.
     *
     * @param input L'oggetto di business su cui eseguire l'operazione.
     * @return L'oggetto di business risultante dall'esecuzione del comando.
     * @throws CommandExecutorException se si verifica un errore durante l'esecuzione del comando.
     */
    BusinessObject execute(BusinessObject input) throws CommandExecutorException;

}
