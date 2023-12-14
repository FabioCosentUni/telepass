package service.impl;

import dao.UtenteDAO;
import dao.impl.UtenteDAOImpl;
import exception.user.UserError;
import exception.user.UserException;
import model.Utente;
import service.UtenteService;

import java.sql.SQLException;

public class UtenteServiceImpl implements UtenteService {

    private final UtenteDAO utenteDAO;

    public UtenteServiceImpl() {
        this.utenteDAO = new UtenteDAOImpl();
    }

    @Override
    public Utente login(String cf, String password) throws SQLException, UserException {

        Utente u = utenteDAO.getUtenteByCodiceFiscale(cf);
        if(u == null) {
            throw new UserException(UserError.INCORRECT_CF);
        }

        if(!u.getPassword().equals(password)) {
            throw new UserException(UserError.INCORRECT_PASSWORD);
        }

        System.out.println("Utente recuperato: " + u.getCodiceFiscalePk());
        return u;
    }

    @Override
    public void register(Utente utente) throws SQLException, UserException {
        if(utenteDAO.getUtenteByCodiceFiscale(utente.getCodiceFiscalePk()) != null) {
            throw new UserException(UserError.USER_ALREADY_REGISTERED);
        }

        if(utenteDAO.getUtenteByEmail(utente.getEmail()) != null) {
            throw new UserException(UserError.USER_EMAIL_ALREADY_REGISTERED);
        }

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
