package minitienda.actions;

import jakarta.servlet.http.HttpServlet;

import minitienda.database.DBFront;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;

public class anadirProducto extends HttpServlet {

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
        // Obtenemos el producto y la cantidad a partir de la peticion
        String producto = request.getParameter("producto");
        String cantidad = request.getParameter("cantidad");

        HttpSession session = request.getSession(true);

//
//        String producto = request.getParameter("producto");
//
//        HttpSession session = request.getSession(true);

        // Reenviamos a la pagina que muestra los parametros
        gotoPage(Constantes.JSP_PATH + "/verProductos.jsp", request, response);

        //carrito.anadirProducto();
    }

    private void gotoPage(String address, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

    public void destroy() {
    }
}
