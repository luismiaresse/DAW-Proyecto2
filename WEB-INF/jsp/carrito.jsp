
<%@ page session="true" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%-- Usar EL e importar JSTL --%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="carrito" value="${sessionScope.carrito}"/>

<html>
<head>
    <title>Carrito - Música para DAW</title>
    <meta charset="UTF-8">
</head>
<body style="background-color: #FDF5E6">
    <h1>Carrito</h1>
    <c:choose>
    <c:when test="${not empty carrito}">
        <table border="1">
            <tr>
                <th>Nombre</th>
                <th>Precio ud.</th>
                <th>Cantidad</th>
                <th>Subtotal</th>
                <th>Acción</th>
            </tr>
            <c:forEach items="${carrito.elementos}" var="elem">
                <tr>
                    <td>${elem.producto.nombre}</td>
                    <td>${elem.producto.precio}</td>
                    <td>${elem.cantidad}</td>
                    <td>${elem.subtotal}</td>
<%--                    <td><a href="Eliminar?indice=${elem.indice}">Eliminar</a></td>--%>
                </tr>
            </c:forEach>
        </table>
        <p>Total: ${sessionScope.precioTotal}</p>
        <p><a href="Pagar">Pagar</a></p>
    </c:when>
    <c:otherwise>
        <p>El carrito está vacío</p>
    </c:otherwise>
    </c:choose>
    <p><a href="${pageContext.request.contextPath}">Volver</a></p>
<hr>
</body>
</html>
