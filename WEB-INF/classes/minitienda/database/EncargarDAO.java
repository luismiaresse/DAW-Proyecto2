package minitienda.database;

import minitienda.application.Carrito;
import minitienda.application.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class EncargarDAO extends AbstractDAO {

    public EncargarDAO(Connection con) {
        super.setConnection(con);
    }

    public boolean hacerPedido(Usuario usuario, Carrito carrito) {
        Connection con;
        PreparedStatement stmEncargar;
        con = super.getConnection();
        float precio = carrito.getPrecioTotal();
        try {
            stmEncargar = con.prepareStatement("INSERT INTO encargar (precio, usuario) VALUES (?, ?)");
            stmEncargar.setFloat(1, precio);
            stmEncargar.setString(2, usuario.getEmail());
            stmEncargar.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println("Error al hacer el pedido: " + e.getMessage());
            return false;
        }

    }
}
