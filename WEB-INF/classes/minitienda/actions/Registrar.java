package minitienda.actions;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import minitienda.application.TipoTarjeta;
import minitienda.application.Usuario;
import minitienda.database.DBFront;

import java.io.IOException;

public class Registrar extends HttpServlet {
    private DBFront db;
    private static final String REGISTRO_JSP = "/WEB-INF/jsp/loginRegistro.jsp";

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
        String email = request.getParameter("email");
        String clave = request.getParameter("clave");
        String claveRepetir = request.getParameter("clave-repetir");
        String numTarjeta = request.getParameter("num-tarjeta");
        TipoTarjeta tipoTarjeta = TipoTarjeta.valueOf(request.getParameter("tipo-tarjeta"));
        HttpSession session = request.getSession(true);

        // Comprobamos que el usuario sea un email
        if (!email.contains("@")) {
            session.setAttribute("errorRegistro", "El usuario debe ser un email");
            gotoPage(REGISTRO_JSP, request, response);
            return;
        }

        // Comprobamos que las claves coinciden
        if (!clave.equals(claveRepetir)) {
            session.setAttribute("errorRegistroClave", "Las contraseñas no coinciden");
            gotoPage("/WEB-INF/jsp/loginRegistro.jsp", request, response);
            return;
        }

        // Comprobamos que la tarjeta es válida
        if (numTarjeta.length() != 16) {
            session.setAttribute("errorRegistroTarjeta", "El número de tarjeta debe tener 16 dígitos");
            gotoPage("/WEB-INF/jsp/loginRegistro.jsp", request, response);
            return;
        }

        Usuario usuario = new Usuario(email, tipoTarjeta, numTarjeta);
        // Guardamos datos en la sesion
        if (db.register(usuario, clave)) {
            session.setAttribute("usuario", usuario);
        } else {
            // Mostrar mensaje de error
            session.setAttribute("errorRegistro", "El usuario " + email + " ya existe");
            gotoPage(REGISTRO_JSP, request, response);
            return;
        }

        // Reenviamos a la página que muestra los parametros
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
