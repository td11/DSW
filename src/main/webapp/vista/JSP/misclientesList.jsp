<%-- 
    Document   : misclientesList
    Created on : 14-nov-2017, 9:17:40
    Author     : Teddy
--%>

<%@page import="Controlador.BuscadorConsumoElectricoServlet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% String lista = new BuscadorConsumoElectricoServlet().devolverContenido(Integer.parseInt(request.getAttribute("paginas").toString())); %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Clientes</title>
        <link rel="stylesheet" href="CSS/respuestaCliente.css" type="text/css"/>
    </head>
    <body>
        <h1>Lista Clientes</h1>

        <h2>Cliente buscado: ${cliente} </h2> 

        <table>
            <thead>
                <tr>
                    <th>Nombre</th>
                    <th>Provincia</th>
                    <th>Poblacion</th>
                </tr>
            </thead>

            <tbody>
                <% out.println(lista);%>
            </tbody>

        </table>

        <section class="paginacion">
            <ul>
                <li><a href="#" class="active">1</a></li>
                <li><a href="#">2</a></li>
                <li><a href="#">3</a></li>
                <li><a href="#">4</a></li>
                <li><a href="#">5</a></li>
            </ul>
        </section>

    </body>
</html>


