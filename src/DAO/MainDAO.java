package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class MainDAO {

    public class DBConnection {
        // Configuración de la base de datos
        private static final String DB_URL = "jdbc:mysql://192.168.1.102:3306/nba";
        private static final String USER = "root";
        private static final String PASS = "casaos";

        public static Connection getConnection() throws SQLException {
            Connection conn = null;
            try {
                // Registrar el controlador JDBC
                Class.forName("com.mysql.jdbc.Driver");
                // Abrir una conexión
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                throw new SQLException("Error al cargar el driver JDBC", e);
            }
            return conn;
        }
    }
}