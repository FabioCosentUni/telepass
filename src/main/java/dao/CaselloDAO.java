package dao;

import model.Casello;

import java.sql.SQLException;
import java.util.List;

public interface CaselloDAO {
    boolean insertCasello(Casello casello) throws SQLException;

    Casello getCaselloById(long id) throws SQLException;

    List<Casello> getAllCaselli() throws SQLException;

    boolean updateCasello(Casello casello) throws SQLException;

    boolean deleteCaselloById(long caselloId) throws SQLException;
}
