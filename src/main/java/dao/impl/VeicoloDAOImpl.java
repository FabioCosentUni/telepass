package dao.impl;

import dao.BaseDao;
import dao.VeicoloDAO;
import model.Veicolo;

public class VeicoloDAOImpl extends BaseDao<Veicolo, String> implements VeicoloDAO {


    public VeicoloDAOImpl() {
        super(Veicolo.class);
    }

    @Override
    public boolean deleteVeicoloByTarga(String targa) {
        try {
            Veicolo veicolo = findById(targa);
            if (veicolo != null) {
                delete(veicolo);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}