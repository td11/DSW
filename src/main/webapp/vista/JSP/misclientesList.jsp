<%-- 
    Document   : misclientesList
    Created on : 14-nov-2017, 9:17:40
    Author     : Teddy
--%>

<%@page import="Controlador.Controlador"%>
<%@page import="Controlador.BuscadorConsumoElectricoServlet"%>
<%@page import="Modelo.Cliente"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Clientes</title>
    </head>
    <body>
        <h1>Lista Clientes</h1>
        
        <h2>Cliente buscado: ${cliente} </h2> 
        
        <table border="1" cellpadding="5" cellspacing="5">
            
            <tr>
                <th>Nombre</th>
                <th>Provincia</th>
                <th>Poblacion</th>
            </tr>

            <% out.println(); %>
            
            
        </table>
        
        <%--For displaying Previous link except for the 1st page --%>
   <!-- <c:if test="${paginaActual != 1}">
        <td><a href="filas.do?page=${paginaActual - 1}">Previous</a></td>
    </c:if>
 
    <%--For displaying Page numbers. 
    The when condition does not display a link for the current page--%>
    <table border="1" cellpadding="5" cellspacing="5">
        <tr>
            <c:forEach begin="1" end="${numeroDePaginas}" var="i">
                <c:choose>
                    <c:when test="${paginaActual eq i}">
                        <td>${i}</td>
                    </c:when>
                    <c:otherwise>
                        <td><a href="filas.do?page=${i}">${i}</a></td>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </tr>
    </table>
    

    <%--For displaying Next link --%>
    <c:if test="${paginaActual lt numeroDePaginas}">
        <td><a href="clientes.do?page=${paginaActual + 1}">Next</a></td>
    </c:if> -->
                
        
    </body>
</html>

<!-- <c:forEach  items="${listaClientes}" var="filas" >
                <tr>
                    <td>${filas.nombre} ${filas.apellidos}</td>
                    <td>${filas.provincia}</td>
                    <td>${filas.poblacion}</td>
                </tr>
            </c:forEach> -->
