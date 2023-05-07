package minitienda.actions;

import jakarta.servlet.http.HttpServlet;

import minitienda.database.DBFront;
import minitienda.application.Carrito;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;

public class EliminarProducto extends HttpServlet {

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    // Se ejecuta cuando se envia un formulario con method="get" o sin method
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        // Obtenemos el producto y la cantidad a partir de la peticion
        int idProducto = Integer.parseInt(request.getParameter("idProducto"));
        HttpSession session = request.getSession(true);

        // Obtenemos el carrito
        Carrito carrito = (Carrito) session.getAttribute("carrito");
        if (carrito == null) {
            // El carrito no existe, no se puede eliminar
            return;
        }
        carrito.eliminarProducto(idProducto);
        session.setAttribute("carrito", carrito);

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
