package dao.impl;

import dao.UtenteDAO;
import dto.UtenteDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UtenteDAOImpl implements UtenteDAO {

    @Override
    public void insertUtente(UtenteDTO utente) throws SQLException {
        /*
        String query = "INSERT INTO TELEPASS.TB_UTENTE (CODICE_FISCALE_PK, NOME, COGNOME, EMAIL, INDIRIZZO_FATT, CITTA_FATT, REGIONE_FATT, AMMINISTRATORE) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, utente.getCodiceFiscalePk());
            statement.setString(2, utente.getNome());
            statement.setString(3, utente.getCognome());
            statement.setString(4, utente.getEmail());
            statement.setString(5, utente.getIndirizzoFatt());
            statement.setString(6, utente.getCittaFatt());
            statement.setString(7, utente.getRegioneFatt());
            statement.setInt(8, utente.getAmministratore());
            statement.executeUpdate();
        }

         */
    }

    @Override
    public UtenteDTO getUtenteByCodiceFiscale(String codiceFiscale) throws SQLException {
        /*
        String query = "SELECT * FROM TELEPASS.TB_UTENTE WHERE CODICE_FISCALE_PK = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, codiceFiscale);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToUtenteDTO(resultSet);
                }
            }
        }

         */
        return null;
    }

    @Override
    public List<UtenteDTO> getAllUtenti() throws SQLException {
        /*
        List<UtenteDTO> utenti = new ArrayList<>();
        String query = "SELECT * FROM TELEPASS.TB_UTENTE";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                utenti.add(mapResultSetToUtenteDTO(resultSet));
            }
        }
        return utenti;

         */
        return null;
    }

    @Override
    public void updateUtente(UtenteDTO utente) throws SQLException {
        /*
        String query = "UPDATE TELEPASS.TB_UTENTE SET NOME = ?, COGNOME = ?, EMAIL = ?, INDIRIZZO_FATT = ?, CITTA_FATT = ?, REGIONE_FATT = ?, AMMINISTRATORE = ? WHERE CODICE_FISCALE_PK = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, utente.getNome());
            statement.setString(2, utente.getCognome());
            statement.setString(3, utente.getEmail());
            statement.setString(4, utente.getIndirizzoFatt());
            statement.setString(5, utente.getCittaFatt());
            statement.setString(6, utente.getRegioneFatt());
            statement.setInt(7, utente.getAmministratore());
            statement.setString(8, utente.getCodiceFiscalePk());
            statement.executeUpdate();
        }

         */
    }

    @Override
    public void deleteUtente(String codiceFiscale) throws SQLException {
        /*
        String query = "DELETE FROM TELEPASS.TB_UTENTE WHERE CODICE_FISCALE_PK = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, codiceFiscale);
            statement.executeUpdate();
        }

         */
    }

    private UtenteDTO mapResultSetToUtenteDTO(ResultSet resultSet) throws SQLException {
        return new UtenteDTO(
                resultSet.getString("CODICE_FISCALE_PK"),
                resultSet.getString("NOME"),
                resultSet.getString("COGNOME"),
                resultSet.getString("EMAIL"),
                resultSet.getString("INDIRIZZO_FATT"),
                resultSet.getString("CITTA_FATT"),
                resultSet.getString("REGIONE_FATT"),
                resultSet.getInt("AMMINISTRATORE")
        );
    }
}
