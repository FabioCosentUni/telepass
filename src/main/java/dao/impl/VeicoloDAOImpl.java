package dao.impl;

import dao.BaseDao;
import dao.VeicoloDAO;
import model.Veicolo;

public class VeicoloDAOImpl extends BaseDao<Veicolo, String> implements VeicoloDAO {


    public VeicoloDAOImpl() {
        super(Veicolo.class);
    }
}