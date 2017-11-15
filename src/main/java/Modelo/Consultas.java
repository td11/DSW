package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * En esta clase hacemos consultas a la base de datos
 * @author Teddy
 */
public class Consultas {
    
    String consultaClientes;
    Connection miconexion;
    PreparedStatement consultar;
    Cliente micliente;
    ArrayList<Cliente> losclientes;
    
    public Consultas(int limite) throws ClassNotFoundException, SQLException{
        miconexion = new Conexion().abrirConexion();
        consultaClientes = "select nombre, apellido, provincia , poblacion from misclientes LIMIT ? ,10";
        consultar = miconexion.prepareStatement(consultaClientes);
        consultar.setInt(1, limite);
        ResultSet rs =consultar.executeQuery();
        
        while(rs.next()){
            
        }
        
    }
    
    
    
    
}
