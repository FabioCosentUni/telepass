package dao;

import dto.ViaggioDTO;

import java.sql.SQLException;
import java.util.List;

public interface ViaggioDAO {
    boolean insertViaggio(ViaggioDTO viaggio) throws SQLException;

    ViaggioDTO getViaggioById(long viaggioId) throws SQLException;

    List<ViaggioDTO> getAllViaggi() throws SQLException;

    boolean updateViaggio(ViaggioDTO viaggio) throws SQLException;

    boolean deleteViaggioById(long viaggioId) throws SQLException;
}
