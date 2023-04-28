
package minitienda.database;

import java.sql.Connection;

public abstract class AbstractDAO {
    private Connection con;

    public Connection getConnection() {
        return con;
    }

    public void setConnection(Connection con) {
        this.con = con;
    }
    
}