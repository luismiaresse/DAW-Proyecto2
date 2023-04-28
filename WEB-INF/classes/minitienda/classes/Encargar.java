
package minitienda.classes;

import java.sql.Date;
import java.util.ArrayList;

public class Encargar {
    private String monitor;
    private String material;
    private java.sql.Date fecha;
    private float precio;
    private int cantidad;

    public Encargar(String monitor, String material, java.sql.Date fecha, float precio, int cantidad) {
        if (monitor == null || material == null || fecha == null) { throw new IllegalArgumentException("La clave primaria no puede contener valores nulos"); }
        this.monitor = monitor;
        this.material = material;
        this.fecha = fecha;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public String getMonitor() {
        return monitor;
    }

    public void setMonitor(String monitor) {
        this.monitor = monitor;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
    

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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
        ArrayList<String> attr = new ArrayList<>(5);
        attr.add("Monitor");
        attr.add("Material");
        attr.add("Fecha");
        attr.add("Precio");
        attr.add("Cantidad");
        return attr;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (getClass() != obj.getClass()) { return false; }
        final Encargar other = (Encargar) obj;
        if (this.monitor == null || !this.monitor.equals(other.monitor)) { return false; }
        if (this.material == null || !this.material.equals(other.material)) { return false; }
        if (this.fecha == null || !this.fecha.equals(other.fecha)) { return false; }
        return true;
    }
    
}