package minitienda.classes;

import java.util.ArrayList;

public class Carrito {

    private ArrayList<Producto> productos;

    public Carrito(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public void anadirProducto(Producto producto){
        productos.add(producto);
    }

    public void eliminarProducto(int posProducto){
        productos.remove(posProducto);
    }

    public static ArrayList<String> getAttributeNames() {
        ArrayList<String> attr = new ArrayList<>(2);
        attr.add("Precio");
        attr.add("Cantidad");
        return attr;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (getClass() != obj.getClass()) { return false; }
        return true;
    }

}
