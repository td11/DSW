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
    ArrayList<Cliente> losclientes;
    
    public Consultas() throws ClassNotFoundException, SQLException{
        losclientes = new ArrayList<Cliente>();
        miconexion = new Conexion().abrirConexion();
        consultaClientes = "select nombre, apellido, provincia , poblacion from misclientes";
        consultar = miconexion.prepareStatement(consultaClientes);
        ResultSet rs =consultar.executeQuery();
        
        while(rs.next()){
            Cliente micliente = new Cliente();
            micliente.setNombre(rs.getString("nombre"));
            micliente.setApellidos(rs.getString("apellido"));
            micliente.setPoblacion(rs.getString("poblacion"));
            micliente.setProvincia(rs.getString("provincia"));
            losclientes.add(micliente);
        }
        
    }
    
    public ArrayList<Cliente> devolverLista(){
        return losclientes;
    }
    
    
    
    
}
