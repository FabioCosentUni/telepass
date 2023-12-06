package dao;

import dto.UtenteDTO;

import java.sql.SQLException;
import java.util.List;

public interface UtenteDAO {
    void insertUtente(UtenteDTO utente) throws SQLException;

    UtenteDTO getUtenteByCodiceFiscale(String codiceFiscale) throws SQLException;

    List<UtenteDTO> getAllUtenti() throws SQLException;

    void updateUtente(UtenteDTO utente) throws SQLException;

    void deleteUtente(String codiceFiscale) throws SQLException;
}
