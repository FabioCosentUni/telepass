package dao;

import dto.CaselloDTO;

import java.sql.SQLException;
import java.util.List;

public interface CaselloDAO {
    void insertCasello(CaselloDTO casello) throws SQLException;

    CaselloDTO getCaselloById(long id) throws SQLException;

    List<CaselloDTO> getAllCaselli() throws SQLException;

    void updateCasello(CaselloDTO casello) throws SQLException;

    void deleteCasello(long id) throws SQLException;
}
