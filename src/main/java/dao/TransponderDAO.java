package dao;

import model.Transponder;

import java.sql.SQLException;
import java.util.List;

public interface TransponderDAO {

    Transponder getById(long id);
    boolean insert(Transponder transponder) throws SQLException;

    Transponder getTransponderByCodice(String codice);

    List<Transponder> getAllTransponders() throws SQLException;

    boolean updateTransponder(Transponder transponder) throws SQLException;

    boolean delete(long id) throws SQLException;

    Transponder getDisponibilita() throws SQLException;
}
