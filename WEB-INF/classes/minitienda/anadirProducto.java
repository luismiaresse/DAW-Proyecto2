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

        // Obtenemos el producto y la cantidad a partir de la peticion
        String producto = request.getParameter("producto");
        String cantidad = request.getParameter("cantidad");

        HttpSession session = request.getSession(true);

        // Guardamos datos en la sesion
        session.setAttribute("producto", producto );
        session.setAttribute("cantidad", cantidad );

        // Reenviamos a la pagina que muestra los parametros
        gotoPage("/WEB-INF/classes/minitienda/verProductos.jsp", request, response);
    }

    // /WEB-INF/classes/minitienda

    private void gotoPage(String address, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

    public void destroy() {
    }
}
