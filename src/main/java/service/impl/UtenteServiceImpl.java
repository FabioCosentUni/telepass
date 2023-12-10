package service.impl;

import dao.UtenteDAO;
import dao.impl.UtenteDAOImpl;
import model.Utente;
import service.UtenteService;

import java.sql.SQLException;
import java.util.List;

public class UtenteServiceImpl implements UtenteService {
    private UtenteDAO utenteDAO = new UtenteDAOImpl();
    @Override
    public boolean insertUtente(Utente utente) {
        try {
            return utenteDAO.insertUtente(utente);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Utente> getAllUtenti() {
        try {
            return utenteDAO.getAllUtenti();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateUtente(Utente utente) {
        try {
            return utenteDAO.updateUtente(utente);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteUtenteByCodiceFiscale(String codiceFiscale) {
        try {
            return utenteDAO.deleteUtenteByCodiceFiscale(codiceFiscale);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Utente getUtenteByCodiceFiscale(String codiceFiscale) {
        try {
            return utenteDAO.getUtenteByCodiceFiscale(codiceFiscale);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
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
