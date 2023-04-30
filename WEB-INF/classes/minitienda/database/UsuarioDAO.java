package minitienda.database;

import minitienda.actions.Constantes;
import minitienda.application.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDAO extends AbstractDAO {

    public UsuarioDAO(Connection con) {
        super.setConnection(con);
    }

    private String hashPassword(String plain) {
        return BCrypt.hashpw(plain, BCrypt.gensalt());
    }

    public Usuario loginRegister(String nombre, String clave) {
        // Crea el hash de la clave
        Usuario resultado = null;
        Connection con;
        PreparedStatement stmUsuario;
        ResultSet rsUsuario;

        con = this.getConnection();
        try {
            stmUsuario = con.prepareStatement("select nombre, pwhash "
                    + "from usuarios "
                    + "where nombre = ?");
            stmUsuario.setString(1, nombre);
            rsUsuario = stmUsuario.executeQuery();
            if (rsUsuario.next()) {
                System.err.println("Usuario: " + rsUsuario.getString("nombre") + " encontrado");
                String storedHash = rsUsuario.getString("pwhash");
                if (!BCrypt.checkpw(clave, storedHash)) {
                    System.err.println("Contraseña incorrecta");
                    return null;
                }
                resultado = new Usuario(nombre, storedHash);
            } else {
                // No existe el usuario, lo registramos
                resultado = registerUser(nombre, clave);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
        return resultado;
    }

    public ArrayList<Usuario> searchUser(String nombre) {
        ArrayList<Usuario> resultado = new ArrayList<>();
        Connection con;
        PreparedStatement stmUsuario = null;
        ResultSet rsUsuario;

        con = this.getConnection();
        try {
            stmUsuario = con.prepareStatement("select nombre, pwhash "
                    + "from usuarios "
                    + "where nombre = ?");
            stmUsuario.setString(1, nombre);
            rsUsuario = stmUsuario.executeQuery();
            while (rsUsuario.next()) {
                Usuario usuario = new Usuario(rsUsuario.getString("nombre"), rsUsuario.getString("pwhash"));
                resultado.add(usuario);
            }

        } catch (SQLException ex) {
            System.err.println("Error al buscar usuarios: " + ex.getMessage());
            return null;
        }
        return resultado;
    }

    private boolean insertUser(String nombre, String hashedPassword) {
        Connection con;
        PreparedStatement stmUsuario;
        con = this.getConnection();
        try {
            stmUsuario = con.prepareStatement("INSERT INTO public.usuarios VALUES (?, ?)");
            stmUsuario.setString(1, nombre);
            stmUsuario.setString(2, hashedPassword);
            stmUsuario.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error al insertar usuario: " + ex.getMessage());
            try {
                // Revierte en caso de fallo (evita error "current transaction is aborted")
                con.rollback();
            } catch (SQLException exc) {
                System.err.println("Error al revertir la transacción: " + exc.getMessage());
            }
            return false;
        }
        return true;
    }

    private Usuario registerUser(String nombre, String clave) {
        if (nombre == null || clave == null || nombre.equals("") || clave.equals("") || nombre.length() > Constantes.MAX_USERNAME_LENGHT) {
            System.err.println("Nombre o clave inválidos");
            return null;
        }

        // Comprueba si el usuario ya existe
        ArrayList<Usuario> comprob;
        comprob = searchUser(nombre);
        String hashedPassword = hashPassword(clave);
        if (comprob.isEmpty() && insertUser(nombre, hashedPassword)) {
            return new Usuario(nombre, hashedPassword);
        } else {
            System.err.println("El usuario " + nombre + " ya existe");
            return null;
        }
    }

}
