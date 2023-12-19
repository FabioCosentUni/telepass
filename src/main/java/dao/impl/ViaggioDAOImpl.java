package dao.impl;

import dao.BaseDao;
import dao.ViaggioDAO;
import model.Viaggio;

public class ViaggioDAOImpl extends BaseDao<Viaggio, Long> implements ViaggioDAO {

    public ViaggioDAOImpl() {
        super(Viaggio.class);
    }


}
