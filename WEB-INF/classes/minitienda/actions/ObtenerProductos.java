package minitienda.actions;

import minitienda.application.Producto;
import minitienda.database.DBFront;
import java.util.ArrayList;

public class ObtenerProductos {
    private DBFront db = DBFront.getInstance();
    public ArrayList<Producto> getProducts() {
        return db.getProducts();
    }
}
