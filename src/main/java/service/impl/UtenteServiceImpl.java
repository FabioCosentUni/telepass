package service.impl;

import dao.impl.TransponderDAOImpl;
import dao.impl.UtenteDAOImpl;
import exception.DaoException;
import exception.TelepassError;
import exception.TelepassException;
import model.Transponder;
import model.Utente;
import model.Veicolo;
import service.UtenteService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UtenteServiceImpl implements UtenteService {

    private final UtenteDAOImpl utenteDAO;
    private final TransponderDAOImpl transponderDAO;

    public UtenteServiceImpl() {
        this.utenteDAO = new UtenteDAOImpl();
        this.transponderDAO = new TransponderDAOImpl();
    }

    @Override
    public Utente login(String cf, String password) throws SQLException, TelepassException, DaoException {

        Utente u = utenteDAO.findById(cf);
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
    public void register(Utente utente, Veicolo v) throws SQLException, TelepassException, DaoException {
        if(v == null || utente == null){
            throw new TelepassException(TelepassError.GENERIC_ERROR);//Cambiare con errore specifico
        }
        if(!v.getTipologiaVe().equals("CLASSE A")
                || !v.getTipologiaVe().equals("CLASSE B")
                || !v.getTipologiaVe().equals("CLASSE 3")
                || !v.getTipologiaVe().equals("CLASSE 4")
                || !v.getTipologiaVe().equals("CLASSE 5"))
        {
            throw new TelepassException(TelepassError.NON_EXISTENT_TYPOLOGY);
        }
        List<Veicolo> veicoli = new ArrayList<>();
        veicoli.add(v);
        if(utenteDAO.findById(utente.getCodiceFiscalePk()) != null) {
            throw new TelepassException(TelepassError.USER_ALREADY_REGISTERED);
        }
        if(utenteDAO.getUtenteByEmail(utente.getEmail()) != null) {
            throw new TelepassException(TelepassError.USER_EMAIL_ALREADY_REGISTERED);
        }
        if(utente.getMethodPayment() == null) {
            throw new TelepassException(TelepassError.PAYMENT_OPTION_NOT_FOUND);
        }
        Transponder t = transponderDAO.getActiveTransponders().stream().findFirst().orElse(null);
        if(t == null) {
            throw new TelepassException(TelepassError.TRANSPONDER_NOT_AVAILABLE);
        }
        utente.setTransponder(t);
        utente.getTransponder().setVeicoloList(veicoli);
        if(utente.getMethodPayment() == null) {
            throw new TelepassException(TelepassError.PAYMENT_OPTION_NOT_FOUND);
        }

        utenteDAO.save(utente);

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
