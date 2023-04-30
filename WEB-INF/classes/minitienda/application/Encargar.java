
package minitienda.application;

import java.util.ArrayList;

public class Encargar {
    private float precio;
    private int cantidad;

    public Encargar(float precio, int cantidad) {
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
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