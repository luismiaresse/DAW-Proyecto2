package minitienda.application;

public class Producto {

    int id;
    String nombre;
    String autor;
    String pais;
    float precio;

    public Producto(int id, String nombre, String autor, String pais, float precio) {
        this.id = id;
        this.nombre = nombre;
        this.autor = autor;
        this.pais = pais;
        // Truncamos el precio a dos decimales
        this.precio = (float) (Math.round(precio * 100.0) / 100.0);
    }

    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public String getAutor() {
        return autor;
    }
    public String getPais() {
        return pais;
    }
    public float getPrecio() {
        return precio;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (getClass() != obj.getClass()) { return false; }
        Producto other = (Producto) obj;
        if (this.id != other.id) { return false; }
        if (this.precio != other.precio) { return false; }
        return true;
    }
}
