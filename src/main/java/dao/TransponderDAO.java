package dao;

import exception.DaoException;
import model.Transponder;

import java.util.List;

public interface TransponderDAO extends BaseDao<Transponder, String> {

    List<Transponder> getActiveTransponders() throws DaoException;

    Transponder findFreeTransponder() throws DaoException;

    void revokeTransponder(String transponderCode) throws DaoException;
}
