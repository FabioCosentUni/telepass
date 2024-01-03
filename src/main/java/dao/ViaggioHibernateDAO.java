package dao;

import exception.DaoException;
import model.Viaggio;

import java.util.List;

public interface ViaggioHibernateDAO extends BaseHibernateDao<Viaggio, Long> {

    List<Integer> getViaggiByVeicolo(String targa) throws DaoException;

    boolean checkVeicoloViaggi(String targa) throws DaoException;

}
