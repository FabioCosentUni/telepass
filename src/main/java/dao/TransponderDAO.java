package dao;

import model.Transponder;

import java.sql.SQLException;
import java.util.List;

public interface TransponderDAO {
    boolean insertTransponder(Transponder transponder) throws SQLException;

    Transponder getTransponderByCodice(long codice) throws SQLException;

    List<Transponder> getAllTransponders() throws SQLException;

    boolean updateTransponder(Transponder transponder) throws SQLException;

    boolean deleteTransponderById(long codice) throws SQLException;
}
