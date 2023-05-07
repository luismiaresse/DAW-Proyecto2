package minitienda.application;

import java.util.ArrayList;
import java.util.HashMap;

public class Carrito {
    private ArrayList<ElementoCarrito> elementos;

    public Carrito() {
        elementos = new ArrayList<>();
    }

    public ArrayList<ElementoCarrito> getElementos() {
        return elementos;
    }

    public void anadirProducto(Producto producto, int cantidad) {
        // Si el producto ya está en el carrito, se actualiza la cantidad
        for (ElementoCarrito elem : elementos) {
            if (elem.getProducto().getId() == producto.getId()) {
                elem.setCantidad(elem.getCantidad() + cantidad);
                return;
            }
        }
        elementos.add(new ElementoCarrito(producto, cantidad));
    }


    public void eliminarProducto(int idProd){
        for (ElementoCarrito elem : elementos) {
            if (elem.getProducto().getId() == idProd) {
                elementos.remove(elem);
                break;
            }
        }
    }

    public float getPrecioTotal() {
        float precio = 0;
        for (ElementoCarrito elem : elementos) {
            precio += elem.getSubtotal();
        }
        return precio;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (getClass() != obj.getClass()) { return false; }
        return true;
    }

}
