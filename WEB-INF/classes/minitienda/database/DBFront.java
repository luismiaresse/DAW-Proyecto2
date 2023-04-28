package minitienda.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.sql.*;
import javax.swing.JOptionPane;

public class DBFront {

    private Connection con;
    private UsuarioDAO usuario;
    private EncargarDAO encargar;

    public DBFront() {
        FileInputStream confFile = null;
        try {
            confFile = new FileInputStream("database.properties");

            Properties user = new Properties();
            Properties conf = new Properties();

            conf.load(confFile);
            confFile.close();
            user.setProperty("username", conf.getProperty("username"));
            user.setProperty("password", conf.getProperty("password"));

            this.con = DriverManager.getConnection("jdbc:"
                    + conf.getProperty("manager") + "://"
                    + conf.getProperty("server") + ":"
                    + conf.getProperty("port") + "/"
                    + conf.getProperty("database"),
                    user);
            usuario = new UsuarioDAO(this.con);
        } catch (IOException | SQLException i) {
            JOptionPane.showMessageDialog(null, "No se pudo acceder a la BD: " + i.getMessage());
            System.exit(1);
        }
    }

    public Usuario login(String username, String password) {
        return this.usuario.login(username, password);
    }

    public ArrayList<Usuario> searchUser(String[] search) {
        return usuario.searchUser(search);
    }

    public boolean registerUser(String[] insert) {
        return usuario.registrar(insert);
    }

    public boolean insertUser(String[] insert, TipoUsuario tipo) {
        return usuario.insertUser(insert, tipo);
    }

    public ArrayList<Encargar> searchPedido(String[] search) {
        return encargar.searchPedido(search);
    }

    public boolean hacerPedido(String[] insert) {
        return encargar.hacerPedido(insert);
    }


}
