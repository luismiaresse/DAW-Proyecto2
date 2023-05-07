<%@ page session="true" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%-- Usar EL e importar JSTL --%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<html>
<head>
    <title>Pago - Música para DAW</title>
    <meta charset="UTF-8">
</head>
<body style="background-color: #FDF5E6; margin: 3%">
    <center>
        <h1>Caja</h1>
    </center>
    <hr>
    <h2>Resumen de la compra</h2>
    <p>Cuenta a la que se realiza el cargo: <c:out value="${sessionScope.usuario.email}"/></p>
    <p>Importe: <c:out value="${sessionScope.precioTotal}"/>€</p>
    <p>Número de tarjeta: <c:out value="${sessionScope.usuario.numTarjetaOculto}"/></p>
    <p>Tipo de tarjeta: <c:out value="${sessionScope.usuario.tipoTarjeta}"/></p>
    <p><a href="Pagar">Confirmar compra y volver al inicio</a></p>
    <p><a href="VerCarrito">Volver al carrito</a></p>
    <hr>
</body>
</html>
