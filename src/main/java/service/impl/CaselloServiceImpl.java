package service.impl;

import command.AutostradeExecutor;
import command.impl.GetAutostradeCommandExecutorImpl;
import dao.CaselloDAO;
import dao.impl.AutostradaDAOImpl;
import exception.TelepassError;
import exception.TelepassException;
import model.Casello;
import model.bo.GetAutostradeOutputBO;
import service.CaselloService;

import java.util.Collections;
import java.util.List;

/**
 * Implementazione del servizio CaselloService per la gestione dei caselli nel sistema Telepass.
 */
public class CaselloServiceImpl implements CaselloService {

    private final CaselloDAO caselloDAO;

    /**
     * Costruttore che inizializza l'implementazione con un'istanza di CaselloDAO.
     *
     * @param caselloDAO DAO per la gestione dei caselli.
     */
    public CaselloServiceImpl(CaselloDAO caselloDAO) {
        this.caselloDAO = caselloDAO;
    }

    /**
     * Restituisce la lista di tutti i caselli nel sistema.
     *
     * @return La lista di tutti i caselli, o una lista vuota se si verifica un errore.
     */
    @Override
    public List<Casello> getAllCaselli() {
        try {
            return caselloDAO.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * Ottiene un elenco di tutte le autostrade disponibili nel sistema.
     *
     * @return Un oggetto GetAutostradeOutputBO contenente le informazioni sulle autostrade.
     * @throws TelepassException se si verifica un errore durante il recupero delle autostrade.
     */
    @Override
    public GetAutostradeOutputBO getAllAutostrade() throws TelepassException {
        try {
            //Command Pattern
            return (GetAutostradeOutputBO) AutostradeExecutor.execute(new GetAutostradeCommandExecutorImpl(new AutostradaDAOImpl()), null);
        } catch (Exception e) {
            throw new TelepassException(TelepassError.GENERIC_ERROR, e);
        }
    }
}
