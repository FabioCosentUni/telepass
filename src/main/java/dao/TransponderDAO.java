package dao;

import dto.TransponderDTO;

import java.sql.SQLException;
import java.util.List;

public interface TransponderDAO {
    boolean insertTransponder(TransponderDTO transponder) throws SQLException;

    TransponderDTO getTransponderByCodice(long codice) throws SQLException;

    List<TransponderDTO> getAllTransponders() throws SQLException;

    boolean updateTransponder(TransponderDTO transponder) throws SQLException;

    boolean deleteTransponderById(long codice) throws SQLException;
}
