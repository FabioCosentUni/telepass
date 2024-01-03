package dao.impl;

import dao.VeicoloHibernateDAO;
import model.Veicolo;

public class VeicoloHibernateDAOImpl extends BaseHibernateDaoImpl<Veicolo, String> implements VeicoloHibernateDAO {


    public VeicoloHibernateDAOImpl() {
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