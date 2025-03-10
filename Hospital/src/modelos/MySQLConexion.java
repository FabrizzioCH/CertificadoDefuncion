package modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConexion {

    private final String nombreDataBase = "hospital";
    private final String usuario = "root";
    private final String password = "root";
    private final String url = "jdbc:mysql://localhost:3306/" + nombreDataBase;
    Connection conn = null;

    public Connection getConnection() {
        try {
            //Obtener valor del driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Obtener la conexión
            conn = DriverManager.getConnection(url, usuario, password);

        } catch (ClassNotFoundException e) {
            System.err.println("Ha ocurrido un ClassNotFoundException " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Ha ocurrido un SQLException " + e.getMessage());
        }
        return conn;
    }
}
