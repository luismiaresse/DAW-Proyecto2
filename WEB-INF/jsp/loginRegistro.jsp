
<%@ page session="true" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="es">
<head>
    <title>Iniciar sesión - Música para DAW</title>
    <meta charset="UTF-8">
</head>
<body style="background-color: #FDF5E6; margin: 3%">
    <center>
        <h1>Inicia sesión para pagar</h1>
    </center>
    <hr>
    <div style="margin: 0 auto; width: 50%">
        <div style="display: flex; flex-direction: row;">
            <div style="max-width: 75%; width: 100%;">
                <h4>Iniciar sesión</h4>
                <form id="login-form" action="IniciarSesion" method="post" style="display: flex; flex-direction: column; max-width: 75%">
                    <label for="login-email">Correo electrónico:</label>
                    <input type="text" id="login-email" name="email" required placeholder="Email">
                    <br>
                    <label for="login-password">Contraseña:</label>
                    <input type="password" id="login-password" name="clave" required placeholder="Contraseña">
                    <br>
                    <input type="submit" value="Iniciar sesión">
                    <c:if test="${not empty sessionScope.errorLogin != null}">
                        <p style="color: red">${sessionScope.errorLogin}</p>
                        <c:remove var="errorLogin" scope="session"/>
                    </c:if>
                </form>
            </div>
            <div style="max-width: 75%; width: 100%;">
                <h4>Crear cuenta</h4>
                <form id="register-form" action="Registrar" method="post" style="display: flex; flex-direction: column; max-width: 75%">
                    <label for="register-email">Correo electrónico:</label>
                    <input type="text" id="register-email" name="email" required placeholder="Email">
                    <br>
                    <label for="register-password">Contraseña:</label>
                    <input type="password" id="register-password" name="clave" required placeholder="Contraseña">
                    <br>
                    <label for="password-repeat">Repite la contraseña:</label>
                    <input type="password" id="password-repeat" name="clave-repetir" required placeholder="Contraseña">
                    <br>
                    <label for="card-type">Tipo de tarjeta:</label>
                    <select id="card-type" name="tipo-tarjeta">
                        <option value="VISA">Visa</option>
                        <option value="MASTERCARD">MasterCard</option>
                    </select>
                    <br>
                    <label for="card-number">Número de tarjeta:</label>
                    <input type="text" id="card-number" name="num-tarjeta" required placeholder="Número de tarjeta (16 números sin separación)">
                    <br>
                    <input type="submit" value="Crear cuenta">
                </form>
                <c:choose>
                    <c:when test="${not empty sessionScope.errorRegistro}">
                        <p style="color: red">${sessionScope.errorRegistro}</p>
                        <c:remove var="errorRegistro" scope="session"/>
                    </c:when>
                    <c:when test="${not empty sessionScope.errorRegistroClave}">
                        <p style="color: red">${sessionScope.errorRegistroClave}</p>
                        <c:remove var="errorRegistroClave" scope="session"/>
                    </c:when>
                    <c:when test="${not empty sessionScope.errorRegistroTarjeta}">
                        <p style="color: red">${sessionScope.errorRegistroTarjeta}</p>
                        <c:remove var="errorRegistroTarjeta" scope="session"/>
                    </c:when>
                </c:choose>
            </div>
        </div>
    </div>
    <hr>
</body>
</html>