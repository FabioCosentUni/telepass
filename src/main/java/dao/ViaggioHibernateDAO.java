package dao;

import exception.DaoException;
import model.Casello;
import model.Viaggio;

import java.util.List;
import java.util.Map;

public interface ViaggioHibernateDAO extends BaseHibernateDao<Viaggio, Long> {

    List<Integer> getViaggiByVeicolo(String targa) throws DaoException;

    boolean checkVeicoloViaggi(String targa) throws DaoException;


    Map<Casello, Double> getPercentualiEntrateCaselli() throws DaoException;

    Map<Casello, Double> getPercentualiUsciteCaselli() throws DaoException;

}
