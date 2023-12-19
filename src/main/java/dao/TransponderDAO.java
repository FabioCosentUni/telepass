package dao;

import exception.DaoException;
import model.Transponder;

import java.util.List;

public interface TransponderDAO {

    List<Transponder> getActiveTransponders() throws DaoException;

    Transponder findFreeTransponder() throws DaoException;
}
