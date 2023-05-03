package minitienda.actions;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import minitienda.application.Usuario;
import minitienda.database.DBFront;

import java.io.IOException;

public class IniciarSesion extends HttpServlet {
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
    }

    // Se ejecuta cuando se envia un formulario con method="post"
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        // Obtenemos el usuario y la clave a partir de la peticion
        String usuario = request.getParameter("email");
        String clave = request.getParameter("clave");

        HttpSession session = request.getSession(true);
        // Limpiamos un posible error anterior
        session.removeAttribute("error-login");
        Usuario user = db.login(usuario, clave);
        // Guardamos datos en la sesion
        if (user != null) {
            System.err.println("Usuario: " + user.getEmail() + " Clave: " + user.getClave());
            session.setAttribute("usuario", user.getEmail());
        } else {
            // Mostrar mensaje de error
            session.setAttribute("error-login", "Contraseña incorrecta para el usuario " + usuario);
        }


        // Reenviamos a la pagina que muestra los parametros
        gotoPage("/WEB-INF/jsp/loginRegistro.jsp", request, response);

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
