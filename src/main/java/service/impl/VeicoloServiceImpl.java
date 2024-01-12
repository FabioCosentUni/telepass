package service.impl;

import dao.VeicoloDAO;
import exception.DaoException;
import exception.TelepassError;
import exception.TelepassException;
import model.Utente;
import model.Veicolo;
import service.VeicoloService;

import java.util.List;

/**
 * Implementazione del servizio VeicoloService per la gestione dei veicoli nel sistema Telepass.
 */
public class VeicoloServiceImpl implements VeicoloService {

    private final VeicoloDAO veicoloDAO;

    /**
     * Costruttore che inizializza l'implementazione con un'istanza di VeicoloDAO.
     *
     * @param veicoloDAO DAO per la gestione dei veicoli.
     */
    public VeicoloServiceImpl(VeicoloDAO veicoloDAO) {
        this.veicoloDAO = veicoloDAO;
    }

    /**
     * Verifica se un veicolo è già registrato nel sistema Telepass.
     *
     * @param veicolo Il veicolo da verificare.
     * @throws TelepassException se il veicolo è già registrato o se si verifica un errore durante la verifica.
     */
    @Override
    public void validateVeicolo(Veicolo veicolo) throws TelepassException {
        try {

            if (veicoloDAO.findById(veicolo.getTargaPk()) != null)
                throw new TelepassException(TelepassError.VEHICLE_ALREADY_REGISTERED);

        } catch (DaoException e) {
            throw new TelepassException(TelepassError.GENERIC_ERROR, e);
        }
    }

    /**
     * Ottiene la lista dei veicoli associati a un utente.
     *
     * @param u L'utente di cui ottenere i veicoli associati.
     * @return La lista dei veicoli associati all'utente.
     * @throws TelepassException se si verifica un errore durante l'ottenimento dei veicoli utente.
     */
    @Override
    public List<Veicolo> getVeicoliUtente(Utente u) throws TelepassException {
        try {
            return veicoloDAO.getVeicoliUtente(u);
        } catch (DaoException e) {
            throw new TelepassException(TelepassError.GENERIC_ERROR, e);
        }
    }
}
