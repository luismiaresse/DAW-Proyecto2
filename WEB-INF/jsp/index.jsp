<%@ page import="java.util.ArrayList" %>
<%@ page import="minitienda.application.Producto" %>
<%@ page import="minitienda.actions.ObtenerProductos" %>
<%@ page session="true" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%-- Usar EL e importar JSTL --%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- Recuperar la lista de productos de la BD --%>
<c:set var="productos" value="<%= new ObtenerProductos().getProducts() %>"/>


<!DOCTYPE html>
<html lang="es">
<head>
    <title>Música para DAW</title>
    <meta charset="UTF-8">
</head>
<body style="background-color: #FDF5E6; margin: 3%">
<table align="center" border="0">
    <tr>
        <th><IMG SRC="" ALIGN="CENTER"></th>
        <th><font face="Times New Roman,Times" size="+3">Música para DAW</font></th>
        <th><IMG SRC="" ALIGN="CENTER"></th>
    </tr>
</table>
<hr>
<form action="AnadirProducto" method="post">
    <b>CD:</b>
    <label>
        <select name="idProducto">
            <c:forEach items="${productos}" var="producto">
                <option value="${producto.id}">${producto.nombre} | ${producto.autor}
                    | ${producto.pais} | ${producto.precio}€</option>
            </c:forEach>
        </select>
    </label>
    <b>Cantidad:</b>
    <input type="text" name="cantidad" value="1">
    <br>
    <c:if test="${not empty sessionScope.anadido}">
        <font color="green">Producto añadido al carrito correctamente</font>
        <c:remove var="anadido" scope="session"/>
    </c:if>
    <br>
    <center>
        <input type="submit" value="Añadir producto">
    </center>
</form>
<br>
<center>
    <button onclick="location.href='VerCarrito'">Ver carrito</button>
</center>
<hr>
<c:if test="${not empty sessionScope.compra}">
    <font color="green">Compra realizada, gracias por confiar en nosotros</font>
    <c:remove var="compra" scope="session"/>
</c:if>
</body>
</html>