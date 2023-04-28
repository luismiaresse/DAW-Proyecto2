package minitienda.database;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class UsuarioDAO extends AbstractDAO {

    public UsuarioDAO(Connection con) {
        super.setConnection(con);
    }

    public Usuario login(String dni, String clave) {
        Usuario resultado = null;
        Connection con;
        PreparedStatement stmUsuario;
        ResultSet rsUsuario;

        con = this.getConnection();
        try {
            stmUsuario = con.prepareStatement("select nombre, clave "
                    + "from usuarios "
                    + "where dni = ? and clave = ?");
            stmUsuario.setString(1, dni);
            stmUsuario.setString(2, clave);
            rsUsuario = stmUsuario.executeQuery();
            if (rsUsuario.next()) {
                resultado = new Usuario(rsUsuario.getString("nombre"), rsUsuario.getString("clave"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.getAppFront().loginError("Inicio de sesión incorrecto");
            return null;
        }
        return resultado;
    }

    public ArrayList<Usuario> searchUser(String[] search) {
        ArrayList<Usuario> resultado = new ArrayList<>();
        Connection con;
        PreparedStatement stmUsuario = null;
        ResultSet rsUsuario;

        con = this.getConnection();
        try {
            stmUsuario = con.prepareStatement("select dni, nombre, fec_inicio, telefono, tipo "
                    + "from usuarios "
                    + "where dni like ? and nombre like ? and fec_inicio like ? and telefono like ?");
            for (int i = 1; i <= 4; i++) {
                stmUsuario.setString(i, "%" + search[i - 1] + "%");
            }
            rsUsuario = stmUsuario.executeQuery();
            while (rsUsuario.next()) {
                Usuario usuario = new Usuario(rsUsuario.getString("dni"), rsUsuario.getString("nombre"), rsUsuario.getString("fec_inicio"), rsUsuario.getString("telefono"), null, TipoUsuario.valueOf(rsUsuario.getString("tipo")));
                resultado.add(usuario);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return resultado;
    }

    public boolean insertUser(String[] insert, TipoUsuario tipo) {
        Connection con;
        PreparedStatement stmUsuario, stmSecund;
        con = this.getConnection();
        try {
            con.setAutoCommit(false);   // Para realizar una sola transacción
            stmUsuario = con.prepareStatement("INSERT INTO public.usuarios VALUES (?, ?, cast(now() as date), ?, ?, '" + tipo.toString() + "')");
            for (int i = 1; i <= Usuario.getAttributeNames().size() - 2; i++) {
                stmUsuario.setString(i, insert[i - 1]);
            }
            if (tipo == TipoUsuario.monitor) {
                stmSecund = con.prepareStatement("INSERT INTO public.monitores VALUES (?, ?)");
                stmSecund.setString(1, insert[0]);                  // DNI
                stmSecund.setString(2, insert[insert.length - 1]);  // Especialidad
            } else {
                stmSecund = con.prepareStatement("INSERT INTO public.clientes VALUES (?, ?, ?, ?)");
                if (!insert[insert.length - 3].equals("")) {
                    stmSecund.setInt(1, Integer.valueOf(insert[insert.length - 3]));  // Abonado
                } else {
                    stmSecund.setInt(1, 0);
                }
                stmSecund.setString(2, insert[0]);  // DNI
                stmSecund.setString(3, insert[insert.length - 2]);  // Dirección
                stmSecund.setString(4, insert[insert.length - 1]);  // Banco

            }
            stmUsuario.executeUpdate();
            stmSecund.executeUpdate();
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

    //Funcion de registro de la fachada de Usuario que devolvera true si el registro se llevo a cabo correctamente
    public boolean registrar(String[] insert) {
        //COmprobamos 
        if (insert[0].equals("") || insert[4].equals("") || insert[5].equals("") || insert[6].equals("")) {
            JOptionPane.showMessageDialog(null, "Faltan campos obligatorios por añadir");
            return false;
        }

        Connection con;
        PreparedStatement stmUsuario;
        ArrayList<Usuario> comp;
        ResultSet rsUsuario;
        int nabonado = 0;

        //Funcion que te devuelve un array list con los usuarios de
        comp = searchUser(insert);

        if (comp.isEmpty()) {
            con = this.getConnection();
            try {
                con.setAutoCommit(false);
                stmUsuario = con.prepareStatement("INSERT INTO public.usuarios VALUES (?, ?, cast(now() as date), ?, ?, ?)");
                stmUsuario.setString(1, insert[0]);
                stmUsuario.setString(2, insert[1]);
                stmUsuario.setString(3, insert[3]);
                stmUsuario.setString(4, insert[4]);
                stmUsuario.setString(5, insert[5]);

                stmUsuario.executeUpdate();
                /* Miro cual es el numero de abonado que toca */
                stmUsuario = con.prepareStatement("select max(\"abonado\") from clientes");

                /* Ejecuto el comando sql */
                rsUsuario = stmUsuario.executeQuery();
                /*Almaceno el resultado en nabonado*/
                while (rsUsuario.next()) {
                    nabonado = rsUsuario.getInt("max");
                }

                /* Le sumo uno al numero de abonado para saber el numero que tendrá la porxima insercion */
                nabonado += 1;

                /* Inserto un cliente nuevo con el numero de abinado correspondiente */
                stmUsuario = con.prepareStatement("INSERT INTO public.clientes VALUES (?, ?, ?, ?,?)"); //COnsulta para insertar cliente o cliente premium 
                stmUsuario.setInt(1, nabonado); //nabonado
                stmUsuario.setString(2, insert[0]); //DNI
                stmUsuario.setString(3, insert[8]); //direccion
                stmUsuario.setString(4, insert[6]); //banco
                stmUsuario.setInt(5, 0); //cupones

                /* Ejecuto la actualizacion */
                stmUsuario.executeUpdate();

                if (insert[7].equals("premium")) {
                    stmUsuario = con.prepareStatement("INSERT INTO public.clientes_premium VALUES (?,?)"); //COnsulta para insertar cliente o cliente premium 
                    stmUsuario.setString(1, insert[0]);
                    stmUsuario.setInt(2, 10);
                    /* Ejecuto la siguiente acualizacion */
                    stmUsuario.executeUpdate();
                }
                con.commit();
                con.setAutoCommit(true);

            } catch (SQLException e) {
                System.out.println(e.getMessage());
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

        } else {

            JOptionPane.showMessageDialog(null, "El cliente introducido ya esta registrado");
            return false;

        }

        return true;
    }

}
