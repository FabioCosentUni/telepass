package dao;

import dto.ViaggioDTO;

import java.sql.SQLException;
import java.util.List;

public interface ViaggioDAO {
    void insertViaggio(ViaggioDTO viaggio) throws SQLException;

    ViaggioDTO getViaggioById(long viaggioId) throws SQLException;

    List<ViaggioDTO> getAllViaggi() throws SQLException;

    void updateViaggio(ViaggioDTO viaggio) throws SQLException;

    void deleteViaggioById(long viaggioId) throws SQLException;
}
