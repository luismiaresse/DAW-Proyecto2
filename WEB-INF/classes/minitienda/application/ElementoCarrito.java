package minitienda.application;

public class ElementoCarrito {
    private Producto producto;
    private int cantidad;
    private float subtotal;

    public ElementoCarrito(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.subtotal = producto.getPrecio() * cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public float getSubtotal() {
        return subtotal;
    }

}
