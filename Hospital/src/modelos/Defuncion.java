package modelos;

public class Defuncion {
    //    Datos para la tabla defuncion
    private String sitio;
    private int dia;
    private int mes;
    private int anio;
    private int horas;
    private int minutos;
    private String atencionMedica;
    private String accidentalViolenta;
    private String defuncionTipo;

    //    Datos para la tabla defuncion_unidad_medica
    private String unidadMedica;
    private String nombreUnidadMedica;
    private String clues;

    //    Datos para la tabla defuncion_mujer_10_54_anios
    private String muerteDurante;
    private String causasComplicacionesPropiasEmbarazoPartoPuerperio;
    private String causasComplicaronEmbarazoPartoPuerperio;

    //    Datos para tabla defuncion_encefalica
    private String muerteEncefalica;
    private String donadorOrganos;

    //    Datos para tabla defuncion_domicilio
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

    //    Datos para la tabla defuncion_cirugia
    private String cirugiaUltimas4Semanas;
    private int cirugiaDia;
    private int cirugiaMes;
    private int cirugiaAnio;
    private String cirugiaMotivo;

    //    Datos para tabla defuncion_necropsia
    private String necropsia;
    private String hallazgosCertificacion;

    //    Datos para tabla defuncion_causas
    private String[] causas = new String[5];
    private String[] intervaloTiempo = new String[4];
    private String[] codigoCie = new String[5];

    //    Datos para tabla defuncion_causas_basicas
    private String causaBasica;
    //    private String intervaloTiempoCausaSignificativa;
    private String codigoCieCausaBasica;

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public String getSitio() {
        return sitio;
    }

    public void setSitio(String sitio) {
        this.sitio = sitio;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getAtencionMedica() {
        return atencionMedica;
    }

    public void setAtencionMedica(String atencionMedica) {
        this.atencionMedica = atencionMedica;
    }

    public String getAccidentalViolenta() {
        return accidentalViolenta;
    }

    public void setAccidentalViolenta(String accidentalViolenta) {
        this.accidentalViolenta = accidentalViolenta;
    }

    public String getDefuncionTipo() {
        return defuncionTipo;
    }

    public void setDefuncionTipo(String defuncionTipo) {
        this.defuncionTipo = defuncionTipo;
    }


    public String getUnidadMedica() {
        return unidadMedica;
    }

    public void setUnidadMedica(String unidadMedica) {
        this.unidadMedica = unidadMedica;
    }

    public String getNombreUnidadMedica() {
        return nombreUnidadMedica;
    }

    public void setNombreUnidadMedica(String nombreUnidadMedica) {
        this.nombreUnidadMedica = nombreUnidadMedica;
    }

    public String getClues() {
        return clues;
    }

    public void setClues(String clues) {
        this.clues = clues;
    }

    public String getMuerteDurante() {
        return muerteDurante;
    }

    public void setMuerteDurante(String muerteDurante) {
        this.muerteDurante = muerteDurante;
    }

    public String getCausasComplicacionesPropiasEmbarazoPartoPuerperio() {
        return causasComplicacionesPropiasEmbarazoPartoPuerperio;
    }

    public void setCausasComplicacionesPropiasEmbarazoPartoPuerperio(String causasComplicacionesPropiasEmbarazoPartoPuerperio) {
        this.causasComplicacionesPropiasEmbarazoPartoPuerperio = causasComplicacionesPropiasEmbarazoPartoPuerperio;
    }

    public String getCausasComplicaronEmbarazoPartoPuerperio() {
        return causasComplicaronEmbarazoPartoPuerperio;
    }

    public void setCausasComplicaronEmbarazoPartoPuerperio(String causasComplicaronEmbarazoPartoPuerperio) {
        this.causasComplicaronEmbarazoPartoPuerperio = causasComplicaronEmbarazoPartoPuerperio;
    }

    public String getMuerteEncefalica() {
        return muerteEncefalica;
    }

    public void setMuerteEncefalica(String muerteEncefalica) {
        this.muerteEncefalica = muerteEncefalica;
    }

    public String getDonadorOrganos() {
        return donadorOrganos;
    }

    public void setDonadorOrganos(String donadorOrganos) {
        this.donadorOrganos = donadorOrganos;
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

    public String getCirugiaUltimas4Semanas() {
        return cirugiaUltimas4Semanas;
    }

    public void setCirugiaUltimas4Semanas(String cirugiaUltimas4Semanas) {
        this.cirugiaUltimas4Semanas = cirugiaUltimas4Semanas;
    }

    public int getCirugiaDia() {
        return cirugiaDia;
    }

    public void setCirugiaDia(int cirugiaDia) {
        this.cirugiaDia = cirugiaDia;
    }

    public int getCirugiaMes() {
        return cirugiaMes;
    }

    public void setCirugiaMes(int cirugiaMes) {
        this.cirugiaMes = cirugiaMes;
    }

    public int getCirugiaAnio() {
        return cirugiaAnio;
    }

    public void setCirugiaAnio(int cirugiaAnio) {
        this.cirugiaAnio = cirugiaAnio;
    }

    public String getCirugiaMotivo() {
        return cirugiaMotivo;
    }

    public void setCirugiaMotivo(String cirugiaMotivo) {
        this.cirugiaMotivo = cirugiaMotivo;
    }

    public String getNecropsia() {
        return necropsia;
    }

    public void setNecropsia(String necropsia) {
        this.necropsia = necropsia;
    }

    public String getHallazgosCertificacion() {
        return hallazgosCertificacion;
    }

    public void setHallazgosCertificacion(String hallazgosCertificacion) {
        this.hallazgosCertificacion = hallazgosCertificacion;
    }

    public String[] getCausas() {
        return causas;
    }

    public void setCausas(String[] causas) {
        this.causas = causas;
    }

    public String[] getIntervaloTiempo() {
        return intervaloTiempo;
    }

    public void setIntervaloTiempo(String[] intervaloTiempo) {
        this.intervaloTiempo = intervaloTiempo;
    }

    public String[] getCodigoCie() {
        return codigoCie;
    }

    public void setCodigoCie(String[] codigoCie) {
        this.codigoCie = codigoCie;
    }

    public String getCausaBasica() {
        return causaBasica;
    }

    public void setCausaBasica(String causaBasica) {
        this.causaBasica = causaBasica;
    }

    public String getCodigoCieCausaBasica() {
        return codigoCieCausaBasica;
    }

    public void setCodigoCieCausaBasica(String codigoCieCausaBasica) {
        this.codigoCieCausaBasica = codigoCieCausaBasica;
    }
}
