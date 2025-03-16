package modelos;

public class Certificante {
    //    Datos para la tabla certificante
    private String certificante;
    private String especifiqueCertificante;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String numeroCedulaProfesional;

    //    Datos para la tabla certificante_domicilio
    private String tipoVialidad;
    private String vialidadNombre;
    private String numeroExterior;
    private String numeroInterior;
    private String tipoAsentamientoHumano;
    private String nombreAsentamientoHumano;
    private String codigoPostal;
    private String localidad;
    private String municipioAlcaldia;
    private String entidadFederativa;

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEntidadFederativa() {
        return entidadFederativa;
    }

    public void setEntidadFederativa(String entidadFederativa) {
        this.entidadFederativa = entidadFederativa;
    }

    public String getMunicipioAlcaldia() {
        return municipioAlcaldia;
    }

    public void setMunicipioAlcaldia(String municipioAlcaldia) {
        this.municipioAlcaldia = municipioAlcaldia;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getNombreAsentamientoHumano() {
        return nombreAsentamientoHumano;
    }

    public void setNombreAsentamientoHumano(String nombreAsentamientoHumano) {
        this.nombreAsentamientoHumano = nombreAsentamientoHumano;
    }

    public String getTipoAsentamientoHumano() {
        return tipoAsentamientoHumano;
    }

    public void setTipoAsentamientoHumano(String tipoAsentamientoHumano) {
        this.tipoAsentamientoHumano = tipoAsentamientoHumano;
    }

    public String getNumeroInterior() {
        return numeroInterior;
    }

    public void setNumeroInterior(String numeroInterior) {
        this.numeroInterior = numeroInterior;
    }

    public String getNumeroExterior() {
        return numeroExterior;
    }

    public void setNumeroExterior(String numeroExterior) {
        this.numeroExterior = numeroExterior;
    }

    public String getVialidadNombre() {
        return vialidadNombre;
    }

    public void setVialidadNombre(String vialidadNombre) {
        this.vialidadNombre = vialidadNombre;
    }

    public String getTipoVialidad() {
        return tipoVialidad;
    }

    public void setTipoVialidad(String tipoVialidad) {
        this.tipoVialidad = tipoVialidad;
    }

    public String getNumeroCedulaProfesional() {
        return numeroCedulaProfesional;
    }

    public void setNumeroCedulaProfesional(String numeroCedulaProfesional) {
        this.numeroCedulaProfesional = numeroCedulaProfesional;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecifiqueCertificante() {
        return especifiqueCertificante;
    }

    public void setEspecifiqueCertificante(String especifiqueCertificante) {
        this.especifiqueCertificante = especifiqueCertificante;
    }

    public String getCertificante() {
        return certificante;
    }

    public void setCertificante(String certificante) {
        this.certificante = certificante;
    }

    private String telefono;
}
