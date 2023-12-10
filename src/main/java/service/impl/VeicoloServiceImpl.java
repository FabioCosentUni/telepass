package service.impl;

import dao.VeicoloDAO;
import dao.impl.VeicoloDAOImpl;
import model.Veicolo;
import service.VeicoloService;

import java.util.List;

public class VeicoloServiceImpl implements VeicoloService {
    private VeicoloDAO veicoloDAO=new VeicoloDAOImpl();
    @Override
    public boolean insertVeicolo(Veicolo veicolo) {
        try {
            return veicoloDAO.insertVeicolo(veicolo);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
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
