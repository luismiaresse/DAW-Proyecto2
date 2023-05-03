
package minitienda.application;

import java.util.ArrayList;

public class Usuario {
    private String email;
    private String clave;

    public Usuario(String email, String pwhash) {
        this.email = email;
        this.clave = pwhash;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
