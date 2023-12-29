package service.impl;

import dao.TransponderDAO;
import dao.UtenteDAO;
import dao.impl.TransponderDAOImpl;
import dao.impl.UtenteDAOImpl;
import exception.DaoException;
import exception.TelepassError;
import exception.TelepassException;
import model.Transponder;
import model.Utente;
import model.Veicolo;
import service.UtenteService;

import java.util.List;

public class UtenteServiceImpl implements UtenteService {

    private final UtenteDAO utenteDAO;
    private final TransponderDAO transponderDAO;

    public UtenteServiceImpl() {
        this.utenteDAO = new UtenteDAOImpl();
        this.transponderDAO = new TransponderDAOImpl();
    }

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

    @Override
    public void register(Utente utente, Veicolo v) throws TelepassException {
        try {
            if (v == null || utente == null) {
                throw new TelepassException(TelepassError.GENERIC_ERROR);//Cambiare con errore specifico
            }

            Transponder t = transponderDAO.findFreeTransponder();

            if (t == null) {
                throw new TelepassException(TelepassError.TRANSPONDER_NOT_AVAILABLE);
            }
            if (utente.getMethodPayment() == null) {
                throw new TelepassException(TelepassError.PAYMENT_OPTION_NOT_FOUND);
            }

            t.setUtente(utente);
            utente.setTransponder(t);
            v.setTransponderDTO(t);//TODO correggere nome del set rimuovendo DTO
            utente.getTransponder().setVeicoloList(List.of(v));

            utenteDAO.save(utente);

        } catch (DaoException e) {
            throw new TelepassException(TelepassError.GENERIC_ERROR, e);
        }

    }

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

    @Override
    public Utente getUtenteByCF(String cf) throws TelepassException {
        try {
            return utenteDAO.findById(cf);
        } catch (DaoException e) {
            throw new TelepassException(TelepassError.GENERIC_ERROR, e);
        }
    }
}
