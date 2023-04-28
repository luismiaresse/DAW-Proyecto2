package minitienda;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class anadirProducto extends HttpServlet {

    public void init(ServletConfig config)
            throws ServletException {

        super.init(config);
    }

    // Se ejecuta cuando se envia un formulario con method="get" o sin method
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
            // Obtener el HTML de la página del formulario

    }

    // Se ejecuta cuando se envia un formulario con method="post"
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        // Obtenemos el producto a partir de la peticion
        String producto = request.getParameter("producto");
        String cantidad = request.getParameter("cantidad");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("""
                <!DOCTYPE html>
                <html lang="es">
                    <head>
                      <title>Musica para DAA</title>
                    </head>
                    <body bgcolor="#FDF5E6">
                      <table align="center" border="0">
                \t<tr>\s
                \t  <th><IMG SRC="" ALIGN="CENTER"></th>
                \t  <th><font face="Times New Roman,Times" size="+3">Música para DAA</font></th>
                \t  <th><IMG SRC="" ALIGN="CENTER"></th>
                \t</tr>
                      </table>
                      <hr>
                \t<p>
                \t\t<form action="anadirProducto" method="post">
                \t\t  <b>CD:</b>
                \t\t\t<label>
                \t\t\t\t<select name="producto">
                \t\t\t  <option>Yuan | The Guo Brothers | China | $14.95</option>
                \t\t\t  <option>Drums of Passion | Babatunde Olatunji | Nigeria | $16.95</option>
                \t\t\t  <option>Kaira | Tounami Diabate| Mali | $16.95</option>
                \t\t\t  <option>The Lion is Loose | Eliades Ochoa | Cuba | $13.95</option>
                \t\t\t  <option>Dance the Devil Away | Outback | Australia | $14.95</option>
                \t\t\t  <option>Record of Changes | Samulnori | Korea | $12.95</option>
                \t\t\t  <option>Djelika | Tounami Diabate | Mali | $14.95</option>
                \t\t\t  <option>Rapture | Nusrat Fateh Ali Khan | Pakistan | $12.95</option>
                \t\t\t  <option>Cesaria Evora | Cesaria Evora | Cape Verde | $16.95</option>
                \t\t\t  <option>DAA | GSTIC | Spain | $50.00</option>
                \t\t\t\t</select>
                \t\t\t</label>
                \t\t\t<b>Cantidad:</b>
                \t\t  <input type="text" name="cantidad" value="1">
                \t\t<p>
                \t\t  <center>
                \t\t\t<input type="submit" value="Selecciona Producto">
                \t\t  </center>
                \t\t</form>
                \t  <hr>
                \t <p> Producto: %s<br>Cantidad: %s</p>
                </body>
                </html>""".formatted(producto, cantidad));


    }

    public void destroy() {
    }
}
