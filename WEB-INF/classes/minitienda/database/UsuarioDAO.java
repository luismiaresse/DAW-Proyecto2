package minitienda.database;

import minitienda.application.TipoTarjeta;
import minitienda.application.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDAO extends AbstractDAO {
    private static final int MAX_EMAIL_LENGHT = 50;
    private static final int CARD_NUMBER_LENGHT = 16;

    public UsuarioDAO(Connection con) {
        super.setConnection(con);
    }

    public static String hashPassword(String plain) {
        return BCrypt.hashpw(plain, BCrypt.gensalt());
    }

    public Usuario login(String email, String clave) {
        // Crea el hash de la clave
        Usuario resultado = null;
        Connection con;
        PreparedStatement stmUsuario;
        ResultSet rsUsuario;

        con = this.getConnection();
        try {
            stmUsuario = con.prepareStatement("select email, pwhash, tipo_tarjeta, num_tarjeta "
                    + "from usuarios "
                    + "where email = ?");
            stmUsuario.setString(1, email);
            rsUsuario = stmUsuario.executeQuery();
            if (rsUsuario.next()) {
                String storedHash = rsUsuario.getString("pwhash");
                if (!BCrypt.checkpw(clave, storedHash)) {
                    System.err.println("Contraseña incorrecta");
                    return null;
                }
                String tipoTarjetaStr = rsUsuario.getString("tipo_tarjeta");
                String numTarjeta = rsUsuario.getString("num_tarjeta");
                TipoTarjeta tipoTarjeta = TipoTarjeta.valueOf(tipoTarjetaStr);
                resultado = new Usuario(email, tipoTarjeta, numTarjeta);
            } else {
                // No existe el usuario, mostrar error
                System.err.println("Usuario: " + email + " no encontrado");
                return null;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
        return resultado;
    }

    public ArrayList<Usuario> searchUser(Usuario usuario) {
        if (usuario == null) {
            System.err.println("Usuario inválido");
            return null;
        }
        String email = usuario.getEmail();
        ArrayList<Usuario> resultado = new ArrayList<>();
        Connection con;
        PreparedStatement stmUsuario = null;
        ResultSet rsUsuario;

        con = this.getConnection();
        try {
            stmUsuario = con.prepareStatement("select email "
                    + "from usuarios "
                    + "where email = ?");
            stmUsuario.setString(1, email);
            rsUsuario = stmUsuario.executeQuery();
            while (rsUsuario.next()) {
                Usuario coinc = new Usuario(rsUsuario.getString("email"));
                resultado.add(coinc);
            }

        } catch (SQLException ex) {
            System.err.println("Error al buscar usuarios: " + ex.getMessage());
            return null;
        }
        return resultado;
    }

    private boolean insertUser(Usuario usuario, String hashedPassword) {
        if (usuario == null) {
            System.err.println("Usuario inválido");
            return false;
        }
        String email = usuario.getEmail();
        String numTarjeta = usuario.getNumTarjeta();
        TipoTarjeta tipoTarjeta = usuario.getTipoTarjeta();
        Connection con;
        PreparedStatement stmUsuario;
        con = this.getConnection();
        try {
            stmUsuario = con.prepareStatement("INSERT INTO public.usuarios VALUES (?, ?, ?, ?)");
            stmUsuario.setString(1, email);
            stmUsuario.setString(2, hashedPassword);
            stmUsuario.setString(3, tipoTarjeta.toString());
            stmUsuario.setString(4, numTarjeta);
            stmUsuario.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error al insertar usuario: " + ex.getMessage());
            return false;
        }
        return true;
    }

    public boolean register(Usuario usuario, String clave) {
        if (usuario == null) {
            System.err.println("Usuario inválido");
            return false;
        }
        String email = usuario.getEmail();
        String numTarjeta = usuario.getNumTarjeta();
        TipoTarjeta tipoTarjeta = usuario.getTipoTarjeta();
        // Comprueba que los datos son válidos
        if (email == null ||  email.equals("")  || email.length() > MAX_EMAIL_LENGHT || clave == null || clave.equals("")
        || numTarjeta == null || numTarjeta.length() != CARD_NUMBER_LENGHT || tipoTarjeta == null) {
            System.err.println("Email o clave inválidos");
            return false;
        }

        // Comprueba si el usuario ya existe
        ArrayList<Usuario> comprob;
        comprob = searchUser(usuario);
        String hashedPassword = hashPassword(clave);
        if (comprob.isEmpty() && insertUser(usuario, hashedPassword)) {
            return true;
        } else {
            System.err.println("El usuario " + email + " ya existe");
            return false;
        }
    }

}
