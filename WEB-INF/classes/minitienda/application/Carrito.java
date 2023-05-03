package minitienda.application;

import java.util.ArrayList;
import java.util.HashMap;

public class Carrito {

    // Clave: id del producto
    // Valor: cantidad de ese producto
    private ArrayList<ElementoCarrito> elementos;

    public Carrito() {
        elementos = new ArrayList<>();
    }

    public ArrayList<ElementoCarrito> getElementos() {
        return elementos;
    }

    public void anadirProducto(Producto producto, int cantidad) {
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


    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (getClass() != obj.getClass()) { return false; }
        return true;
    }

}
