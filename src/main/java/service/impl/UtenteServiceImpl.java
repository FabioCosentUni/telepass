package service.impl;

import dao.UtenteDAO;
import dao.impl.UtenteDAOImpl;
import dto.UtenteDTO;
import service.UtenteService;

import java.sql.SQLException;
import java.util.List;

public class UtenteServiceImpl implements UtenteService {
    private UtenteDAO utenteDAO = new UtenteDAOImpl();
    @Override
    public boolean insertUtente(UtenteDTO utenteDTO) {
        try {
            return utenteDAO.insertUtente(utenteDTO);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<UtenteDTO> getAllUtenti() {
        try {
            return utenteDAO.getAllUtenti();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateUtente(UtenteDTO utenteDTO) {
        try {
            return utenteDAO.updateUtente(utenteDTO);
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
    public UtenteDTO getUtenteByCodiceFiscale(String codiceFiscale) {
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
