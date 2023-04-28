package minitienda.database;

import java.awt.Color;
import java.sql.Connection;
import java.sql.Date;
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
            String query = "select dni_monitor, nombre_material, fecha, precio, cantidad "
                    + "from encargar "
                    + "where dni_monitor like ? and nombre_material like ?";

            if (!search[2].equals("")) {
                query += " and fecha = ?";
            } else {
                query += " and fecha > ?";
            }
            if (!search[3].equals("")) {
                query += " and precio = ?";
            } else {
                query += "and precio >= ?";
            }
            if (!search[4].equals("")) {
                query += " and cantidad = ?";
            } else {
                query += " and cantidad > ?";
            }
            con = this.getConnection();

            stmEncargar = con.prepareStatement(query);
            for (int i = 1; i <= 5; i++) {
                switch (i) {
                    case 1:
                    case 2:
                        stmEncargar.setString(i, "%" + search[i - 1] + "%");
                        break;
                    case 3:
                        if (!search[i - 1].equals("")) {
                            stmEncargar.setDate(i, java.sql.Date.valueOf(search[i - 1]));
                        } else {
                            stmEncargar.setDate(i, java.sql.Date.valueOf("1970-01-01"));
                        }
                        break;
                    case 4:
                        if (!search[i - 1].equals("")) {
                            stmEncargar.setFloat(i, Float.valueOf(search[i - 1]));
                        } else {
                            stmEncargar.setFloat(i, Float.valueOf(0));
                        }
                        break;
                    case 5:
                        if (!search[i - 1].equals("")) {
                            stmEncargar.setInt(i, Integer.valueOf(search[i - 1]));
                        } else {
                            stmEncargar.setInt(i, 0);
                        }
                        break;
                }
            }

            rsEncargar = stmEncargar.executeQuery();
            while (rsEncargar.next()) {
                Encargar encargar = new Encargar(rsEncargar.getString("dni_monitor"), rsEncargar.getString("nombre_material"), rsEncargar.getDate("fecha"), rsEncargar.getFloat("precio"), rsEncargar.getInt("cantidad"));
                resultado.add(encargar);
            }

        } catch (SQLException ex) {
            getAppFront().showLabel(ex.getMessage(), Color.RED);
        }
        return resultado;
    }

    public boolean hacerPedido(String[] insert) {

        if (insert[1].equals("") || insert[2].equals("") || insert[3].equals("")) {
            this.getAppFront().showLabel("Introduce los campos obligatorios", Color.RED);
            return false;
        }

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
                stmEncargar = con.prepareStatement("insert into encargar values ( ?, ?, cast(now() as date), ?, ?)");
                for (int i = 1; i <= 4; i++) {
                    switch (i) {
                        case 1:
                        case 2:
                            stmEncargar.setString(i, insert[i - 1]);
                            break;
                        case 3:
                            stmEncargar.setFloat(i, Float.valueOf(insert[i - 1]));
                            break;
                        case 4:
                            if (Integer.valueOf(insert[i - 1]) <= 50) {
                                stmEncargar.setInt(i, Integer.valueOf(insert[i - 1]));
                            } else {
                                stmEncargar.setInt(i, 50);
                            }
                            break;
                    }
                }

            } else {
                int cantidad = 0;
                Date hoy = new Date(System.currentTimeMillis());
                for (int i = 0; i < pedidos.size(); i++) {
                    cantidad += pedidos.get(i).getCantidad();
                    if (pedidos.get(i).getMonitor().equals(super.getAppFront().getUser().getDni()) && pedidos.get(i).getFecha().toString().equals(hoy.toString())) {
                        this.getAppFront().showLabel("Pedido ya realizado", Color.RED);
                        return false;
                    }
                }
                if (cantidad < 50) {

                    stmEncargar = con.prepareStatement("insert into encargar values ( ?, ?, cast(now() as date), ?, ?)");
                    for (int i = 1; i <= 4; i++) {
                        switch (i) {
                            case 1:
                            case 2:
                                stmEncargar.setString(i, insert[i - 1]);
                                break;
                            case 3:
                                stmEncargar.setFloat(i, Float.valueOf(insert[i - 1]));
                                break;
                            case 4:
                                if (Integer.valueOf(insert[i - 1]) + cantidad <= 50) {
                                    stmEncargar.setInt(i, Integer.valueOf(insert[i - 1]));
                                } else {
                                    stmEncargar.setInt(i, 50 - cantidad);
                                }
                                break;
                        }
                    }
                } else {
                    this.getAppFront().showLabel("Unidades máximas pedidas de ese material", Color.RED);
                    return false;
                }
            }
            stmEncargar.executeUpdate();
            con.commit();
            con.setAutoCommit(true);

        } catch (SQLException ex) {
            getAppFront().showLabel(ex.getMessage(), Color.RED);
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
