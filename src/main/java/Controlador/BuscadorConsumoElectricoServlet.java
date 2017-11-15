package Controlador;

import Modelo.Cliente;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Teddy
 */
public class BuscadorConsumoElectricoServlet extends HttpServlet {

    //Variables
    ServletConfig config;
    private String dbUrl;
    private String dbUser;
    private String dbPassword;
    private Integer dbPageSize;
    private Integer DEFAULT_PAGESIZE;

    //Variables para conectarse
    Connection conexion = null;

    //ArrayList de objetos
    ArrayList<Cliente> listaclientes = new ArrayList();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Buscador de Clientes</title>");
            out.println("<link rel='stylesheet' type='text/css' href='CSS/estilo.css'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Clientes listados</h1>");

            String nombre = request.getParameter("nameToSearch");

            //Conectarse 
            conectarse();

            //Comprobamos si la conexion no tuvo errores
            if (conexion != null) {
                //Verificamos si el usuario queria buscar un cliente o no
                if (nombre.isEmpty()) {
                    todosLosClientes();
                } else {
                   // buscarCliente();
                }
            } else {
                out.println("<h2>Error no se han encontrado los clientes</h2>");
            }

            //Imprimir si el arraylist de clientes no esta vacio
            if (listaclientes.isEmpty()) {
                out.println("<h3>Vuelva a la página de inicio e intentelo de nuevo</h3>");
            } else {
                //Dibujamos la tabla
                out.println("<h2>Tabla de clientes</h2>");
                out.println("<table>");
                //Cabecero
                out.println("<tr>");
                out.println("<th>ID</th>");
                out.println("<th>Nombre</th>");
                out.println("<th>Apellido</th>");
                out.println("</tr>");
                //Imprimimos contenido
                for (int i = 0; i < listaclientes.size(); i++) {
                    Cliente uncliente = new Cliente();
                    uncliente = listaclientes.get(i);
                    out.println("<tr>");
                    out.println("<td>"+uncliente.getClNo()+"</td>");
                    out.println("<td>"+uncliente.getNombre()+"</td>");
                    out.println("<td>"+uncliente.getApellidos()+"</td>");
                    out.println("</tr>");
                }
                out.println("</table>");
                        
            }

            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(BuscadorConsumoElectricoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(BuscadorConsumoElectricoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    //Para conectarse a la base de datos
    public void conectarse() throws SQLException {
        //conexion = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/consumoelectrico", "2dawa", "2dawa");
    }

    public void todosLosClientes() throws SQLException {
        Cliente elCliente;

        Statement s = conexion.createStatement();
        s.setMaxRows(dbPageSize);
        //Realizamos una consulta para obtener el id del primer contacto
        ResultSet rs = s.executeQuery("select * from misclientes");

        while (rs.next()) {
            elCliente = new Cliente();
            elCliente.setClNo(rs.getInt("Id"));
            elCliente.setNombre(rs.getString("nombre"));
            elCliente.setApellidos(rs.getString("apellido"));

            listaclientes.add(elCliente);
        }

    }
    
    //Se usa para el archivo JSP
    public LinkedList<Cliente> devolverClientes() throws SQLException {
        LinkedList<Cliente> listaClientes = new LinkedList<>();
        //inicio(config);
        conectarse();
        
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("select nombre,apellidos,provincia,poblacion from misclientes limit 0,10");
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellidos(rs.getString("apellidos"));
                cliente.setProvincia(rs.getString("provincia"));
                cliente.setPoblacion(rs.getString("poblacion"));
                listaClientes.add(cliente);
            }
            rs.close();
            st.close();
            conexion.close();
            
        return listaClientes;
    }
    
    //Init para el JSP
    public void inicio(ServletConfig config){
        
        try {
            String configBundleName = config.getInitParameter("app.config");
            ResourceBundle rb = ResourceBundle.getBundle(configBundleName);
            this.dbUrl = rb.getString("database.url");
            this.dbUser = rb.getString("database.user");
            this.dbPassword = rb.getString("database.password");
            this.dbPageSize = rb.getString("database.pageSize") == null ? DEFAULT_PAGESIZE : Integer.parseInt(rb.getString("database.pageSize"));

            String driverClassName = rb.getString("database.driver");

            Class.forName(driverClassName);

        } catch (ClassNotFoundException ex) {
            System.out.println("Error de algo en el init uuuuh");
        }
    }
    

    @Override
    public void init(ServletConfig config) throws ServletException {

        super.init(config);

        try {
            String configBundleName = config.getInitParameter("app.config");
            ResourceBundle rb = ResourceBundle.getBundle(configBundleName);
            this.dbUrl = rb.getString("database.url");
            this.dbUser = rb.getString("database.user");
            this.dbPassword = rb.getString("database.password");
            this.dbPageSize = rb.getString("database.pageSize") == null ? DEFAULT_PAGESIZE : Integer.parseInt(rb.getString("database.pageSize"));

            String driverClassName = rb.getString("database.driver");

            Class.forName(driverClassName);

        } catch (ClassNotFoundException ex) {
            System.out.println("Error de algo en el init uuuuh");
        }

    }

}
