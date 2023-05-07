package minitienda.actions;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import minitienda.application.Carrito;
import minitienda.application.Usuario;
import minitienda.database.DBFront;

import java.io.IOException;

public class IniciarSesion extends HttpServlet {
    private DBFront db;
    private static final String LOGIN_JSP = "/WEB-INF/jsp/loginRegistro.jsp";
    public void init(ServletConfig config)
            throws ServletException {
        super.init(config);
        db = DBFront.getInstance();
    }

    // Se ejecuta cuando se envia un formulario con method="get" o sin method
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        gotoPage(LOGIN_JSP, request, response);
    }

    // Se ejecuta cuando se envia un formulario con method="post"
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        // Obtenemos el usuario y la clave a partir de la peticion
        String email = request.getParameter("email");
        String clave = request.getParameter("clave");
        HttpSession session = request.getSession(true);
        // Comprobamos que el usuario sea un email
        if (!email.contains("@")) {
            session.setAttribute("errorLogin", "El usuario debe ser un email");
            gotoPage(LOGIN_JSP, request, response);
            return;
        }
        // Limpiamos un posible error anterior
        session.removeAttribute("errorLogin");
        Usuario usuario = db.login(email, clave);
        // Guardamos datos en la sesion
        if (usuario != null) {
            session.setAttribute("usuario", usuario);
        } else {
            // Mostrar mensaje de error
            session.setAttribute("errorLogin", "Contraseña incorrecta para el usuario " + email);
            gotoPage(LOGIN_JSP, request, response);
            return;
        }

        // Reenviamos a la pagina que muestra los parametros
        gotoPage("/WEB-INF/jsp/caja.jsp", request, response);
    }

    private void gotoPage(String address, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

    public void destroy() {
    }
}
