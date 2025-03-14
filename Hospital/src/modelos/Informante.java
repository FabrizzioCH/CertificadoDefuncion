package modelos;

public class Informante {
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String parentescoFallecido;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getParentescoFallecido() {
        return parentescoFallecido;
    }

    public void setParentescoFallecido(String parentescoFallecido) {
        this.parentescoFallecido = parentescoFallecido;
    }
}
