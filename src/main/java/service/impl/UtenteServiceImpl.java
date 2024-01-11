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

public class UtenteServiceImpl implements UtenteService {

    private static final int FIRST_INDEX = 0;
    private final UtenteDAO utenteDAO;
    private final TransponderDAO transponderDAO;

    public UtenteServiceImpl(UtenteDAO utenteDAO, TransponderDAO transponderDAO) {
        this.utenteDAO = utenteDAO;
        this.transponderDAO = transponderDAO;
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
                throw new TelepassException(TelepassError.GENERIC_ERROR);
            }

            List<Transponder> freeTransponders = transponderDAO.getFreeTransponders();

            if(freeTransponders.isEmpty()) {
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

    @Override
    public Utente addVehicle(Utente u, Veicolo v) throws TelepassException {
        try{
            u.getTransponder().getVeicoloList().add(v);
            utenteDAO.merge(u);
            return utenteDAO.findById(u.getCodiceFiscalePk());
        } catch (DaoException e) {
            throw new TelepassException(TelepassError.GENERIC_ERROR, e);
        }
    }
}
