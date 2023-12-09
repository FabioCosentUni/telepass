package dao;

import dto.CaselloDTO;

import java.sql.SQLException;
import java.util.List;

public interface CaselloDAO {
    boolean insertCasello(CaselloDTO casello) throws SQLException;

    CaselloDTO getCaselloById(long id) throws SQLException;

    List<CaselloDTO> getAllCaselli() throws SQLException;

    boolean updateCasello(CaselloDTO casello) throws SQLException;

    boolean deleteCaselloById(long caselloId) throws SQLException;
}
