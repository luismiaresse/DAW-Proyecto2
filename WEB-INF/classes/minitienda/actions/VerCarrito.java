package minitienda.actions;

import minitienda.application.Carrito;
import minitienda.application.ElementoCarrito;
import minitienda.application.Producto;
import minitienda.database.DBFront;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.logging.Logger;

public class VerCarrito extends HttpServlet {

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    // Se ejecuta cuando se envia un formulario con method="get" o sin method
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        // Calcular el precio total del carrito
        HttpSession session = request.getSession(true);
        Carrito carrito = (Carrito) session.getAttribute("carrito");
        float precioTotal = 0;
        if (carrito != null) {
            precioTotal = carrito.getPrecioTotal();
        }
        // Truncamos el precio a dos decimales
        precioTotal = (float) (Math.round(precioTotal * 100.0) / 100.0);
        session.setAttribute("precioTotal", precioTotal);

        // Reenviamos a la pagina que muestra los parametros
        gotoPage("/WEB-INF/jsp/carrito.jsp", request, response);
    }

    // Se ejecuta cuando se envia un formulario con method="post"
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {

    }

    private void gotoPage(String address, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

    public void destroy() {
    }
}
