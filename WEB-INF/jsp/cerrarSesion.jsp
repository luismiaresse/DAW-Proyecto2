
<%@page session="true" %>
<%@ page contentType="text/html; charset=UTF-8" %>


<!DOCTYPE html>
<html lang="es">
<head>
    <title>Musica para DAW</title>
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
<p>Inicia sesión para poder comprar. Si no tienes cuenta, se creará una automáticamente.</p>
<form id="login-form" action="IniciarSesion" method="post">
    <label for="login-user">Nombre de usuario:</label>
    <input type="text" id="login-user" name="usuario" required placeholder="Nombre">
    <label for="login-password">Contraseña:</label>
    <input type="password" id="login-password" name="clave" required placeholder="Contraseña">
    <input type="submit" value="Iniciar sesión">
</form>
<hr>
<form action="anadirProducto" method="post">
    <b>CD:</b>
    <label>
        <select name="producto">
            <option>Yuan | The Guo Brothers | China | $14.95</option>
            <option>Drums of Passion | Babatunde Olatunji | Nigeria | $16.95</option>
            <option>Kaira | Tounami Diabate| Mali | $16.95</option>
            <option>The Lion is Loose | Eliades Ochoa | Cuba | $13.95</option>
            <option>Dance the Devil Away | Outback | Australia | $14.95</option>
            <option>Record of Changes | Samulnori | Korea | $12.95</option>
            <option>Djelika | Tounami Diabate | Mali | $14.95</option>
            <option>Rapture | Nusrat Fateh Ali Khan | Pakistan | $12.95</option>
            <option>Cesaria Evora | Cesaria Evora | Cape Verde | $16.95</option>
            <option>DAA | GSTIC | Spain | $50.00</option>
        </select>
    </label>
    <b>Cantidad:</b>
    <input type="text" name="cantidad" value="1">
    <p>
    <center>
        <input type="submit" value="Selecciona Producto">
    </center>
</form>
<hr>
</body>
</html>