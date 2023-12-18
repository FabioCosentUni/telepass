package service.impl;

import dao.VeicoloDAO;
import dao.impl.VeicoloDAOImpl;
import exception.user.VehicleError;
import exception.user.VehicleException;
import model.Utente;
import model.Veicolo;
import service.VeicoloService;

import java.sql.SQLException;
import java.util.List;

public class VeicoloServiceImpl implements VeicoloService {
    private VeicoloDAO veicoloDAO=new VeicoloDAOImpl();
    @Override
    public Veicolo insertVeicolo(Veicolo veicolo, Utente utente) throws SQLException, VehicleException {
        try {
            if(veicoloDAO.getVeicoloByTarga(veicolo.getTargaPk()) != null)
                throw new VehicleException(VehicleError.VEHICLE_ALREADY_REGISTERED);

            if(veicolo.getTipologiaVe().equals("CLASSE A")
            || veicolo.getTipologiaVe().equals("CLASSE B")
            || veicolo.getTipologiaVe().equals("CLASSE 3")
            || veicolo.getTipologiaVe().equals("CLASSE 4")
            || veicolo.getTipologiaVe().equals("CLASSE 5"))
            {
                //veicolo.setTransponderDTO(utente.getTransponder());
                if(veicoloDAO.insertVeicolo(veicolo)){
                    return veicoloDAO.getVeicoloByTarga(veicolo.getTargaPk());
                } else {
                    throw new VehicleException(VehicleError.GENERIC_ERROR);
                }
            } else {
                throw new VehicleException(VehicleError.NON_EXISTENT_TYPOLOGY);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Veicolo> getAllVeicoli() {
        try {
            return veicoloDAO.getAllVeicoli();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateVeicolo(Veicolo veicolo) {
        try {
            return veicoloDAO.updateVeicolo(veicolo);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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
            return veicoloDAO.getVeicoloByTarga(targa);
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
