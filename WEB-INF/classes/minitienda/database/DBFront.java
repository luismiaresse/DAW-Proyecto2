package minitienda.database;

import minitienda.application.*;

import java.util.ArrayList;
import java.sql.*;

public class DBFront {
    // Constantes
    private static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/minitienda";
    private static final String DB_USER = "luismi";
    private static final String DB_PASSWORD = "luismi";

    // Singleton
    private static DBFront instancia = null;

    // Atributos
    private Connection con;
    private UsuarioDAO usuario;
    private EncargarDAO encargar;
    private ProductosDAO productos;

    private DBFront() {
        try {
            this.con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            usuario = new UsuarioDAO(this.con);
            encargar = new EncargarDAO(this.con);
            productos = new ProductosDAO(this.con);
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

    public Usuario login(String username, String password) {
        return this.usuario.login(username, password);
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

    public ArrayList<Producto> getProducts() {
        return productos.getProducts();
    }

    public Producto getProductById(int prodId) {
        return productos.getProductById(prodId);
    }


}
