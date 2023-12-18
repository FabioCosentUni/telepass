package dao;

import model.Transponder;

import java.sql.SQLException;
import java.util.List;

public interface TransponderDAO {
    boolean insert(Transponder transponder) throws SQLException;

    Transponder getTransponderByCodice(String codice);

    List<Transponder> getAllTransponders() throws SQLException;

    boolean updateTransponder(Transponder transponder) throws SQLException;

    boolean deleteTransponderById(long codice) throws SQLException;

    Transponder getDisponibilita() throws SQLException;
}
