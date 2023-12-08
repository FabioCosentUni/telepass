package dao.impl;

import dao.CaselloDAO;
import dto.CaselloDTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.HibernateConfiguration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CaselloDAOImpl implements CaselloDAO {


    @Override
    public void insertCasello(CaselloDTO casello) throws SQLException {
        Transaction transaction = null;
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(casello);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
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
