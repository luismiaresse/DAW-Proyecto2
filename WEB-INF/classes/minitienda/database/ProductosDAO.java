package minitienda.database;

import minitienda.application.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductosDAO extends AbstractDAO {

    public ProductosDAO(Connection con) {
        super.setConnection(con);
    }

    public ArrayList<Producto> getProducts() {
        ArrayList<Producto> resultado = new ArrayList<>();
        Connection con;
        PreparedStatement stmProds = null;
        ResultSet rsProds;
        con = this.getConnection();
        try {
            String query = "select id, nombre, autor, pais, precio "
                    + "from productos ";

            stmProds = con.prepareStatement(query);
            rsProds = stmProds.executeQuery();
            while (rsProds.next()) {
                Producto prods = new Producto(rsProds.getInt("id"),
                        rsProds.getString("nombre"),
                        rsProds.getString("autor"),
                        rsProds.getString("pais"),
                        rsProds.getFloat("precio"));
                resultado.add(prods);
            }

        } catch (SQLException ex) {
            System.err.println("No se pudo obtener los productos: " + ex.getMessage());
            return null;
        }
        return resultado;
    }

    public Producto getProductById(int prodId) {
        Producto resultado = null;
        Connection con;
        PreparedStatement stmProd = null;
        ResultSet rsProds;
        con = this.getConnection();
        try {
            String query = "select id, nombre, autor, pais, precio "
                    + "from productos "
                    + "where id = ?";
            stmProd = con.prepareStatement(query);
            stmProd.setInt(1, prodId);
            rsProds = stmProd.executeQuery();
            if (!rsProds.next()) {
                System.err.println("Producto: " + prodId + " no encontrado");
                return null;
            }
            resultado = new Producto(rsProds.getInt("id"),
                    rsProds.getString("nombre"),
                    rsProds.getString("autor"),
                    rsProds.getString("pais"),
                    rsProds.getFloat("precio"));

        } catch (SQLException ex) {
            System.err.println("No se pudo obtener el producto: " + ex.getMessage());
            return null;
        }
        return resultado;
    }

}
