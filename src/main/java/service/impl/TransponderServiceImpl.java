package service.impl;

import dao.TransponderDAO;
import exception.DaoException;
import exception.TelepassError;
import exception.TelepassException;
import model.Transponder;
import service.TransponderService;

import java.util.List;

/**
 * Implementazione del servizio TransponderService per la gestione dei transponder nel sistema Telepass.
 */
public class TransponderServiceImpl implements TransponderService {

    private final TransponderDAO dao;

    /**
     * Costruttore che inizializza l'implementazione con un'istanza di TransponderDAO.
     *
     * @param dao DAO per la gestione dei transponder su un DB.
     */
    public TransponderServiceImpl(TransponderDAO dao) {
        this.dao = dao;
    }

    /**
     * Inserisce un nuovo transponder nel sistema.
     *
     * @param transponder Il transponder da inserire.
     * @throws TelepassException se il transponder è già registrato o si verifica un errore durante l'inserimento.
     */
    @Override
    public void insert(Transponder transponder) throws TelepassException {
        try {
            Transponder existingTransponder = dao.findById(transponder.getCodiceTransponder());

            if (existingTransponder != null) {
                throw new TelepassException(TelepassError.TELEPASS_ALREADY_REGISTERED);
            }

            dao.save(transponder);

        } catch (DaoException e) {
            throw new TelepassException(TelepassError.GENERIC_ERROR, e);
        }
    }

    /**
     * Ottiene la lista dei transponder attivi nel sistema.
     *
     * @return La lista dei transponder attivi.
     * @throws TelepassException se si verifica un errore durante il recupero dei transponder attivi.
     */
    @Override
    public List<Transponder> getActiveTransponders() throws TelepassException {
        try {
            //restituisce i trasponder che sono associati ad un utente
            return dao.getActiveTransponders();
        } catch (DaoException e) {
            throw new TelepassException(TelepassError.GENERIC_ERROR, e);
        }
    }

    /**
     * Innalaza il livello di contratto di un transponder. ( "Plus" )
     *
     * @param transponder Il transponder per cui si vuole innalzare il livello di contratto.
     * @throws TelepassException se si verifica un errore durante l'aggiornamento del transponder.
     */
    @Override
    public void makePlus(Transponder transponder) throws TelepassException {
        try {
            transponder.setPlus(1);

            /*
             si utilizza il metodo merge di hibernate in quanto il transponder che arriva in input è un'entità nello stato transient,
             non gestita da una sessione hibernate. Il metodo update darebbe un errore.
             */

            dao.merge(transponder);
        } catch (DaoException e) {
            throw new TelepassException(TelepassError.GENERIC_ERROR, e);
        }
    }

    /**
     * Revoca un transponder associato a un utente.
     *
     * @param transponderCode Il codice del transponder da revocare.
     * @throws TelepassException se il transponder non è trovato o si verifica un errore durante la revoca.
     */
    @Override
    public void revokeTransponder(String transponderCode) throws TelepassException {
        try {
            Transponder transponder = dao.findById(transponderCode);
            if (transponder == null) {
                throw new TelepassException(TelepassError.TELEPASS_NOT_FOUND);
            }

            //si revoca il transponder settando a 0 il campo plus e settando a null l'utente associato
            transponder.setPlus(0);
            transponder.setUtente(null);

            transponder.getVeicoloList().forEach(v -> v.setTransponderDTO(null));

            dao.merge(transponder);

        } catch (DaoException e) {
            throw new TelepassException(TelepassError.GENERIC_ERROR, e);
        }
    }
}
