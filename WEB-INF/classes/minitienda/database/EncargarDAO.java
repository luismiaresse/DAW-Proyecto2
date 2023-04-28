package minitienda.database;

import minitienda.classes.Encargar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EncargarDAO extends AbstractDAO {

    public EncargarDAO(Connection con) {
        super.setConnection(con);
    }

    public ArrayList<Encargar> searchPedido(String[] search) {
        ArrayList<Encargar> resultado = new ArrayList<>();
        Connection con;
        PreparedStatement stmEncargar = null;
        ResultSet rsEncargar;
        try {
            String query = "select precio, cantidad "
                    + "from pedidos "
                    + "where precio = ? and cantidad = ?";

            con = this.getConnection();

            stmEncargar = con.prepareStatement(query);
            stmEncargar.setFloat(1, Float.parseFloat(search[0]));
            stmEncargar.setInt(2, Integer.parseInt(search[1]));

            rsEncargar = stmEncargar.executeQuery();
            while (rsEncargar.next()) {
                Encargar encargar = new Encargar(rsEncargar.getFloat("precio"), rsEncargar.getInt("cantidad"));
                resultado.add(encargar);
            }

        } catch (SQLException ex) {
//            getAppFront().showLabel(ex.getMessage(), Color.RED);
        }
        return resultado;
    }

    public boolean hacerPedido(String[] insert) {

        Connection con;
        PreparedStatement stmEncargar;
        ArrayList<Encargar> pedidos;
        String[] buscar = new String[5];
        for (int i = 0; i < 5; i++) {
            buscar[i] = "";
        }
        buscar[1] = insert[1];

        pedidos = searchPedido(buscar);

        con = this.getConnection();

        try {
            con.setAutoCommit(false);
            if (pedidos.isEmpty()) {
                stmEncargar = con.prepareStatement("insert into pedidos values (?, ?)");
                for (int i = 1; i <= 4; i++) {

                }

            } else {
                int cantidad = 0;
                for (int i = 0; i < pedidos.size(); i++) {
                    cantidad += pedidos.get(i).getCantidad();
                }
//                stmEncargar = con.prepareStatement("insert into encargar values ( ?, ?, cast(now() as date), ?, ?)");
//                for (int i = 1; i <= 4; i++) {
//
//                }

            }
//            stmEncargar.executeUpdate();
            con.commit();
            con.setAutoCommit(true);

        } catch (SQLException ex) {
//            getAppFront().showLabel(ex.getMessage(), Color.RED);
            try {
                // Revierte en caso de fallo (evita error "current transaction is aborted")
                con.rollback();
                con.setAutoCommit(true);
            } catch (SQLException exc) {
                System.out.println(exc.getMessage());
                System.exit(0);
            }
            return false;
        }
        return true;
    }
}
