
package minitienda.classes;

import java.util.ArrayList;

public class Usuario {
    private String nombre;
    private String clave;

    public Usuario(String nombre, String clave) {
        this.nombre = nombre;
        this.clave = clave;
    }

    // Atributos comunes a clientes y monitores
    public static ArrayList<String> getAttributeNames() {
        ArrayList<String> attr = new ArrayList<>(6);
        attr.add("Nombre");
        attr.add("Clave");
        return attr;
    }
    

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
