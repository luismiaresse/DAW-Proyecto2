
package minitienda.classes;

import java.util.ArrayList;

public class Usuario {
    private final String dni;
    private String nombre;
    private String fechaInicio;
    private String numTelefono;
    private String clave;
    private TipoUsuario tipo;
    
    public Usuario(String dni, String nombre, String fechaInicio, String numTelefono, String clave, TipoUsuario tipo) {
        this.dni = dni;
        this.nombre = nombre;
        this.numTelefono = numTelefono;
        this.fechaInicio = fechaInicio;
        this.clave = clave;
        this.tipo = tipo;
    }

    // Atributos comunes a clientes y monitores
    public static ArrayList<String> getAttributeNames() {
        ArrayList<String> attr = new ArrayList<>(6);
        attr.add("DNI");
        attr.add("Nombre");
        attr.add("Fecha de inicio");
        attr.add("Teléfono");
        attr.add("Clave");
        attr.add("Tipo de usuario");
        return attr;
    }
    
    public String getDni() {
        return dni;
    }
    
    //Añado los getters y setters de clave
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

    public String getNumTelefono() {
        return numTelefono;
    }

    public void setNumTelefono(String numTelefono) {
        this.numTelefono = numTelefono;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

}
