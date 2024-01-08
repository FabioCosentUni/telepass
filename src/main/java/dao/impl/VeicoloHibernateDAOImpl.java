package dao.impl;

import dao.VeicoloHibernateDAO;
import model.Veicolo;

public class VeicoloHibernateDAOImpl extends BaseHibernateDAOImpl<Veicolo, String> implements VeicoloHibernateDAO {


    public VeicoloHibernateDAOImpl() {
        super(Veicolo.class);
    }
}