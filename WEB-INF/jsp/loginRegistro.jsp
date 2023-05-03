
<%@ page session="true" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>


<!DOCTYPE html>
<html lang="es">
<head>
    <title>Inicio o registro - Música para DAW</title>
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
<h1>Inicia sesión para pagar o crea una cuenta si aún no tienes una.</h1>
<h3>Iniciar sesión</h3>
<form id="login-form" action="IniciarSesion" method="post">
    <label for="login-email">Correo electrónico:</label>
    <input type="text" id="login-email" name="email" required placeholder="Email">
    <label for="login-password">Contraseña:</label>
    <input type="password" id="login-password" name="clave" required placeholder="Contraseña">
    <input type="submit" value="Iniciar sesión">
</form>
<hr>
<h3>Crear cuenta</h3>
<form id="register-form" action="Registrar" method="post">
    <label for="register-email">Correo electrónico:</label>
    <input type="text" id="register-email" name="email" required placeholder="Email">
    <label for="register-password">Contraseña:</label>
    <input type="password" id="register-password" name="clave" required placeholder="Contraseña">
    <label for="password-repeat">Repite la contraseña:</label>
    <input type="password" id="password-repeat" name="clave-repetir" required placeholder="Contraseña">
    <label for="card-type">Tipo de tarjeta:</label>
    <select id="card-type" name="tipo-tarjeta">
        <option value="visa">Visa</option>
        <option value="mastercard">MasterCard</option>
    </select>
    <label for="card-number">Número de tarjeta:</label>
    <input type="text" id="card-number" name="num-tarjeta" required placeholder="Número de tarjeta">
    <input type="submit" value="Crear cuenta">
</form>
<hr>
</body>
</html>