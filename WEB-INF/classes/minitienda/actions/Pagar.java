package minitienda.actions;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import minitienda.application.Carrito;
import minitienda.application.Usuario;
import minitienda.database.DBFront;

import java.io.IOException;

public class Pagar extends HttpServlet {
    private DBFront db;
    public void init(ServletConfig config)
            throws ServletException {
        super.init(config);
        db = DBFront.getInstance();
    }

    // Se ejecuta cuando se envia un formulario con method="get" o sin method
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        Carrito carrito = (Carrito) session.getAttribute("carrito");
        if (usuario == null) {
            gotoPage("/WEB-INF/jsp/loginRegistro.jsp", request, response);
            return;
        }
        if (carrito == null) {
            gotoPage("/WEB-INF/jsp/carrito.jsp", request, response);
            return;
        }
        db.hacerPedido(usuario, carrito);
        session.removeAttribute("usuario");
        session.removeAttribute("carrito");
        session.setAttribute("compra", true);

        gotoPage("/WEB-INF/jsp/index.jsp", request, response);
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
