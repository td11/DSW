package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Gestionar conexion a la base de datos
 * @author Teddy
 */
public class Conexion {
    
    private ResourceBundle rb = ResourceBundle.getBundle("consumoelectrico");
    private String driver = rb.getString("database.driver");
    private String url = rb.getString("database.url");
    private String user = rb.getString("database.user");
    private String password = rb.getString("database.password");
    private Connection conexion;

    public Conexion() {
        
    }

    public Connection abrirConexion() throws ClassNotFoundException, SQLException {
        Class.forName(this.driver);
        conexion = DriverManager.getConnection(this.url, this.user, this.password);
        return conexion;
    }
    
    
    
}
