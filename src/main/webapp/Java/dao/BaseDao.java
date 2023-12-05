import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class BaseDAO {

    private static final String JDBC_URL = "jdbc:oracle:thin:@//localhost:1521/your_database";
    private static final String USERNAME = "your_username";
    private static final String PASSWORD = "your_password";

    protected Connection connection;

    public BaseDAO() {
        this.connection = openConnection();
    }

    private Connection openConnection() {
        try {
            return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Errore durante l'apertura della connessione al database", e);
        }
    }

    protected void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException("Errore durante la chiusura della connessione al database", e);
            }
        }
    }
}
