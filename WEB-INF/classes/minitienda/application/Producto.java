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
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
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
