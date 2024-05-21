package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class MainDAO {


    public class MySQLConnection {

        private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/team_game_logs";
        private static final String USER = "root";
        private static final String PASS = "Sox2020@";

        public static void main(String[] args) {
            Connection conn = null;
            Statement stmt = null;

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");

                conn = DriverManager.getConnection(DB_URL, USER, PASS);

                // Paso 3: Ejecutar una consulta SQL
                System.out.println("Creando declaración...");
                stmt = conn.createStatement();
                String sql = "SELECT id, nombre, apellido FROM Empleados";
                ResultSet rs = stmt.executeQuery(sql);

                // Paso 4: Procesar los resultados del ResultSet
                while (rs.next()) {
                    // Recuperar datos por nombre de columna
                    int id = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    String apellido = rs.getString("apellido");

                    // Mostrar los valores
                    System.out.print("ID: " + id);
                    System.out.print(", Nombre: " + nombre);
                    System.out.println(", Apellido: " + apellido);
                }

                // Paso 5: Cerrar el ResultSet
                rs.close();
            } catch (SQLException se) {
                // Manejo de errores para JDBC
                se.printStackTrace();
            } catch (Exception e) {
                // Manejo de errores para Class.forName
                e.printStackTrace();
            } finally {
                // Bloque finally usado para cerrar recursos
                try {
                    if (stmt != null) stmt.close();
                } catch (SQLException se2) {
                } // Nada que podamos hacer
                try {
                    if (conn != null) conn.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }

            System.out.println("Adiós!");
        }
    }
}

