package modelos;

public class Situacion {
    //    Datos para tabla situacion
    private String duranteTrabajo;
    private String lugarLesion;
    private String actaNumero;
    private String relacionAgresorFallecido;

    //    Datos para tabla situacion_agresion
    private String situacionAgresion;
    private String descripcionAgresion;

    //    Datos para tabla situacion_domicilio
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

    public String getDuranteTrabajo() {
        return duranteTrabajo;
    }

    public void setDuranteTrabajo(String duranteTrabajo) {
        this.duranteTrabajo = duranteTrabajo;
    }

    public String getLugarLesion() {
        return lugarLesion;
    }

    public void setLugarLesion(String lugarLesion) {
        this.lugarLesion = lugarLesion;
    }

    public String getActaNumero() {
        return actaNumero;
    }

    public void setActaNumero(String actaNumero) {
        this.actaNumero = actaNumero;
    }

    public String getRelacionAgresorFallecido() {
        return relacionAgresorFallecido;
    }

    public void setRelacionAgresorFallecido(String relacionAgresorFallecido) {
        this.relacionAgresorFallecido = relacionAgresorFallecido;
    }

    public String getSituacionAgresion() {
        return situacionAgresion;
    }

    public void setSituacionAgresion(String situacionAgresion) {
        this.situacionAgresion = situacionAgresion;
    }

    public String getDescripcionAgresion() {
        return descripcionAgresion;
    }

    public void setDescripcionAgresion(String descripcionAgresion) {
        this.descripcionAgresion = descripcionAgresion;
    }

    public String getTipoVialidad() {
        return tipoVialidad;
    }

    public void setTipoVialidad(String tipoVialidad) {
        this.tipoVialidad = tipoVialidad;
    }

    public String getVialidadNombre() {
        return vialidadNombre;
    }

    public void setVialidadNombre(String vialidadNombre) {
        this.vialidadNombre = vialidadNombre;
    }

    public String getNumeroExterior() {
        return numeroExterior;
    }

    public void setNumeroExterior(String numeroExterior) {
        this.numeroExterior = numeroExterior;
    }

    public String getNumeroInterior() {
        return numeroInterior;
    }

    public void setNumeroInterior(String numeroInterior) {
        this.numeroInterior = numeroInterior;
    }

    public String getTipoAsentamientoHumano() {
        return tipoAsentamientoHumano;
    }

    public void setTipoAsentamientoHumano(String tipoAsentamientoHumano) {
        this.tipoAsentamientoHumano = tipoAsentamientoHumano;
    }

    public String getNombreAsentamientoHumano() {
        return nombreAsentamientoHumano;
    }

    public void setNombreAsentamientoHumano(String nombreAsentamientoHumano) {
        this.nombreAsentamientoHumano = nombreAsentamientoHumano;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getMunicipioAlcaldia() {
        return municipioAlcaldia;
    }

    public void setMunicipioAlcaldia(String municipioAlcaldia) {
        this.municipioAlcaldia = municipioAlcaldia;
    }

    public String getEntidadFederativa() {
        return entidadFederativa;
    }

    public void setEntidadFederativa(String entidadFederativa) {
        this.entidadFederativa = entidadFederativa;
    }
}
