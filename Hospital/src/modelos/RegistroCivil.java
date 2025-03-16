package modelos;

public class RegistroCivil {
    //    Datos para tabla registro_civil
    private String numero;
    private String numeroLibro;
    private String numeroActa;
    private int diaRegistro;
    private int mesRegistro;
    private int anioRegistro;

    //    Datos para tabla registro_civil_lugar
    private String localidad;
    private String municipioAlcaldia;
    private String entidadFederativa;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNumeroLibro() {
        return numeroLibro;
    }

    public void setNumeroLibro(String numeroLibro) {
        this.numeroLibro = numeroLibro;
    }

    public String getNumeroActa() {
        return numeroActa;
    }

    public void setNumeroActa(String numeroActa) {
        this.numeroActa = numeroActa;
    }

    public int getDiaRegistro() {
        return diaRegistro;
    }

    public void setDiaRegistro(int diaRegistro) {
        this.diaRegistro = diaRegistro;
    }

    public int getMesRegistro() {
        return mesRegistro;
    }

    public void setMesRegistro(int mesRegistro) {
        this.mesRegistro = mesRegistro;
    }

    public int getAnioRegistro() {
        return anioRegistro;
    }

    public void setAnioRegistro(int anioRegistro) {
        this.anioRegistro = anioRegistro;
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
