package modelos;

public class Fallecido {
//    Atributos necesarios para la primera tabla (fallecido)

    private boolean desconocido;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private int diaNacimiento;
    private int mesNacimiento;
    private int anioNacimiento;
    private String sexo;
    private String entidadNacimiento;
    private String curp;
    private String nacionalidad;
    private String especifiqueNacionalidad;

    //    Atributos para identidad cultural
    private String afromexicano;
    private String indigena;
    private String hablaIndigena;
    private String especifiqueLenguaIndigena;

    //  Atributos para edad
    private boolean seIgnoraEdad;
    private int edadMinutos;
    private int edadHoras;
    private int edadDias;
    private int edadMeses;
    private int edadAnios;

    //  Attibutos para edad menor de 28 dias
    private String folioCertificadoNacimiento;
    private int semanasGestacion;
    private int pesoNacimiento;

    //  Atributos para estado civil
    private String situacionConyugal;

    //Atributos para residencia habitual
    private String tipoVialidad;
    private String nombreVialidad;
    private String numeroExterior;
    private String numeroInterior;
    private String tipoAsentamientoHumano;
    private String nombreAsentamientoHumano;
    private String codigoPostal;
    private String localidad;
    private String municipioAlcaldia;
    private String entidadFederativaPais;

    //Atributos para escolaridad
    private String escolaridad;
    private String tipoEscolaridad;

    //Atributos para ocupacion
    private boolean ocupacionHabitual;
    private String descripcionOcupacion;
    private String trabajaba;

    //Atributos para seguridad social
    private String afiliacionServicioSalud;
    private String especifiqueServicioSalud;
    private String numeroSeguridadSocialAfiliacion;

    //Dato provisional
    private int diaCertificacion;
    private int mesCertificacion;
    private int anioCertificacion;

    public int getDiaCertificacion() {
        return diaCertificacion;
    }

    public void setDiaCertificacion(int diaCertificacion) {
        this.diaCertificacion = diaCertificacion;
    }

    public int getMesCertificacion() {
        return mesCertificacion;
    }

    public void setMesCertificacion(int mesCertificacion) {
        this.mesCertificacion = mesCertificacion;
    }

    public int getAnioCertificacion() {
        return anioCertificacion;
    }

    public void setAnioCertificacion(int anioCertificacion) {
        this.anioCertificacion = anioCertificacion;
    }

    public String getAfiliacionServicioSalud() {
        return afiliacionServicioSalud;
    }

    public void setAfiliacionServicioSalud(String afiliacionServicioSalud) {
        this.afiliacionServicioSalud = afiliacionServicioSalud;
    }

    public String getEspecifiqueServicioSalud() {
        return especifiqueServicioSalud;
    }

    public void setEspecifiqueServicioSalud(String especifiqueServicioSalud) {
        this.especifiqueServicioSalud = especifiqueServicioSalud;
    }

    public String getNumeroSeguridadSocialAfiliacion() {
        return numeroSeguridadSocialAfiliacion;
    }

    public void setNumeroSeguridadSocialAfiliacion(String numeroSeguridadSocialAfiliacion) {
        this.numeroSeguridadSocialAfiliacion = numeroSeguridadSocialAfiliacion;
    }

    public boolean isOcupacionHabitual() {
        return ocupacionHabitual;
    }

    public void setOcupacionHabitual(boolean ocupacionHabitual) {
        this.ocupacionHabitual = ocupacionHabitual;
    }

    public String getDescripcionOcupacion() {
        return descripcionOcupacion;
    }

    public void setDescripcionOcupacion(String descripcionOcupacion) {
        this.descripcionOcupacion = descripcionOcupacion;
    }

    public String getTrabajaba() {
        return trabajaba;
    }

    public void setTrabajaba(String trabajaba) {
        this.trabajaba = trabajaba;
    }

    public String getEscolaridad() {
        return escolaridad;
    }

    public void setEscolaridad(String escolaridad) {
        this.escolaridad = escolaridad;
    }

    public String getTipoEscolaridad() {
        return tipoEscolaridad;
    }

    public void setTipoEscolaridad(String tipoEscolaridad) {
        this.tipoEscolaridad = tipoEscolaridad;
    }

    public String getTipoVialidad() {
        return tipoVialidad;
    }

    public void setTipoVialidad(String tipoVialidad) {
        this.tipoVialidad = tipoVialidad;
    }

    public String getNombreVialidad() {
        return nombreVialidad;
    }

    public void setNombreVialidad(String nombreVialidad) {
        this.nombreVialidad = nombreVialidad;
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

    public String getEntidadFederativaPais() {
        return entidadFederativaPais;
    }

    public void setEntidadFederativaPais(String entidadFederativaPais) {
        this.entidadFederativaPais = entidadFederativaPais;
    }

    public String getSituacionConyugal() {
        return situacionConyugal;
    }

    public void setSituacionConyugal(String situacionConyugal) {
        this.situacionConyugal = situacionConyugal;
    }

    public boolean isSeIgnoraEdad() {
        return seIgnoraEdad;
    }

    public void setSeIgnoraEdad(boolean seIgnoraEdad) {
        this.seIgnoraEdad = seIgnoraEdad;
    }

    public int getEdadMinutos() {
        return edadMinutos;
    }

    public void setEdadMinutos(int edadMinutos) {
        this.edadMinutos = edadMinutos;
    }

    public int getEdadHoras() {
        return edadHoras;
    }

    public void setEdadHoras(int edadHoras) {
        this.edadHoras = edadHoras;
    }

    public int getEdadDias() {
        return edadDias;
    }

    public void setEdadDias(int edadDias) {
        this.edadDias = edadDias;
    }

    public int getEdadMeses() {
        return edadMeses;
    }

    public void setEdadMeses(int edadMeses) {
        this.edadMeses = edadMeses;
    }

    public int getEdadAnios() {
        return edadAnios;
    }

    public void setEdadAnios(int edadAnios) {
        this.edadAnios = edadAnios;
    }

    public String getFolioCertificadoNacimiento() {
        return folioCertificadoNacimiento;
    }

    public void setFolioCertificadoNacimiento(String folioCertificadoNacimiento) {
        this.folioCertificadoNacimiento = folioCertificadoNacimiento;
    }

    public int getSemanasGestacion() {
        return semanasGestacion;
    }

    public void setSemanasGestacion(int semanasGestacion) {
        this.semanasGestacion = semanasGestacion;
    }

    public int getPesoNacimiento() {
        return pesoNacimiento;
    }

    public void setPesoNacimiento(int pesoNacimiento) {
        this.pesoNacimiento = pesoNacimiento;
    }

    public String getAfromexicano() {
        return afromexicano;
    }

    public void setAfromexicano(String afromexicano) {
        this.afromexicano = afromexicano;
    }

    public String getIndigena() {
        return indigena;
    }

    public void setIndigena(String indigena) {
        this.indigena = indigena;
    }

    public String getHablaIndigena() {
        return hablaIndigena;
    }

    public void setHablaIndigena(String hablaIndigena) {
        this.hablaIndigena = hablaIndigena;
    }

    public String getEspecifiqueLenguaIndigena() {
        return especifiqueLenguaIndigena;
    }

    public void setEspecifiqueLenguaIndigena(String especifiqueLenguaIndigena) {
        this.especifiqueLenguaIndigena = especifiqueLenguaIndigena;
    }

    public String getEspecifiqueNacionalidad() {
        return especifiqueNacionalidad;
    }

    public void setEspecifiqueNacionalidad(String especifiqueNacionalidad) {
        this.especifiqueNacionalidad = especifiqueNacionalidad;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getEntidadNacimiento() {
        return entidadNacimiento;
    }

    public void setEntidadNacimiento(String entidadNacimiento) {
        this.entidadNacimiento = entidadNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getDiaNacimiento() {
        return diaNacimiento;
    }

    public void setDiaNacimiento(int diaNacimiento) {
        this.diaNacimiento = diaNacimiento;
    }

    public int getMesNacimiento() {
        return mesNacimiento;
    }

    public void setMesNacimiento(int mesNacimiento) {
        this.mesNacimiento = mesNacimiento;
    }

    public int getAnioNacimiento() {
        return anioNacimiento;
    }

    public void setAnioNacimiento(int anioNacimiento) {
        this.anioNacimiento = anioNacimiento;
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

    public boolean isDesconocido() {
        return desconocido;
    }

    public void setDesconocido(boolean desconocido) {
        this.desconocido = desconocido;
    }
}
