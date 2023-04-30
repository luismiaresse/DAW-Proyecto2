package minitienda.database;

import minitienda.actions.Constantes;
import minitienda.application.*;

import java.util.ArrayList;
import java.sql.*;

public class DBFront {
    private static DBFront instancia = null;
    private Connection con;
    private UsuarioDAO usuario;
    private EncargarDAO encargar;

    private DBFront() {
        try {
            this.con = DriverManager.getConnection(Constantes.DB_URL, Constantes.DB_USER, Constantes.DB_PASSWORD);
            usuario = new UsuarioDAO(this.con);
            encargar = new EncargarDAO(this.con);
        } catch (SQLException i) {
            System.err.println("No se pudo acceder a la BD: " + i.getMessage());
        }
    }

    public static DBFront getInstance() {
        if (instancia == null) {
            instancia = new DBFront();
        }
        return instancia;
    }

    public Usuario loginRegister(String username, String password) {
        return this.usuario.loginRegister(username, password);
    }

    public ArrayList<Usuario> searchUser(String nombre) {
        return usuario.searchUser(nombre);
    }

    public ArrayList<Encargar> searchPedido(String[] search) {
        return encargar.searchPedido(search);
    }

    public boolean hacerPedido(String[] insert) {
        return encargar.hacerPedido(insert);
    }


}
