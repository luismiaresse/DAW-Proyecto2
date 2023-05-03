<%@ page import="java.util.ArrayList" %>
<%@ page import="minitienda.application.Producto" %>
<%@ page import="minitienda.actions.ObtenerProductos" %>
<%@ page session="true" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%-- Usar EL e importar JSTL --%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- Recuperar la lista de productos de la DB --%>
<c:set var="productos" value="<%= new ObtenerProductos().getProducts() %>"/>


<!DOCTYPE html>
<html lang="es">
<head>
    <title>Música para DAW</title>
    <meta charset="UTF-8">
</head>
<body bgcolor="#FDF5E6">
<table align="center" border="0">
    <tr>
        <th><IMG SRC="" ALIGN="CENTER"></th>
        <th><font face="Times New Roman,Times" size="+3">Música para DAW</font></th>
        <th><IMG SRC="" ALIGN="CENTER"></th>
    </tr>
</table>
<hr>
<form id="login-form" action="IniciarSesion" method="post">
    <label for="login-email">Correo electrónico:</label>
    <input type="text" id="login-email" name="email" required placeholder="Email">
    <label for="login-password">Contraseña:</label>
    <input type="password" id="login-password" name="clave" required placeholder="Contraseña">
    <input type="submit" value="Iniciar sesión">
</form>
<hr>
<form action="anadirProducto" method="post">
    <b>CD:</b>
    <label>
        <select name="idProducto">
            <c:forEach items="${productos}" var="producto">
                <option value="${producto.id}">${producto.nombre} | ${producto.autor}
                    | ${producto.pais} | $${producto.precio}</option>
            </c:forEach>
        </select>
    </label>
    <b>Cantidad:</b>
    <input type="text" name="cantidad" value="1">
    <center>
        <input type="submit" value="Añadir producto">
    </center>
</form>
<form action="VerCarrito" method="post">
    <center>
        <input type="submit" value="Ver carrito">
    </center>
</form>
<hr>
</body>
</html>