package service.impl;

import dao.VeicoloDAO;
import dao.impl.VeicoloDAOImpl;
import exception.TelepassError;
import exception.TelepassException;
import model.Utente;
import model.Veicolo;
import service.VeicoloService;

import java.sql.SQLException;
import java.util.List;

public class VeicoloServiceImpl implements VeicoloService {
    private final VeicoloDAO veicoloDAO;

    public VeicoloServiceImpl() {
        veicoloDAO = new VeicoloDAOImpl();
    }

    @Override
    public Veicolo insertVeicolo(Veicolo veicolo, Utente utente) throws SQLException, TelepassException {
        /*
        try {
            if (veicoloDAO.findById(veicolo.getTargaPk()) != null)
                throw new TelepassException(TelepassError.VEHICLE_ALREADY_REGISTERED);

            if (veicolo.getTipologiaVe().equals("CLASSE A")
                    || veicolo.getTipologiaVe().equals("CLASSE B")
                    || veicolo.getTipologiaVe().equals("CLASSE 3")
                    || veicolo.getTipologiaVe().equals("CLASSE 4")
                    || veicolo.getTipologiaVe().equals("CLASSE 5")) {
                //veicolo.setTransponderDTO(utente.getTransponder());
                if (veicoloDAO.save(veicolo)) {
                    return veicoloDAO.findById(veicolo.getTargaPk());
                } else {
                    throw new TelepassException(TelepassError.GENERIC_ERROR);
                }
            } else {
                throw new TelepassException(TelepassError.NON_EXISTENT_TYPOLOGY);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

         */
        return null;
    }

    @Override
    public List<Veicolo> getAllVeicoli() {
        try {
            return veicoloDAO.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateVeicolo(Veicolo veicolo) {
        /*
        try {
            return veicoloDAO.update(veicolo);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

         */
        return false;
    }

    @Override
    public boolean deleteVeicoloByTarga(String targa) {
        try {
            return veicoloDAO.deleteVeicoloByTarga(targa);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Veicolo getVeicoloByTarga(String targa) {
        try {
            return veicoloDAO.findById(targa);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void entraNelCasello(Veicolo veicolo, long idCasello) {

    }

    @Override
    public void esceDalCasello(Veicolo veicolo, long idCasello) {

    }
}
