package dao;

import model.Veicolo;

public interface VeicoloHibernateDAO extends BaseHibernateDao<Veicolo, String> {

    boolean deleteVeicoloByTarga(String targa);
}
