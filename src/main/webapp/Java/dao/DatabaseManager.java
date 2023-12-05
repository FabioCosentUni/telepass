import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    private static final String JDBC_URL = "jdbc:oracle:thin:@//localhost:1521/your_database";
    private static final String USERNAME = "your_username";
    private static final String PASSWORD = "your_password";

    private static Connection connection;

    private DatabaseManager() {
        // Costruttore privato per impedire la creazione di istanze esterne
    }

    public static Connection getConnection() {
        if (connection == null) {
            synchronized (DatabaseManager.class) {
                if (connection == null) {
                    try {
                        connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
                    } catch (SQLException e) {
                        throw new RuntimeException("Errore durante l'apertura della connessione al database", e);
                    }
                }
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException("Errore durante la chiusura della connessione al database", e);
            } finally {
                connection = null; // Imposta la connessione a null dopo la chiusura
            }
        }
    }
}
