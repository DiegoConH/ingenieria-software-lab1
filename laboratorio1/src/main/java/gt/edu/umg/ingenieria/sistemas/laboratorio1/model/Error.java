package gt.edu.umg.ingenieria.sistemas.laboratorio1.model;

import java.io.Serializable;

public class Error implements Serializable {
    private String nombre;
    private String message;

    public Error() {
    }

    public Error(String nombre, String message) {
        this.nombre = nombre;
        this.message = message;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
