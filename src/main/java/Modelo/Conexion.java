package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Gestionar conexion a la base de datos
 *
 * @author Teddy
 */
public class Conexion {

    private ResourceBundle rb;
    private String driver, url, user, password;
    private Connection conexion;

    public Conexion() {
        rb = ResourceBundle.getBundle("consumoelectrico");
        driver = rb.getString("database.driver");
        url = rb.getString("database.url");
        user = rb.getString("database.user");
        password = rb.getString("database.password");
    }

    public void abrirConexion() throws ClassNotFoundException, SQLException {
        Class.forName(this.driver);
        conexion = DriverManager.getConnection(this.url, this.user, this.password);
    }

    //Getters y Setters
    public Connection getConexion() {
        return conexion;
    }

}
