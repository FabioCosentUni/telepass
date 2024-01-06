package dao.impl;

import dao.VeicoloHibernateDAO;
import model.Veicolo;

public class VeicoloHibernateDAOImpl extends BaseHibernateDaoImpl<Veicolo, String> implements VeicoloHibernateDAO {


    public VeicoloHibernateDAOImpl() {
        super(Veicolo.class);
    }
}