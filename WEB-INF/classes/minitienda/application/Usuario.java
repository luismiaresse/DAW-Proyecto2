
package minitienda.application;

import minitienda.database.BCrypt;

public class Usuario {
    private String email;
    private TipoTarjeta tipoTarjeta;
    private String numTarjeta;

    public Usuario(String email) {
        this.email = email;
    }

    public Usuario(String email, TipoTarjeta tipoTarjeta, String numTarjeta) {
        this.email = email;
        this.tipoTarjeta = tipoTarjeta;
        this.numTarjeta = numTarjeta;
    }

    public String getEmail() {
        return email;
    }

    public TipoTarjeta getTipoTarjeta() {
        return tipoTarjeta;
    }

    public String getNumTarjeta() {
        return numTarjeta;
    }

    public String getNumTarjetaOculto() {
        return "**** **** **** " + numTarjeta.substring(numTarjeta.length() - 4);
    }

}
