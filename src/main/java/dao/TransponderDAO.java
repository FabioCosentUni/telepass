package dao;

import exception.DaoException;
import model.Transponder;

import java.sql.SQLException;
import java.util.List;

public interface TransponderDAO extends BaseDao<Transponder, String> {

    List<Transponder> getActiveTransponders() throws DaoException;
}
