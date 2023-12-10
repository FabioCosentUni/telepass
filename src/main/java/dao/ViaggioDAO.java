package dao;

import model.Viaggio;

import java.sql.SQLException;
import java.util.List;

public interface ViaggioDAO {
    boolean insertViaggio(Viaggio viaggio) throws SQLException;

    Viaggio getViaggioById(long viaggioId) throws SQLException;

    List<Viaggio> getAllViaggi() throws SQLException;

    boolean updateViaggio(Viaggio viaggio) throws SQLException;

    boolean deleteViaggioById(long viaggioId) throws SQLException;
}
