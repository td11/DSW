package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * En esta clase hacemos consultas a la base de datos
 *
 * @author Teddy
 */
public class Modelo {

    private String consultaClientes;
    private Conexion miconexion;
    private PreparedStatement ps;
    private ArrayList<Cliente> losclientes;

    public Modelo() throws ClassNotFoundException, SQLException {
        miconexion = new Conexion();
    }

    public String devolverTabla(int rango) throws SQLException, ClassNotFoundException {
        
        StringBuilder sb = new StringBuilder();
        miconexion.abrirConexion();
        consultaClientes = "select nombre, apellido, provincia , poblacion from misclientes LIMIT 0,?";
        ps = miconexion.getConexion().prepareStatement(consultaClientes);
        ps.setInt(1, rango);
        ResultSet rs = ps.executeQuery();
        Cliente micliente;

        while (rs.next()) {
            micliente = new Cliente();
            sb.append("<tr>");
            sb.append("<td>" + rs.getString("nombre") + " " + rs.getString("apellido") + "</td>");
            sb.append("<td>" + rs.getString("poblacion") + "</td>");
            sb.append("<td>" + rs.getString("provincia") + "</td>");
            sb.append("</tr>");

        }

        rs.close();
        ps.close();
        miconexion.getConexion().close();

        return sb.toString();
    }
    
    public ArrayList<Cliente> devolverClientes() throws SQLException, ClassNotFoundException {
        StringBuilder sb = new StringBuilder();
        miconexion.abrirConexion();
        consultaClientes = "select nombre, apellido, provincia , poblacion from misclientes";
        ps = miconexion.getConexion().prepareStatement(consultaClientes);
        ResultSet rs = ps.executeQuery();
        Cliente micliente;
        ArrayList<Cliente> lista = new ArrayList<Cliente>();

        while (rs.next()) {
            micliente = new Cliente();
           
            micliente.setNombre(rs.getString("nombre")); 
            micliente.setApellidos(rs.getString("apellido"));
            micliente.setPoblacion(rs.getString("poblacion"));
            micliente.setProvincia(rs.getString("provincia"));
            
            lista.add(micliente);
        }

        rs.close();
        ps.close();
        miconexion.getConexion().close();

        return lista;
    }

}
