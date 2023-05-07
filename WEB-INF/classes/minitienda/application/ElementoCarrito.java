package minitienda.application;

public class ElementoCarrito {
    private Producto producto;
    private int cantidad;
    private float subtotal;

    public ElementoCarrito(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.subtotal = producto.getPrecio() * cantidad;
        // Truncamos el subtotal a dos decimales
        this.subtotal = (float) (Math.round(this.subtotal * 100.0) / 100.0);
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        this.subtotal = producto.getPrecio() * cantidad;
        // Truncamos el subtotal a dos decimales
        this.subtotal = (float) (Math.round(this.subtotal * 100.0) / 100.0);
    }

    public float getSubtotal() {
        return subtotal;
    }

}
