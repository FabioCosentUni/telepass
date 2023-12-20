package dao;

import exception.DaoException;
import model.Viaggio;

import java.util.List;

public interface ViaggioDAO extends BaseDao<Viaggio, Long> {

    List<Integer> getViaggiByVeicolo(String targa) throws DaoException;

    boolean checkVeicoloViaggi(String targa) throws DaoException;

}
