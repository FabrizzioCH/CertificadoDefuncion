package controladores;

import javax.swing.JOptionPane;

import modelos.Fallecido;
import modelos.FallecidoDAO;
import modelos.Informante;
import vistas.VistaGeneraCertificado;

public class ControladorCertificadoDefuncion {

    public static VistaGeneraCertificado vistaGeneraCertificado = new VistaGeneraCertificado();
    private static Fallecido fallecido;
    private static Informante informante;

    public static void mostrar() {
        vistaGeneraCertificado.setVisible(true);
    }

    public static void recopilarInformacionVista1() {
        fallecido = new Fallecido();
        var errores = new StringBuilder();
        if (!vistaGeneraCertificado.getRdbtnPersonaDesconocida().isSelected()) {
            fallecido.setDesconocido(false);
            if (!vistaGeneraCertificado.getTxtNombre().getText().equals("")) {
                fallecido.setNombre(vistaGeneraCertificado.getTxtNombre().getText().trim());
            } else {
                errores.append("El campo Nombre no debe ser vacío\n");
            }
            if (!vistaGeneraCertificado.getTxtApellidoPaterno().getText().equals("")) {
                fallecido.setApellidoPaterno(vistaGeneraCertificado.getTxtApellidoPaterno().getText().trim());
            } else {
                errores.append("El campo Apellido Paterno no debe ser vacío\n");
            }
            if (!vistaGeneraCertificado.getTxtApellidoMaterno().getText().equals("")) {
                fallecido.setApellidoMaterno(vistaGeneraCertificado.getTxtApellidoMaterno().getText().trim());
            } else {
                errores.append("El campo Apellido Materno no debe ser vacío\n");
            }
        } else {
            fallecido.setDesconocido(true);
        }
        try {
            String diaTexto = vistaGeneraCertificado.getTxtDiaNacimiento().getText().trim();
            String mesTexto = vistaGeneraCertificado.getTxtMesNacimiento().getText().trim();
            String anioTexto = vistaGeneraCertificado.getTxtAñoNacimiento().getText().trim();

            if (!diaTexto.equals("") && !mesTexto.equals("") && !anioTexto.equals("")) {
                String fechaCompleta = diaTexto + "/" + mesTexto + "/" + anioTexto;
                if (fechaCompleta.matches("^([0-2][0-9]|3[0-1])/(0[1-9]|1[0-2])/\\d{4}$")) {
                    int dia = Integer.parseInt(diaTexto);
                    int mes = Integer.parseInt(mesTexto);
                    int anio = Integer.parseInt(anioTexto);

                    if (dia > 0 && dia <= 31 && mes > 0 && mes <= 12 && anio > 0) {
                        fallecido.setDiaNacimiento(dia);
                        fallecido.setMesNacimiento(mes);
                        fallecido.setAnioNacimiento(anio);

                        // Additional validation for a valid calendar date
                        java.time.LocalDate.of(anio, mes, dia);
                    } else {
                        errores.append("La fecha ingresada es inválida. Verifica el día, mes y año.\n");
                    }
                } else {
                    errores.append("La fecha debe estar en el formato dd/mm/yyyy.\n");
                }
            } else {
                if (diaTexto.equals("")) errores.append("El campo Día de Nacimiento no debe ser vacío\n");
                if (mesTexto.equals("")) errores.append("El campo Mes de Nacimiento no debe ser vacío\n");
                if (anioTexto.equals("")) errores.append("El campo Año de Nacimiento no debe ser vacío\n");
            }
        } catch (NumberFormatException | java.time.DateTimeException e) {
            errores.append("Solo puedes ingresar números válidos en los campos de Fecha de Nacimiento. Verifica que la fecha sea válida.\n");
        }


        if (vistaGeneraCertificado.getRdbtnHombre().isSelected()) {
            fallecido.setSexo("Hombre");
        } else if (vistaGeneraCertificado.getRdbtnMujer().isSelected()) {
            fallecido.setSexo("Mujer");
        } else if (vistaGeneraCertificado.getRdbtnSeIgnoraSexo().isSelected()) {
            fallecido.setSexo("Se ignora");
        } else {
            errores.append("El campo Sexo no debe ser vacío");
        }

        if (errores.length() > 0) {
            JOptionPane.showMessageDialog(
                    null,
                    errores.toString(),
                    "Errores de validación",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            vistaGeneraCertificado.getTabbedPaneCertificadoDefuncion().setSelectedIndex(1);
        }
    }

    public static void recopilarInformacionVista2() {
        var errores = new StringBuilder();
        if (!vistaGeneraCertificado.getTxtEntidadNacimiento().getText().equals("")) {
            fallecido.setEntidadNacimiento(vistaGeneraCertificado.getTxtEntidadNacimiento().getText().trim());
        } else {
            errores.append("El campo Entidad Nacimiento no debe ser vacío\n");
        }
        if (!vistaGeneraCertificado.getTxtCurp().getText().equals("")) {
            fallecido.setCurp(vistaGeneraCertificado.getTxtCurp().getText().trim());
        } else {
            errores.append("El campo Curp no debe ser vacío\n");
        }
        if (vistaGeneraCertificado.getRdbtnSiAfromexicano().isSelected()) {
            fallecido.setAfromexicano("Si");
        } else if (vistaGeneraCertificado.getRdbtnNoAfromexicano().isSelected()) {
            fallecido.setAfromexicano("No");
        } else if (vistaGeneraCertificado.getRdbtnSeIgnoraAfromexicano().isSelected()) {
            fallecido.setAfromexicano("Se ignora");
        } else {
            errores.append("El campo Afromexicano no debe ser vacío\n");
        }

        if (vistaGeneraCertificado.getRdbtnSiIndigena().isSelected()) {
            fallecido.setIndigena("Si");
        } else if (vistaGeneraCertificado.getRdbtnNoIndigena().isSelected()) {
            fallecido.setIndigena("No");
        } else if (vistaGeneraCertificado.getRdbtnSeIgnoraIndigena().isSelected()) {
            fallecido.setIndigena("Se ignora");
        } else {
            errores.append("El campo Indigena no debe ser vacío\n");
        }

        if (vistaGeneraCertificado.getRdbtnSiLenguaIndigena().isSelected()) {
            fallecido.setHablaIndigena("Si");
            if (!vistaGeneraCertificado.getTxtEspecifiqueLenguaIndigena().getText().equals("")) {
                fallecido.setEspecifiqueLenguaIndigena(vistaGeneraCertificado.getTxtEspecifiqueLenguaIndigena().getText().trim());
            } else {
                errores.append("El campo Especifique Lengua Indigena no debe ser vacío\n");
            }
        } else if (vistaGeneraCertificado.getRdbtnNoLenguaIndigena().isSelected()) {
            fallecido.setHablaIndigena("No");
        } else if (vistaGeneraCertificado.getRdbtnSeIgnoraLenguaIndigena().isSelected()) {
            fallecido.setHablaIndigena("Se ignora");
        } else {
            errores.append("El campo Habla Indigena no debe ser vacío\n");
        }

        if (vistaGeneraCertificado.getRdbtnNacionalidadMex().isSelected()) {
            fallecido.setNacionalidad("Mexicana");
        } else if (vistaGeneraCertificado.getRdbtnOtraNacionalidad().isSelected()) {
            fallecido.setNacionalidad("Otra");
            if (!vistaGeneraCertificado.getTxtEspecifiqueNacionalidad().getText().equals("")) {
                fallecido.setEspecifiqueNacionalidad(vistaGeneraCertificado.getTxtEspecifiqueNacionalidad().getText().trim());
            } else {
                errores.append("El campo Especifique Nacionalidad no debe ser vacío\n");
            }
        } else if (vistaGeneraCertificado.getRdbtnSeIgnoraNacionalidad().isSelected()) {
            fallecido.setNacionalidad("Se ignora");
        } else {
            errores.append("El campo Nacionalidad no debe ser vacío");
        }

        if (errores.length() > 0) {
            JOptionPane.showMessageDialog(
                    null,
                    errores.toString(),
                    "Errores de validación",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            vistaGeneraCertificado.getTabbedPaneCertificadoDefuncion().setSelectedIndex(2);
        }
    }

    public static void recopilarInformacionVista3() {
        var errores = new StringBuilder();
        if (!vistaGeneraCertificado.getRdbtnSeIgnoraEdad().isSelected()) {
            fallecido.setSeIgnoraEdad(false);
            try {
                if (!vistaGeneraCertificado.getTxtEdadTiempo().getText().equals("")) {
                    if (vistaGeneraCertificado.getjComboBoxTiempo().getSelectedItem().equals("Minutos")) {
                        fallecido.setEdadMinutos(Integer.parseInt(vistaGeneraCertificado.getTxtEdadTiempo().getText().trim()));

                    } else if (vistaGeneraCertificado.getjComboBoxTiempo().getSelectedItem().equals("Horas")) {
                        fallecido.setEdadHoras(Integer.parseInt(vistaGeneraCertificado.getTxtEdadTiempo().getText().trim()));

                    } else if (vistaGeneraCertificado.getjComboBoxTiempo().getSelectedItem().equals("Días")) {
                        fallecido.setEdadDias(Integer.parseInt(vistaGeneraCertificado.getTxtEdadTiempo().getText().trim()));

                    } else if (vistaGeneraCertificado.getjComboBoxTiempo().getSelectedItem().equals("Meses")) {
                        fallecido.setEdadMeses(Integer.parseInt(vistaGeneraCertificado.getTxtEdadTiempo().getText().trim()));

                    } else if (vistaGeneraCertificado.getjComboBoxTiempo().getSelectedItem().equals("Años cumplidos")) {
                        fallecido.setEdadAnios(Integer.parseInt(vistaGeneraCertificado.getTxtEdadTiempo().getText().trim()));
                    }
                } else {
                    errores.append("El campo Edad Cumplida no debe ser vacío\n");
                }
            } catch (NumberFormatException e) {
                errores.append("El campo Edad Cumplida debe ser un valor numérico\n");
            }


            if ((vistaGeneraCertificado.getjComboBoxTiempo().getSelectedItem().equals("Días")
                    && Integer.parseInt(vistaGeneraCertificado.getTxtEdadTiempo().getText()) < 28)
                    || vistaGeneraCertificado.getjComboBoxTiempo().getSelectedItem().equals("Minutos")
                    || vistaGeneraCertificado.getjComboBoxTiempo().getSelectedItem().equals("Horas")) {
                try {
                    if (!vistaGeneraCertificado.getTxtFolioCertificadoNacimiento().getText().equals("")) {
                        fallecido.setFolioCertificadoNacimiento(vistaGeneraCertificado.getTxtFolioCertificadoNacimiento().getText().trim());
                    } else {
                        errores.append("El campo Folio Certificado de Nacimiento no debe ser vacío\n");
                    }
                    if (!vistaGeneraCertificado.getTxtSemanasGestacion().getText().equals("")) {
                        fallecido.setSemanasGestacion(Integer.parseInt(vistaGeneraCertificado.getTxtSemanasGestacion().getText().trim()));
                    } else {
                        errores.append("El campo Semanas de Gestación no debe ser vacío\n");
                    }
                    if (!vistaGeneraCertificado.getTxtPesoAlNacer().getText().equals("")) {
                        fallecido.setPesoNacimiento(Integer.parseInt(vistaGeneraCertificado.getTxtPesoAlNacer().getText().trim()));
                    } else {
                        errores.append("El campo Peso en Gramos no debe ser vacío\n");
                    }
                } catch (NumberFormatException e) {
                    errores.append("Los valores en el campo Semanas Gestación y Peso al Nacer deben ser numéricos\n");
                }

            }
        } else {
            fallecido.setSeIgnoraEdad(true);
        }

        if (vistaGeneraCertificado.getRdbtnSoltero().isSelected()) {
            fallecido.setSituacionConyugal("Soltera/o");
        } else if (vistaGeneraCertificado.getRdbtnSeparado().isSelected()) {
            fallecido.setSituacionConyugal("Separada/o");
        } else if (vistaGeneraCertificado.getRdbtnUnionLibre().isSelected()) {
            fallecido.setSituacionConyugal("En unión libre");
        } else if (vistaGeneraCertificado.getRdbtnDivorciado().isSelected()) {
            fallecido.setSituacionConyugal("Divorciada/o");
        } else if (vistaGeneraCertificado.getRdbtnCasado().isSelected()) {
            fallecido.setSituacionConyugal("Casada/o");
        } else if (vistaGeneraCertificado.getRdbtnViudo().isSelected()) {
            fallecido.setSituacionConyugal("Viuda/o");
        } else if (vistaGeneraCertificado.getRdbtnSeIgnoraSituacionConyugal().isSelected()) {
            fallecido.setSituacionConyugal("Se ignora");
        } else {
            errores.append("El campo Situación Conyugal no debe ser vacío");
        }

        if (errores.length() > 0) {
            JOptionPane.showMessageDialog(
                    null,
                    errores.toString(),
                    "Errores de validación",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            vistaGeneraCertificado.getTabbedPaneCertificadoDefuncion().setSelectedIndex(3);
        }

    }

    public static void recopilarInformacionVista4() {
        var errores = new StringBuilder();
        if (!vistaGeneraCertificado.getTxtTipoVialidad().getText().equals("")) {
            fallecido.setTipoVialidad(vistaGeneraCertificado.getTxtTipoVialidad().getText().trim());
        } else {
            errores.append("El campo Tipo Vialidad no debe ser vacío\n");
        }
        if (!vistaGeneraCertificado.getTxtNombreVialidad().getText().equals("")) {
            fallecido.setNombreVialidad(vistaGeneraCertificado.getTxtNombreVialidad().getText().trim());
        } else {
            errores.append("El campo Nombre Vialidad no debe ser vacío\n");
        }
        if (!vistaGeneraCertificado.getTxtNumExt().getText().equals("")) {
            fallecido.setNumeroExterior(vistaGeneraCertificado.getTxtNumExt().getText().trim());
        } else {
            errores.append("El campo Número Exterior no debe ser vacío\n");
        }
        if (!vistaGeneraCertificado.getTxtNumInt().getText().equals("")) {
            fallecido.setNumeroInterior(vistaGeneraCertificado.getTxtNumInt().getText().trim());
        } else {
            errores.append("El campo Número Interior no debe ser vacío\n");
        }
        if (!vistaGeneraCertificado.getTxtTipoAsentamientoHumano().getText().equals("")) {
            fallecido.setTipoAsentamientoHumano(vistaGeneraCertificado.getTxtTipoAsentamientoHumano().getText().trim());
        } else {
            errores.append("El campo Tipo Asentamiento Humano no debe ser vacío\n");
        }
        if (!vistaGeneraCertificado.getTxtNombreAsentamientoHumano().getText().equals("")) {
            fallecido.setNombreAsentamientoHumano(vistaGeneraCertificado.getTxtNombreAsentamientoHumano().getText().trim());
        } else {
            errores.append("El campo Nombre Asentamiento Humano no debe ser vacío\n");
        }
        if (!vistaGeneraCertificado.getTxtCodigoPostal().getText().equals("")) {
            fallecido.setCodigoPostal(vistaGeneraCertificado.getTxtCodigoPostal().getText().trim());
        } else {
            errores.append("El campo Código Postal no debe ser vacío\n");
        }
        if (!vistaGeneraCertificado.getTxtLocalidad().getText().equals("")) {
            fallecido.setLocalidad(vistaGeneraCertificado.getTxtLocalidad().getText().trim());
        } else {
            errores.append("El campo Localidad no debe ser vacío\n");
        }
        if (!vistaGeneraCertificado.getTxtMunicipioAlcaldia().getText().equals("")) {
            fallecido.setMunicipioAlcaldia(vistaGeneraCertificado.getTxtMunicipioAlcaldia().getText().trim());
        } else {
            errores.append("El campo Municipio/Alcaldía no debe ser vacío\n");
        }
        if (!vistaGeneraCertificado.getTxtEntidadFederativaPais().getText().equals("")) {
            fallecido.setEntidadFederativaPais(vistaGeneraCertificado.getTxtEntidadFederativaPais().getText().trim());
        } else {
            errores.append("El campo Entidad Federativa/País no debe ser vacío\n");
        }

        if (errores.length() > 0) {
            JOptionPane.showMessageDialog(
                    null,
                    errores.toString(),
                    "Errores de validación",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            vistaGeneraCertificado.getTabbedPaneCertificadoDefuncion().setSelectedIndex(4);
        }
    }

    public static void recopilarInformacionVista5() {
        var errores = new StringBuilder();

        if (!vistaGeneraCertificado.getRdbtnEscolaridadSeIgnora().isSelected()) {
            if (vistaGeneraCertificado.getRdbtnEscolaridadNinguna().isSelected()) {
                fallecido.setEscolaridad("Ninguna");
            } else if (vistaGeneraCertificado.getRdbtnEscolaridadPrimaria().isSelected()) {
                fallecido.setEscolaridad("Primaria");
            } else if (vistaGeneraCertificado.getRdbtnEscolaridadBachilleratoPreparatoria().isSelected()) {
                fallecido.setEscolaridad("Bachillerato o Preparatoria");
            } else if (vistaGeneraCertificado.getRdbtnEscolaridadPosgrado().isSelected()) {
                fallecido.setEscolaridad("Posgrado");
            } else if (vistaGeneraCertificado.getRdbtnEscolaridadPreescolar().isSelected()) {
                fallecido.setEscolaridad("Preescolar");
            } else if (vistaGeneraCertificado.getRdbtnEscolaridadSecundaria().isSelected()) {
                fallecido.setEscolaridad("Secundaria");
            } else if (vistaGeneraCertificado.getRdbtnEscolaridadProfesional().isSelected()) {
                fallecido.setEscolaridad("Profesional");
            } else {
                errores.append("El campo Escolaridad no debe ser vacío\n");
            }
        } else {
            fallecido.setEscolaridad("Se ignora");
        }

        if (!vistaGeneraCertificado.getRdbtnEscolaridadNinguna().isSelected()
                && !vistaGeneraCertificado.getRdbtnEscolaridadSeIgnora().isSelected()) {
            if (vistaGeneraCertificado.getRdbtnEscolaridadCompleta().isSelected()) {
                fallecido.setTipoEscolaridad("Completa");
            } else if (vistaGeneraCertificado.getRdbtnEscolaridadIncompleta().isSelected()) {
                fallecido.setTipoEscolaridad("Incompleta");
            } else {
                errores.append("El campo Tipo Escolaridad no debe ser vacío\n");
            }
        }

        if (!vistaGeneraCertificado.getRdbtnOcupacionHabitualSeIgnora().isSelected()) {
            fallecido.setOcupacionHabitual(true);
            if (!vistaGeneraCertificado.getTxtOcupacionHabitual().getText().equals("")) {
                fallecido.setDescripcionOcupacion(vistaGeneraCertificado.getTxtOcupacionHabitual().getText().trim());
            } else {
                errores.append("El campo Ocupacion Habitual no debe ser vacío\n");
            }
        } else {
            fallecido.setOcupacionHabitual(false);
        }

        if (vistaGeneraCertificado.getRdbtnSiTrabajaba().isSelected()) {
            fallecido.setTrabajaba("Si");
        } else if (vistaGeneraCertificado.getRdbtnNoTrabajaba().isSelected()) {
            fallecido.setTrabajaba("No");
        } else if (vistaGeneraCertificado.getRdbtnSeIgnoraTrabajaba().isSelected()) {
            fallecido.setTrabajaba("Se ignora");
        } else {
            errores.append("El campo Trabajaba no debe ser vacío");
        }

        if (errores.length() > 0) {
            JOptionPane.showMessageDialog(
                    null,
                    errores.toString(),
                    "Errores de validación",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            vistaGeneraCertificado.getTabbedPaneCertificadoDefuncion().setSelectedIndex(5);
        }
    }

    public static void recopilarInformacionVista6() {
        informante = new Informante();
        var errores = new StringBuilder();

        if (!vistaGeneraCertificado.getRdbtnServiciosDeSaludNinguna().isSelected()
                && !vistaGeneraCertificado.getRdbtnServiciosDeSaludSeIgnora().isSelected()) {
            if (vistaGeneraCertificado.getRdbtnServiciosDeSaludIMSS().isSelected()) {
                fallecido.setAfiliacionServicioSalud("IMSS");
            } else if (vistaGeneraCertificado.getRdbtnServiciosDeSaludISSSTE().isSelected()) {
                fallecido.setAfiliacionServicioSalud("ISSSTE");
            } else if (vistaGeneraCertificado.getRdbtnServiciosDeSaludPEMEX().isSelected()) {
                fallecido.setAfiliacionServicioSalud("PEMEX");
            } else if (vistaGeneraCertificado.getRdbtnServiciosDeSaludSEDENA().isSelected()) {
                fallecido.setAfiliacionServicioSalud("SEDENA");
            } else if (vistaGeneraCertificado.getRdbtnServiciosDeSaludSEMAR().isSelected()) {
                fallecido.setAfiliacionServicioSalud("SEMAR");
            } else if (vistaGeneraCertificado.getRdbtnServiciosDeSaludIMSSBIENESTAR().isSelected()) {
                fallecido.setAfiliacionServicioSalud("IMSS BIENESTAR");
            } else if (vistaGeneraCertificado.getRdbtnServiciosDeSaludISSFAM().isSelected()) {
                fallecido.setAfiliacionServicioSalud("ISSFAM");
            } else if (vistaGeneraCertificado.getRdbtnServiciosDeSaludOtra().isSelected()) {
                fallecido.setAfiliacionServicioSalud("Otra");
                if (!vistaGeneraCertificado.getTxtServiciosDeSaludEspecifique().getText().equals("")) {
                    fallecido.setEspecifiqueServicioSalud(vistaGeneraCertificado.getTxtServiciosDeSaludEspecifique().getText().trim());
                } else {
                    errores.append("El campo Específique Servicio de Salud no debe ser vacío\n");
                }
            } else {
                errores.append("El campo Servicio de Salud no debe ser vacío\n");
            }
            if (!vistaGeneraCertificado.getTxtNumeroSeguridadSocial().getText().equals("")) {
                fallecido.setNumeroSeguridadSocialAfiliacion(vistaGeneraCertificado.getTxtNumeroSeguridadSocial().getText().trim());
            } else {
                errores.append("El campo Número de Seguridad Social no debe ser vacío\n");
            }
        }

        if (!vistaGeneraCertificado.getTxtNombreInf().getText().equals("")) {
            informante.setNombre(vistaGeneraCertificado.getTxtNombreInf().getText().trim());
        } else {
            errores.append("El campo Nombre del Informante no debe ser vacío\n");
        }
        if (!vistaGeneraCertificado.getTxtApellidoPaternoInf().getText().equals("")) {
            informante.setApellidoPaterno(vistaGeneraCertificado.getTxtApellidoPaternoInf().getText().trim());
        } else {
            errores.append("El campo Apellido Paterno del Informante no debe ser vacío\n");
        }
        if (!vistaGeneraCertificado.getTxtApellidoMaternoInf().getText().equals("")) {
            informante.setApellidoMaterno(vistaGeneraCertificado.getTxtApellidoMaternoInf().getText().trim());
        } else {
            errores.append("El campo Apellido Materno del Informante no debe ser vacío");
        }

        if (errores.length() > 0) {
            JOptionPane.showMessageDialog(
                    null,
                    errores.toString(),
                    "Errores de validación",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            vistaGeneraCertificado.getTabbedPaneCertificadoDefuncion().setSelectedIndex(6);
        }
    }

    public static void deshabilitarHabilitarCamposEdadCumplida() {
        try {
            if (vistaGeneraCertificado.getRdbtnSeIgnoraEdad().isSelected()) {
                vistaGeneraCertificado.getTxtFolioCertificadoNacimiento().setEditable(false);
                vistaGeneraCertificado.getTxtFolioCertificadoNacimiento().setEnabled(false);
                vistaGeneraCertificado.getTxtSemanasGestacion().setEditable(false);
                vistaGeneraCertificado.getTxtSemanasGestacion().setEnabled(false);
                vistaGeneraCertificado.getTxtPesoAlNacer().setEditable(false);
                vistaGeneraCertificado.getTxtPesoAlNacer().setEnabled(false);
                vistaGeneraCertificado.getTxtEdadTiempo().setEditable(false);
                vistaGeneraCertificado.getTxtEdadTiempo().setEnabled(false);
                vistaGeneraCertificado.getjComboBoxTiempo().setEnabled(false);
            } else if ((vistaGeneraCertificado.getjComboBoxTiempo().getSelectedItem().equals("Días")
                    && Integer.parseInt(vistaGeneraCertificado.getTxtEdadTiempo().getText()) < 28)
                    || vistaGeneraCertificado.getjComboBoxTiempo().getSelectedItem().equals("Minutos")
                    || vistaGeneraCertificado.getjComboBoxTiempo().getSelectedItem().equals("Horas")) {

                vistaGeneraCertificado.getTxtFolioCertificadoNacimiento().setEditable(true);
                vistaGeneraCertificado.getTxtFolioCertificadoNacimiento().setEnabled(true);
                vistaGeneraCertificado.getTxtSemanasGestacion().setEditable(true);
                vistaGeneraCertificado.getTxtSemanasGestacion().setEnabled(true);
                vistaGeneraCertificado.getTxtPesoAlNacer().setEditable(true);
                vistaGeneraCertificado.getTxtPesoAlNacer().setEnabled(true);
                vistaGeneraCertificado.getTxtEdadTiempo().setEditable(true);
                vistaGeneraCertificado.getTxtEdadTiempo().setEnabled(true);
                vistaGeneraCertificado.getjComboBoxTiempo().setEnabled(true);
            } else {
                vistaGeneraCertificado.getTxtFolioCertificadoNacimiento().setEditable(false);
                vistaGeneraCertificado.getTxtFolioCertificadoNacimiento().setEnabled(false);
                vistaGeneraCertificado.getTxtSemanasGestacion().setEditable(false);
                vistaGeneraCertificado.getTxtSemanasGestacion().setEnabled(false);
                vistaGeneraCertificado.getTxtPesoAlNacer().setEditable(false);
                vistaGeneraCertificado.getTxtPesoAlNacer().setEnabled(false);
            }
        } catch (NumberFormatException e) {

        }

    }

    public static void deshabilitarHabilitarTextFieldNombre() {
        if (vistaGeneraCertificado.getRdbtnPersonaDesconocida().isSelected()) {
            vistaGeneraCertificado.getTxtNombre().setEnabled(false);
            vistaGeneraCertificado.getTxtApellidoPaterno().setEnabled(false);
            vistaGeneraCertificado.getTxtApellidoMaterno().setEnabled(false);
        } else {
            vistaGeneraCertificado.getTxtNombre().setEnabled(true);
            vistaGeneraCertificado.getTxtApellidoPaterno().setEnabled(true);
            vistaGeneraCertificado.getTxtApellidoMaterno().setEnabled(true);
        }
    }

    public static void deshabilitarHabilitarOcupacionDescripcion() {
        if (vistaGeneraCertificado.getRdbtnOcupacionHabitualSeIgnora().isSelected()) {
            vistaGeneraCertificado.getTxtOcupacionHabitual().setEnabled(false);
            vistaGeneraCertificado.getTxtOcupacionHabitual().setEditable(false);
            vistaGeneraCertificado.getTxtOcupacionHabitual().setText("");
        } else if (!vistaGeneraCertificado.getRdbtnOcupacionHabitualSeIgnora().isSelected()) {
            vistaGeneraCertificado.getTxtOcupacionHabitual().setEnabled(true);
            vistaGeneraCertificado.getTxtOcupacionHabitual().setEditable(true);
        }
    }

    public static void deshabilitarHabilitarTipoEscolaridad() {
        if (vistaGeneraCertificado.getRdbtnEscolaridadSeIgnora().isSelected()
                || vistaGeneraCertificado.getRdbtnEscolaridadNinguna().isSelected()) {
            vistaGeneraCertificado.getRdbtnEscolaridadCompleta().setEnabled(false);
            vistaGeneraCertificado.getRdbtnEscolaridadIncompleta().setEnabled(false);
        } else {
            vistaGeneraCertificado.getRdbtnEscolaridadCompleta().setEnabled(true);
            vistaGeneraCertificado.getRdbtnEscolaridadIncompleta().setEnabled(true);
        }
    }

    public static void deshabilitarHabilitarCamposServicioSalud() {
        if (vistaGeneraCertificado.getRdbtnServiciosDeSaludSeIgnora().isSelected()
                || vistaGeneraCertificado.getRdbtnServiciosDeSaludNinguna().isSelected()) {
            vistaGeneraCertificado.getTxtNumeroSeguridadSocial().setEditable(false);
            vistaGeneraCertificado.getTxtNumeroSeguridadSocial().setEnabled(false);
            vistaGeneraCertificado.getTxtServiciosDeSaludEspecifique().setEnabled(false);
            vistaGeneraCertificado.getTxtServiciosDeSaludEspecifique().setEditable(false);
        } else if (vistaGeneraCertificado.getRdbtnServiciosDeSaludOtra().isSelected()) {
            vistaGeneraCertificado.getTxtServiciosDeSaludEspecifique().setEnabled(true);
            vistaGeneraCertificado.getTxtServiciosDeSaludEspecifique().setEditable(true);
            vistaGeneraCertificado.getTxtNumeroSeguridadSocial().setEditable(true);
            vistaGeneraCertificado.getTxtNumeroSeguridadSocial().setEnabled(true);
        } else {
            vistaGeneraCertificado.getTxtServiciosDeSaludEspecifique().setEnabled(false);
            vistaGeneraCertificado.getTxtServiciosDeSaludEspecifique().setEditable(false);
            vistaGeneraCertificado.getTxtNumeroSeguridadSocial().setEditable(true);
            vistaGeneraCertificado.getTxtNumeroSeguridadSocial().setEnabled(true);
        }
    }

}
