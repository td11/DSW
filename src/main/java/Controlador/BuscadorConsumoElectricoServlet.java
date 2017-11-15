package Controlador;

import Modelo.Cliente;
import Modelo.Consultas;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
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


    //Variables para conectarse
    Connection conexion = null;

    //ArrayList de objetos
    ArrayList<Cliente> listaclientes = new ArrayList();


    public String devolverLista(){
        StringBuilder sb = new StringBuilder();
        
        try {
            ArrayList<Cliente> lista = new Consultas().devolverLista();    
            for (int i = 0; i < lista.size(); i++) {
                sb.append("<tr>");
                sb.append("<td>"+lista.get(i).getNombre()+" "+lista.get(i).getApellidos()+"</td>");
                sb.append("<td>"+lista.get(i).getProvincia()+"</td>");
                sb.append("<td>"+lista.get(i).getPoblacion()+"</td>");
                sb.append("</tr>");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BuscadorConsumoElectricoServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BuscadorConsumoElectricoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return sb.toString();
        
    }
    
    
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


            //Comprobamos si la conexion no tuvo errores
            if (conexion != null) {
                //Verificamos si el usuario queria buscar un cliente o no
                if (nombre.isEmpty()) {
                    
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = 1;
        int recordsPerPage = 5;
        int noDeResultados;
        int noDePaginas;
        ArrayList<Cliente> lista = null;
        String cliente = request.getParameter("nameToSearch");
        
        if(request.getParameter("paginas") != null)
            page = Integer.parseInt(request.getParameter("paginas"));
        
        
        try {
            
            lista = new Consultas().devolverLista();
            noDeResultados = lista.size();
            noDePaginas = (int) Math.ceil(noDeResultados * 1.0 / recordsPerPage);
            request.setAttribute("numeroDePaginas",noDePaginas);
            request.setAttribute("paginaActual", page);
            request.setAttribute("cliente",cliente);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BuscadorConsumoElectricoServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BuscadorConsumoElectricoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
    /* Cosas que seguir probando 
        try {
            
            ArrayList<Cliente> lista = new Consultas().devolverLista();
            int noDeResultados = lista.size();
            int noDePaginas = (int) Math.ceil(noDeResultados * 1.0 / recordsPerPage);
            request.setAttribute("listaClientes", lista);
            request.setAttribute("numeroDePaginas",noDePaginas);
            request.setAttribute("paginaActual", page);
            request.setAttribute("cliente",cliente);
            RequestDispatcher vista = request.getRequestDispatcher("/vista/JSP/misclientesList.jsp");
            vista.forward(request, response);

            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BuscadorConsumoElectricoServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BuscadorConsumoElectricoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    */
    
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


  

}
