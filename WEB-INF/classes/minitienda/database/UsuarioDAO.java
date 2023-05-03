package minitienda.database;

import minitienda.application.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDAO extends AbstractDAO {
    private static final int MAX_USERNAME_LENGHT = 50;

    public UsuarioDAO(Connection con) {
        super.setConnection(con);
    }

    private String hashPassword(String plain) {
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
            stmUsuario = con.prepareStatement("select email, pwhash "
                    + "from usuarios "
                    + "where email = ?");
            stmUsuario.setString(1, email);
            rsUsuario = stmUsuario.executeQuery();
            if (rsUsuario.next()) {
                System.err.println("Usuario: " + rsUsuario.getString("email") + " encontrado");
                String storedHash = rsUsuario.getString("pwhash");
                if (!BCrypt.checkpw(clave, storedHash)) {
                    System.err.println("Contraseña incorrecta");
                    return null;
                }
                resultado = new Usuario(email, storedHash);
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

    public ArrayList<Usuario> searchUser(String email) {
        ArrayList<Usuario> resultado = new ArrayList<>();
        Connection con;
        PreparedStatement stmUsuario = null;
        ResultSet rsUsuario;

        con = this.getConnection();
        try {
            stmUsuario = con.prepareStatement("select email, pwhash "
                    + "from usuarios "
                    + "where email = ?");
            stmUsuario.setString(1, email);
            rsUsuario = stmUsuario.executeQuery();
            while (rsUsuario.next()) {
                Usuario usuario = new Usuario(rsUsuario.getString("email"), rsUsuario.getString("pwhash"));
                resultado.add(usuario);
            }

        } catch (SQLException ex) {
            System.err.println("Error al buscar usuarios: " + ex.getMessage());
            return null;
        }
        return resultado;
    }

    private boolean insertUser(String email, String hashedPassword) {
        Connection con;
        PreparedStatement stmUsuario;
        con = this.getConnection();
        try {
            stmUsuario = con.prepareStatement("INSERT INTO public.usuarios VALUES (?, ?)");
            stmUsuario.setString(1, email);
            stmUsuario.setString(2, hashedPassword);
            stmUsuario.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error al insertar usuario: " + ex.getMessage());
            return false;
        }
        return true;
    }

    private Usuario registerUser(String email, String clave) {
        if (email == null || clave == null || email.equals("") || clave.equals("") || email.length() > MAX_USERNAME_LENGHT) {
            System.err.println("Email o clave inválidos");
            return null;
        }

        // Comprueba si el usuario ya existe
        ArrayList<Usuario> comprob;
        comprob = searchUser(email);
        String hashedPassword = hashPassword(clave);
        if (comprob.isEmpty() && insertUser(email, hashedPassword)) {
            return new Usuario(email, hashedPassword);
        } else {
            System.err.println("El usuario " + email + " ya existe");
            return null;
        }
    }

}
