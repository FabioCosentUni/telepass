package service.impl;

import dao.TransponderDAO;
import dao.UtenteDAO;
import exception.DaoException;
import exception.TelepassError;
import exception.TelepassException;
import model.Transponder;
import model.Utente;
import model.Veicolo;
import service.UtenteService;

import java.util.List;

/**
 * Implementazione del servizio UtenteService per la gestione degli utenti nel sistema Telepass.
 */
public class UtenteServiceImpl implements UtenteService {

    private static final int FIRST_INDEX = 0;
    private final UtenteDAO utenteDAO;
    private final TransponderDAO transponderDAO;

    /**
     * Costruttore che inizializza l'implementazione con istanze di UtenteDAO e TransponderDAO.
     *
     * @param utenteDAO DAO per la gestione degli utenti.
     * @param transponderDAO DAO per la gestione dei transponder.
     */
    public UtenteServiceImpl(UtenteDAO utenteDAO, TransponderDAO transponderDAO) {
        this.utenteDAO = utenteDAO;
        this.transponderDAO = transponderDAO;
    }

    /**
     * Effettua il login dell'utente nel sistema Telepass.
     *
     * @param cf       Il codice fiscale dell'utente.
     * @param password La password dell'utente.
     * @return L'utente autenticato.
     * @throws TelepassException se il codice fiscale o la password sono errati o si verifica un errore durante il login.
     */
    @Override
    public Utente login(String cf, String password) throws TelepassException {
        Utente u;

        try {
            u = utenteDAO.findById(cf);

            if (u == null) {
                throw new TelepassException(TelepassError.INCORRECT_CF);
            }

            if (!u.getPassword().equals(password)) {
                throw new TelepassException(TelepassError.INCORRECT_PASSWORD);
            }
        } catch (DaoException e) {
            throw new TelepassException(TelepassError.GENERIC_ERROR, e);
        }

        System.out.println("Utente recuperato: " + u.getCodiceFiscalePk());
        return u;
    }

    /**
     * Registra un nuovo utente nel sistema Telepass e gli associa un transponder e un veicolo.
     *
     * @param utente L'utente da registrare.
     * @param v      Il veicolo associato all'utente.
     * @throws TelepassException se il veicolo o l'utente sono null, se non ci sono transponder disponibili,
     *                           se l'opzione di pagamento non è trovata o se si verifica un errore durante la registrazione.
     */
    @Override
    public void register(Utente utente, Veicolo v) throws TelepassException {
        try {
            if (v == null || utente == null) {
                throw new TelepassException(TelepassError.GENERIC_ERROR);
            }

            List<Transponder> freeTransponders = transponderDAO.getFreeTransponders();

            if (freeTransponders.isEmpty()) {
                throw new TelepassException(TelepassError.TRANSPONDER_NOT_AVAILABLE);
            }

            Transponder t = freeTransponders.get(FIRST_INDEX);

            if (utente.getMethodPayment() == null) {
                throw new TelepassException(TelepassError.PAYMENT_OPTION_NOT_FOUND);
            }

            t.setUtente(utente);
            utente.setTransponder(t);
            v.setTransponderDTO(t);
            utente.getTransponder().setVeicoloList(List.of(v));

            utenteDAO.save(utente);

        } catch (DaoException e) {
            throw new TelepassException(TelepassError.GENERIC_ERROR, e);
        }
    }

    /**
     * Verifica se un utente è già registrato nel sistema Telepass.
     *
     * @param u L'utente da verificare.
     * @throws TelepassException se l'utente è già registrato (tramite email o cf) o se si verifica un errore durante la verifica.
     */
    @Override
    public void validateUser(Utente u) throws TelepassException {
        try {

            if (utenteDAO.findById(u.getCodiceFiscalePk()) != null) {
                throw new TelepassException(TelepassError.USER_ALREADY_REGISTERED);
            }
            if (utenteDAO.getUtenteByEmail(u.getEmail()) != null) {
                throw new TelepassException(TelepassError.USER_EMAIL_ALREADY_REGISTERED);
            }

        } catch (DaoException e) {
            throw new TelepassException(TelepassError.GENERIC_ERROR, e);
        }
    }

    /**
     * Ottiene un utente dato il suo codice fiscale.
     *
     * @param cf Il codice fiscale dell'utente da recuperare.
     * @return L'utente corrispondente al codice fiscale.
     * @throws TelepassException se si verifica un errore durante il recupero dell'utente.
     */
    @Override
    public Utente getUtenteByCF(String cf) throws TelepassException {
        try {
            return utenteDAO.findById(cf);
        } catch (DaoException e) {
            throw new TelepassException(TelepassError.GENERIC_ERROR, e);
        }
    }

    /**
     * Aggiunge un veicolo alla lista dei veicoli associati a un utente.
     *
     * @param u L'utente a cui aggiungere il veicolo.
     * @param v Il veicolo da aggiungere.
     * @return L'utente aggiornato con il nuovo veicolo.
     * @throws TelepassException se si verifica un errore durante l'aggiunta del veicolo.
     */
    @Override
    public Utente addVehicle(Utente u, Veicolo v) throws TelepassException {
        try {
            u.getTransponder().getVeicoloList().add(v);
            utenteDAO.merge(u);
            return utenteDAO.findById(u.getCodiceFiscalePk());
        } catch (DaoException e) {
            throw new TelepassException(TelepassError.GENERIC_ERROR, e);
        }
    }
}
