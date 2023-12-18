package service.impl;

import dao.UtenteDAO;
import dao.impl.UtenteDAOImpl;
import exception.TelepassError;
import exception.TelepassException;
import model.Utente;
import service.UtenteService;

import java.sql.SQLException;

public class UtenteServiceImpl implements UtenteService {

    private final UtenteDAO utenteDAO;

    public UtenteServiceImpl() {
        this.utenteDAO = new UtenteDAOImpl();
    }

    @Override
    public Utente login(String cf, String password) throws SQLException, TelepassException {

        Utente u = utenteDAO.getUtenteByCodiceFiscale(cf);
        if(u == null) {
            throw new TelepassException(TelepassError.INCORRECT_CF);
        }

        if(!u.getPassword().equals(password)) {
            throw new TelepassException(TelepassError.INCORRECT_PASSWORD);
        }

        System.out.println("Utente recuperato: " + u.getCodiceFiscalePk());
        return u;
    }

    @Override
    public void register(Utente utente) throws SQLException, TelepassException {
        if(utenteDAO.getUtenteByCodiceFiscale(utente.getCodiceFiscalePk()) != null) {
            throw new TelepassException(TelepassError.USER_ALREADY_REGISTERED);
        }

        if(utenteDAO.getUtenteByEmail(utente.getEmail()) != null) {
            throw new TelepassException(TelepassError.USER_EMAIL_ALREADY_REGISTERED);
        }

        /*if(paymentOption == null) {
            throw new UserException(UserError.PAYMENT_OPTION_NOT_FOUND);
        }*/

        //Transponder transponder = new Transponder(utente, paymentOption.getName());
        //utente.setTransponder(transponder);

        utenteDAO.insert(utente);
    }

    @Override
    public boolean richiediNuovoVeicoloTransponder(String targa) {
        return false;
    }

    @Override
    public boolean richiediTelepassPlus(long idTransponder) {
        return false;
    }
}
