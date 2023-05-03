package minitienda.actions;

import jakarta.servlet.http.HttpServlet;

import minitienda.application.Carrito;
import minitienda.application.ElementoCarrito;
import minitienda.application.Producto;
import minitienda.database.DBFront;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;

public class VerCarrito extends HttpServlet {

    //Carrito carrito;

    private DBFront db;
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        db = DBFront.getInstance();
    }

    // Se ejecuta cuando se envia un formulario con method="get" o sin method
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
    }

    // Se ejecuta cuando se envia un formulario con method="post"
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {

        // Calcular el precio total del carrito
        HttpSession session = request.getSession(true);
        Carrito carrito = (Carrito) session.getAttribute("carrito");
        float precioTotal = 0;
        if (carrito == null) {
            carrito = new Carrito();
            session.setAttribute("carrito", carrito);
        } else {
            for (ElementoCarrito elem : carrito.getElementos()) {
                precioTotal += elem.getProducto().getPrecio() * elem.getCantidad();
            }
        }
        session.setAttribute("precioTotal", precioTotal);

        // Reenviamos a la pagina que muestra los parametros
        gotoPage("/WEB-INF/jsp/carrito.jsp", request, response);
    }

    private void gotoPage(String address, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

    public void destroy() {
    }
}
