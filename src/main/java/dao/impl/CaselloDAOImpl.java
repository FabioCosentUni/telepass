package dao.impl;

import dao.CaselloDAO;
import dto.CaselloDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CaselloDAOImpl implements CaselloDAO {


    @Override
    public void insertCasello(CaselloDTO casello) throws SQLException {
        /*
        String query = "INSERT INTO TELEPASS.TB_CASELLO (ID_CASELLO_PK, KM, INGRESSI, AUTOSTRADA) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, casello.getIdCaselloPk());
            statement.setInt(2, casello.getKm());
            statement.setInt(3, casello.getIngressi());
            statement.setString(4, casello.getAutostrada());
            statement.executeUpdate();
        }

         */
    }

    @Override
    public CaselloDTO getCaselloById(long id) throws SQLException {
        /*
        String query = "SELECT * FROM TELEPASS.TB_CASELLO WHERE ID_CASELLO_PK = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToCaselloDTO(resultSet);
                }
            }
        }
        return null;

         */
        return null;
    }

    @Override
    public List<CaselloDTO> getAllCaselli() throws SQLException {
        /*
        List<CaselloDTO> caselli = new ArrayList<>();
        String query = "SELECT * FROM TELEPASS.TB_CASELLO";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                caselli.add(mapResultSetToCaselloDTO(resultSet));
            }
        }
        return caselli;

         */
        return null;
    }

    @Override
    public void updateCasello(CaselloDTO casello) throws SQLException {
        /*
        String query = "UPDATE TELEPASS.TB_CASELLO SET KM = ?, INGRESSI = ?, AUTOSTRADA = ? WHERE ID_CASELLO_PK = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, casello.getKm());
            statement.setInt(2, casello.getIngressi());
            statement.setString(3, casello.getAutostrada());
            statement.setLong(4, casello.getIdCaselloPk());
            statement.executeUpdate();
        }

         */
    }

    @Override
    public void deleteCasello(long id) throws SQLException {
        /*
        String query = "DELETE FROM TELEPASS.TB_CASELLO WHERE ID_CASELLO_PK = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }

         */
    }

    private CaselloDTO mapResultSetToCaselloDTO(ResultSet resultSet) throws SQLException {
        return new CaselloDTO(
                resultSet.getLong("ID_CASELLO_PK"),
                resultSet.getInt("KM"),
                resultSet.getInt("INGRESSI"),
                resultSet.getString("AUTOSTRADA")
        );
    }
}
