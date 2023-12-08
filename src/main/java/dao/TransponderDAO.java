package dao;

import dto.TransponderDTO;

import java.sql.SQLException;
import java.util.List;

public interface TransponderDAO {
    void insertTransponder(TransponderDTO transponder) throws SQLException;

    TransponderDTO getTransponderByCodice(long codice) throws SQLException;

    List<TransponderDTO> getAllTransponders() throws SQLException;

    void updateTransponder(TransponderDTO transponder) throws SQLException;

    void deleteTransponderById(long codice) throws SQLException;
}
