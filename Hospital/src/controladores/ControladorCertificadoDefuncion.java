package controladores;

import javax.swing.JOptionPane;

import modelos.*;
import vistas.VistaGeneraCertificado;

import java.time.LocalDate;
import java.time.Period;

public class ControladorCertificadoDefuncion {

    // Instancia de la vista para generar el certificado de defunción
    public static VistaGeneraCertificado vistaGeneraCertificado = new VistaGeneraCertificado();

    // Instancia de la clase Fallecido para almacenar datos del fallecido
    private static Fallecido fallecido;

    // Instancia de la clase Informante para almacenar datos del informante
    private static Informante informante;

    // Instancia de la clase Defuncion para gestionar los datos de la defunción
    private static Defuncion defuncion;

    // Instancia de la clase Situacion para manejar datos adicionales relacionados
    private static Situacion situacion;

    // Instancia de la clase Certificante para gestionar los datos del certificante
    private static Certificante certificante;

    // Instancia de la clase RegistroCivil para manejar los datos del registro civil
    private static RegistroCivil registroCivil;

    // Bandera para validar si se aplica la condición de mujer entre 10 y 54 años
    private static boolean isMujer1054 = false;

    public static void mostrar() {
        vistaGeneraCertificado.setVisible(true);
    }

    public static void recopilarInformacionVista1() {
//        Se crea una instancia de Fallecido para aignarle los datos
        fallecido = new Fallecido();
//        Stringbuilder para gestionar los errores de validación
        var errores = new StringBuilder();

//        Condición que valida si el fallecido NO es desconocido, en caso de cumplirse
//        se validan los campos referentes al nombre del fallecido
        if (!vistaGeneraCertificado.getRdbtnPersonaDesconocida().isSelected()) {
            fallecido.setDesconocido(false);
//            Se validan que los Jtextfield no sean vacíos, en caso de no cumplirse
//            se agrega un String al Stringbuilder con el error de validación
            if (!vistaGeneraCertificado.getTxtNombre().getText().isEmpty()
                    && !vistaGeneraCertificado.getTxtNombre().getText().isBlank()) {
                fallecido.setNombre(vistaGeneraCertificado.getTxtNombre().getText().trim());
            } else {
                errores.append("El campo Nombre no debe ser vacío\n");
            }
            if (!vistaGeneraCertificado.getTxtApellidoPaterno().getText().isEmpty()
                    && !vistaGeneraCertificado.getTxtApellidoPaterno().getText().isBlank()) {
                fallecido.setApellidoPaterno(vistaGeneraCertificado.getTxtApellidoPaterno().getText().trim());
            } else {
                errores.append("El campo Apellido Paterno no debe ser vacío\n");
            }
            if (!vistaGeneraCertificado.getTxtApellidoMaterno().getText().isEmpty()
                    && !vistaGeneraCertificado.getTxtApellidoMaterno().getText().isBlank()) {
                fallecido.setApellidoMaterno(vistaGeneraCertificado.getTxtApellidoMaterno().getText().trim());
            } else {
                errores.append("El campo Apellido Materno no debe ser vacío\n");
            }
        } else {
            fallecido.setDesconocido(true);
        }
//        Bloque try para manejar las excepciones que puedan surgir por el parseo al pasar los valores de las fechas
//        de String a Integer
        try {
            String diaTexto = vistaGeneraCertificado.getTxtDiaNacimiento().getText().trim();
            String mesTexto = vistaGeneraCertificado.getTxtMesNacimiento().getText().trim();
            String anioTexto = vistaGeneraCertificado.getTxtAñoNacimiento().getText().trim();

//            Condicion para validar que los JtextField de la edad no sean vacíos
            if (!diaTexto.isEmpty() && !mesTexto.isEmpty() && !anioTexto.isEmpty()) {
//                String para juntar los valores de las fechas y crear el formato dd/mm/aaaa
                String fechaCompleta = diaTexto + "/" + mesTexto + "/" + anioTexto;

//                Condición que utiliza un regex para validar que los valores ingresados sean números
//                y se respete el formato dd/mm/aaaa
                if (fechaCompleta.matches("^([0-2][0-9]|3[0-1])/(0[1-9]|1[0-2])/\\d{4}$")) {
//                    Parseo de String a Integer
                    int dia = Integer.parseInt(diaTexto);
                    int mes = Integer.parseInt(mesTexto);
                    int anio = Integer.parseInt(anioTexto);

//                    Condición que evalua los valores de día, mes y año
                    if (dia > 0 && dia <= 31 && mes > 0 && mes <= 12 && anio > 0) {
                        fallecido.setDiaNacimiento(dia);
                        fallecido.setMesNacimiento(mes);
                        fallecido.setAnioNacimiento(anio);

//                        Validacion adicional de fecha por calendario
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
//            Atrapa las excepciones que puedan surgir por un parseo de texto que no sea numérico a Integer
//            o una fecha que no sea válida
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

//        Condición que valida si se agregaron Strings al Stringbuilder, en caso cumplirse se muestran
//        los errores de validación, en caso contrario avanza a la siguiente pestaña del formulario
        if (errores.length() > 0) {
            JOptionPane.showMessageDialog(
                    null,
                    errores.toString(),
                    "Errores de validación",
                    JOptionPane.WARNING_MESSAGE);
        } else {
//            Muestra la siguiente pestaña del formulario
            vistaGeneraCertificado.getTabbedPaneCertificadoDefuncion().setSelectedIndex(1);
        }
    }

    public static void recopilarInformacionVista2() {
        // Construye un StringBuilder para almacenar mensajes de error
        var errores = new StringBuilder();

        // Valida si el campo "Entidad Nacimiento" no está vacío
        if (!vistaGeneraCertificado.getTxtEntidadNacimiento().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtEntidadNacimiento().getText().isBlank()) {
            fallecido.setEntidadNacimiento(vistaGeneraCertificado.getTxtEntidadNacimiento().getText().trim());
        } else {
            errores.append("El campo Entidad Nacimiento no debe ser vacío\n");
        }

        // Valida si el campo "CURP" no está vacío
        if (!vistaGeneraCertificado.getTxtCurp().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtCurp().getText().isBlank()) {
            fallecido.setCurp(vistaGeneraCertificado.getTxtCurp().getText().trim());
        } else {
            errores.append("El campo Curp no debe ser vacío\n");
        }

        // Valida la opción seleccionada para "Afromexicano"
        if (vistaGeneraCertificado.getRdbtnSiAfromexicano().isSelected()) {
            fallecido.setAfromexicano("Si");
        } else if (vistaGeneraCertificado.getRdbtnNoAfromexicano().isSelected()) {
            fallecido.setAfromexicano("No");
        } else if (vistaGeneraCertificado.getRdbtnSeIgnoraAfromexicano().isSelected()) {
            fallecido.setAfromexicano("Se ignora");
        } else {
            errores.append("El campo Afromexicano no debe ser vacío\n");
        }

        // Valida la opción seleccionada para "Indígena"
        if (vistaGeneraCertificado.getRdbtnSiIndigena().isSelected()) {
            fallecido.setIndigena("Si");
        } else if (vistaGeneraCertificado.getRdbtnNoIndigena().isSelected()) {
            fallecido.setIndigena("No");
        } else if (vistaGeneraCertificado.getRdbtnSeIgnoraIndigena().isSelected()) {
            fallecido.setIndigena("Se ignora");
        } else {
            errores.append("El campo Indigena no debe ser vacío\n");
        }

        // Validación para "Habla Lengua Indígena" y campo adicional en caso de respuesta afirmativa
        if (vistaGeneraCertificado.getRdbtnSiLenguaIndigena().isSelected()) {
            fallecido.setHablaIndigena("Si");
            if (!vistaGeneraCertificado.getTxtEspecifiqueLenguaIndigena().getText().isEmpty()
                    && !vistaGeneraCertificado.getTxtEspecifiqueLenguaIndigena().getText().isBlank()) {
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

        // Valida la opción seleccionada para "Nacionalidad"
        if (vistaGeneraCertificado.getRdbtnNacionalidadMex().isSelected()) {
            fallecido.setNacionalidad("Mexicana");
        } else if (vistaGeneraCertificado.getRdbtnOtraNacionalidad().isSelected()) {
            fallecido.setNacionalidad("Otra");
            // Si la nacionalidad es "Otra", se valida el campo adicional
            if (!vistaGeneraCertificado.getTxtEspecifiqueNacionalidad().getText().isEmpty()
                    && !vistaGeneraCertificado.getTxtEspecifiqueNacionalidad().getText().isBlank()) {
                fallecido.setEspecifiqueNacionalidad(vistaGeneraCertificado.getTxtEspecifiqueNacionalidad().getText().trim());
            } else {
                errores.append("El campo Especifique Nacionalidad no debe ser vacío\n");
            }
        } else if (vistaGeneraCertificado.getRdbtnSeIgnoraNacionalidad().isSelected()) {
            fallecido.setNacionalidad("Se ignora");
        } else {
            errores.append("El campo Nacionalidad no debe ser vacío");
        }

        // Si existen errores, muestra un cuadro de diálogo con los mensajes de error
        if (errores.length() > 0) {
            JOptionPane.showMessageDialog(
                    null,
                    errores.toString(),
                    "Errores de validación",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            // Si no hay errores, avanza a la siguiente pestaña del formulario
            vistaGeneraCertificado.getTabbedPaneCertificadoDefuncion().setSelectedIndex(2);
        }
    }

    public static void recopilarInformacionVista3() {
        // Construye un StringBuilder para acumular mensajes de error
        var errores = new StringBuilder();

        // Verifica si la edad no está marcada como "Se ignora"
        if (!vistaGeneraCertificado.getRdbtnSeIgnoraEdad().isSelected()) {
            fallecido.setSeIgnoraEdad(false);
            try {
                // Valida si el campo de tiempo de edad no está vacío
                if (!vistaGeneraCertificado.getTxtEdadTiempo().getText().isEmpty()
                        && !vistaGeneraCertificado.getTxtEdadTiempo().getText().isBlank()) {
                    // Asigna la edad según la unidad seleccionada en el ComboBox
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
                // Agrega mensaje de error si el valor no es numérico
                errores.append("El campo Edad Cumplida debe ser un valor numérico\n");
            }

            // Valida información adicional si la edad es menor a 28 días o está en menor unidad de tiempo
            if ((vistaGeneraCertificado.getjComboBoxTiempo().getSelectedItem().equals("Días")
                    && Integer.parseInt(vistaGeneraCertificado.getTxtEdadTiempo().getText()) < 28)
                    || vistaGeneraCertificado.getjComboBoxTiempo().getSelectedItem().equals("Minutos")
                    || vistaGeneraCertificado.getjComboBoxTiempo().getSelectedItem().equals("Horas")) {
                try {
                    // Valida y asigna el folio del certificado de nacimiento
                    if (!vistaGeneraCertificado.getTxtFolioCertificadoNacimiento().getText().isEmpty()
                            && !vistaGeneraCertificado.getTxtFolioCertificadoNacimiento().getText().isBlank()) {
                        fallecido.setFolioCertificadoNacimiento(vistaGeneraCertificado.getTxtFolioCertificadoNacimiento().getText().trim());
                    } else {
                        errores.append("El campo Folio Certificado de Nacimiento no debe ser vacío\n");
                    }
                    // Valida y asigna las semanas de gestación
                    if (!vistaGeneraCertificado.getTxtSemanasGestacion().getText().isEmpty()
                            && !vistaGeneraCertificado.getTxtSemanasGestacion().getText().isBlank()) {
                        fallecido.setSemanasGestacion(Integer.parseInt(vistaGeneraCertificado.getTxtSemanasGestacion().getText().trim()));
                    } else {
                        errores.append("El campo Semanas de Gestación no debe ser vacío\n");
                    }
                    // Valida y asigna el peso al nacer
                    if (!vistaGeneraCertificado.getTxtPesoAlNacer().getText().isEmpty()
                            && !vistaGeneraCertificado.getTxtPesoAlNacer().getText().isBlank()) {
                        fallecido.setPesoNacimiento(Integer.parseInt(vistaGeneraCertificado.getTxtPesoAlNacer().getText().trim()));
                    } else {
                        errores.append("El campo Peso en Gramos no debe ser vacío\n");
                    }
                } catch (NumberFormatException e) {
                    // Agrega mensaje de error si los valores no son numéricos
                    errores.append("Los valores en el campo Semanas Gestación y Peso al Nacer deben ser numéricos\n");
                }
            }
        } else {
            // Marca la edad como "Se ignora"
            fallecido.setSeIgnoraEdad(true);
        }

// Valida la situación conyugal seleccionada
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
            // Agrega mensaje de error si no se seleccionó ninguna opción
            errores.append("El campo Situación Conyugal no debe ser vacío");
        }

// Muestra un cuadro de diálogo si existen errores de validación
        if (errores.length() > 0) {
            JOptionPane.showMessageDialog(
                    null,
                    errores.toString(),
                    "Errores de validación",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            // Avanza a la siguiente pestaña del formulario si no hay errores
            vistaGeneraCertificado.getTabbedPaneCertificadoDefuncion().setSelectedIndex(3);
        }

    }

    public static void recopilarInformacionVista4() {
        // Crear un acumulador para mensajes de error
        var errores = new StringBuilder();

        // Validar y asignar el campo "Tipo Vialidad"
        if (!vistaGeneraCertificado.getTxtTipoVialidad().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtTipoVialidad().getText().isBlank()) {
            fallecido.setTipoVialidad(vistaGeneraCertificado.getTxtTipoVialidad().getText().trim());
        } else {
            errores.append("El campo Tipo Vialidad no debe ser vacío\n");
        }

        // Validar y asignar el campo "Nombre Vialidad"
        if (!vistaGeneraCertificado.getTxtNombreVialidad().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtNombreVialidad().getText().isBlank()) {
            fallecido.setNombreVialidad(vistaGeneraCertificado.getTxtNombreVialidad().getText().trim());
        } else {
            errores.append("El campo Nombre Vialidad no debe ser vacío\n");
        }

        // Validar y asignar el campo "Número Exterior"
        if (!vistaGeneraCertificado.getTxtNumExt().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtNumExt().getText().isBlank()) {
            fallecido.setNumeroExterior(vistaGeneraCertificado.getTxtNumExt().getText().trim());
        } else {
            errores.append("El campo Número Exterior no debe ser vacío\n");
        }

        // Validar y asignar el campo "Número Interior"
        if (!vistaGeneraCertificado.getTxtNumInt().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtNumInt().getText().isBlank()) {
            fallecido.setNumeroInterior(vistaGeneraCertificado.getTxtNumInt().getText().trim());
        } else {
            errores.append("El campo Número Interior no debe ser vacío\n");
        }

        // Validar y asignar el campo "Tipo Asentamiento Humano"
        if (!vistaGeneraCertificado.getTxtTipoAsentamientoHumano().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtTipoAsentamientoHumano().getText().isBlank()) {
            fallecido.setTipoAsentamientoHumano(vistaGeneraCertificado.getTxtTipoAsentamientoHumano().getText().trim());
        } else {
            errores.append("El campo Tipo Asentamiento Humano no debe ser vacío\n");
        }

        // Validar y asignar el campo "Nombre Asentamiento Humano"
        if (!vistaGeneraCertificado.getTxtNombreAsentamientoHumano().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtNombreAsentamientoHumano().getText().isBlank()) {
            fallecido.setNombreAsentamientoHumano(vistaGeneraCertificado.getTxtNombreAsentamientoHumano().getText().trim());
        } else {
            errores.append("El campo Nombre Asentamiento Humano no debe ser vacío\n");
        }

        // Validar y asignar el campo "Código Postal"
        if (!vistaGeneraCertificado.getTxtCodigoPostal().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtCodigoPostal().getText().isBlank()) {
            fallecido.setCodigoPostal(vistaGeneraCertificado.getTxtCodigoPostal().getText().trim());
        } else {
            errores.append("El campo Código Postal no debe ser vacío\n");
        }

        // Validar y asignar el campo "Localidad"
        if (!vistaGeneraCertificado.getTxtLocalidad().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtLocalidad().getText().isBlank()) {
            fallecido.setLocalidad(vistaGeneraCertificado.getTxtLocalidad().getText().trim());
        } else {
            errores.append("El campo Localidad no debe ser vacío\n");
        }

        // Validar y asignar el campo "Municipio/Alcaldía"
        if (!vistaGeneraCertificado.getTxtMunicipioAlcaldia().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtMunicipioAlcaldia().getText().isBlank()) {
            fallecido.setMunicipioAlcaldia(vistaGeneraCertificado.getTxtMunicipioAlcaldia().getText().trim());
        } else {
            errores.append("El campo Municipio/Alcaldía no debe ser vacío\n");
        }

        // Validar y asignar el campo "Entidad Federativa/País"
        if (!vistaGeneraCertificado.getTxtEntidadFederativaPais().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtEntidadFederativaPais().getText().isBlank()) {
            fallecido.setEntidadFederativaPais(vistaGeneraCertificado.getTxtEntidadFederativaPais().getText().trim());
        } else {
            errores.append("El campo Entidad Federativa/País no debe ser vacío\n");
        }

        // Mostrar mensajes de error si los hay
        if (errores.length() > 0) {
            JOptionPane.showMessageDialog(
                    null,
                    errores.toString(),
                    "Errores de validación",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            // Avanzar a la siguiente pestaña del formulario si no hay errores
            vistaGeneraCertificado.getTabbedPaneCertificadoDefuncion().setSelectedIndex(4);
        }
    }

    public static void recopilarInformacionVista5() {
        // Crear un acumulador para mensajes de error
        var errores = new StringBuilder();

        // Validar el campo "Escolaridad"
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
                // Si no se seleccionó ninguna opción válida, agregar un mensaje de error
                errores.append("El campo Escolaridad no debe ser vacío\n");
            }
        } else {
            // Si se ignora la escolaridad, asignar "Se ignora"
            fallecido.setEscolaridad("Se ignora");
        }

        // Validar el campo "Tipo Escolaridad" si la escolaridad no es "Ninguna" o "Se ignora"
        if (!vistaGeneraCertificado.getRdbtnEscolaridadNinguna().isSelected()
                && !vistaGeneraCertificado.getRdbtnEscolaridadSeIgnora().isSelected()) {
            if (vistaGeneraCertificado.getRdbtnEscolaridadCompleta().isSelected()) {
                fallecido.setTipoEscolaridad("Completa");
            } else if (vistaGeneraCertificado.getRdbtnEscolaridadIncompleta().isSelected()) {
                fallecido.setTipoEscolaridad("Incompleta");
            } else {
                // Si no se seleccionó ninguna opción válida, agregar un mensaje de error
                errores.append("El campo Tipo Escolaridad no debe ser vacío\n");
            }
        }

        // Validar el campo "Ocupación Habitual"
        if (!vistaGeneraCertificado.getRdbtnOcupacionHabitualSeIgnora().isSelected()) {
            fallecido.setOcupacionHabitual(true);
            if (!vistaGeneraCertificado.getTxtOcupacionHabitual().getText().isEmpty()
                    && !vistaGeneraCertificado.getTxtOcupacionHabitual().getText().isBlank()) {
                // Asignar el texto del campo de descripción de ocupación
                fallecido.setDescripcionOcupacion(vistaGeneraCertificado.getTxtOcupacionHabitual().getText().trim());
            } else {
                // Si el campo de descripción está vacío, agregar un mensaje de error
                errores.append("El campo Ocupacion Habitual no debe ser vacío\n");
            }
        } else {
            // Si la ocupación habitual se ignora, marcarlo como falso
            fallecido.setOcupacionHabitual(false);
        }

        // Validar el campo "Trabajaba"
        if (vistaGeneraCertificado.getRdbtnSiTrabajaba().isSelected()) {
            fallecido.setTrabajaba("Si");
        } else if (vistaGeneraCertificado.getRdbtnNoTrabajaba().isSelected()) {
            fallecido.setTrabajaba("No");
        } else if (vistaGeneraCertificado.getRdbtnSeIgnoraTrabajaba().isSelected()) {
            fallecido.setTrabajaba("Se ignora");
        } else {
            // Si no se seleccionó ninguna opción válida, agregar un mensaje de error
            errores.append("El campo Trabajaba no debe ser vacío");
        }

        // Mostrar los mensajes de error si existen
        if (errores.length() > 0) {
            JOptionPane.showMessageDialog(
                    null,
                    errores.toString(),
                    "Errores de validación",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            // Si no hay errores, avanzar a la siguiente pestaña del formulario
            vistaGeneraCertificado.getTabbedPaneCertificadoDefuncion().setSelectedIndex(5);
        }
    }

    public static void recopilarInformacionVista6() {
        // Inicializar un nuevo objeto para recopilar datos del informante
        informante = new Informante();
        var errores = new StringBuilder();

        // Validar y asignar la afiliación al servicio de salud
        if (!vistaGeneraCertificado.getRdbtnServiciosDeSaludNinguna().isSelected()
                && !vistaGeneraCertificado.getRdbtnServiciosDeSaludSeIgnora().isSelected()) {
            // Verificar qué servicio de salud está seleccionado y asignarlo
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
                // Si es "Otra", validar y asignar el campo adicional para especificar
                fallecido.setAfiliacionServicioSalud("Otra");
                if (!vistaGeneraCertificado.getTxtServiciosDeSaludEspecifique().getText().isEmpty()
                        && !vistaGeneraCertificado.getTxtServiciosDeSaludEspecifique().getText().isBlank()) {
                    fallecido.setEspecifiqueServicioSalud(vistaGeneraCertificado.getTxtServiciosDeSaludEspecifique().getText().trim());
                } else {
                    // Generar un error si el campo para especificar está vacío
                    errores.append("El campo Específique Servicio de Salud no debe ser vacío\n");
                }
            } else {
                // Generar un error si no se seleccionó ningún servicio de salud válido
                errores.append("El campo Servicio de Salud no debe ser vacío\n");
            }

            // Validar y asignar el número de seguridad social
            if (!vistaGeneraCertificado.getTxtNumeroSeguridadSocial().getText().isEmpty()
                    && !vistaGeneraCertificado.getTxtNumeroSeguridadSocial().getText().isBlank()) {
                fallecido.setNumeroSeguridadSocialAfiliacion(vistaGeneraCertificado.getTxtNumeroSeguridadSocial().getText().trim());
            } else {
                // Generar un error si el campo está vacío
                errores.append("El campo Número de Seguridad Social no debe ser vacío\n");
            }
        }

// Validar y asignar el nombre del informante
        if (!vistaGeneraCertificado.getTxtNombreInf().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtNombreInf().getText().isBlank()) {
            informante.setNombre(vistaGeneraCertificado.getTxtNombreInf().getText().trim());
        } else {
            // Generar un error si el campo está vacío
            errores.append("El campo Nombre del Informante no debe ser vacío\n");
        }

// Validar y asignar el apellido paterno del informante
        if (!vistaGeneraCertificado.getTxtApellidoPaternoInf().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtApellidoPaternoInf().getText().isBlank()) {
            informante.setApellidoPaterno(vistaGeneraCertificado.getTxtApellidoPaternoInf().getText().trim());
        } else {
            // Generar un error si el campo está vacío
            errores.append("El campo Apellido Paterno del Informante no debe ser vacío\n");
        }

// Validar y asignar el apellido materno del informante
        if (!vistaGeneraCertificado.getTxtApellidoMaternoInf().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtApellidoMaternoInf().getText().isBlank()) {
            informante.setApellidoMaterno(vistaGeneraCertificado.getTxtApellidoMaternoInf().getText().trim());
        } else {
            // Generar un error si el campo está vacío
            errores.append("El campo Apellido Materno del Informante no debe ser vacío");
        }

// Verificar si hay errores de validación
        if (errores.length() > 0) {
            // Mostrar mensaje con los errores encontrados
            JOptionPane.showMessageDialog(
                    null,
                    errores.toString(),
                    "Errores de validación",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            // Avanzar a la siguiente pestaña del formulario si no hay errores
            vistaGeneraCertificado.getTabbedPaneCertificadoDefuncion().setSelectedIndex(6);
        }
    }

    public static void recopilarInformacionVista7() {
        defuncion = new Defuncion();
        var errores = new StringBuilder();

        if (!vistaGeneraCertificado.getTxtParentescoFamiliar().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtParentescoFamiliar().getText().isBlank()) {
            informante.setParentescoFallecido(vistaGeneraCertificado.getTxtParentescoFamiliar().getText().trim());
        } else {
            errores.append("El campo Parentesco con el Fallecido no debe ser vacío\n");
        }


        if (vistaGeneraCertificado.getRdbtnDefuncionViaPublica().isSelected()) {
            defuncion.setSitio("Vía pública");
        } else if (vistaGeneraCertificado.getRdbtnDefuncionHogar().isSelected()) {
            defuncion.setSitio("Hogar");
        } else if (vistaGeneraCertificado.getRdbtnDefuncionOtroLugar().isSelected()) {
            defuncion.setSitio("Otro lugar");
        } else if (vistaGeneraCertificado.getRdbtnDefuncionSeIgnora().isSelected()) {
            defuncion.setSitio("Se ignora");
        } else {
            if (vistaGeneraCertificado.getRdbtnDefuncionSecretariaSalud().isSelected()) {
                defuncion.setUnidadMedica("Secretaría de Salud");
            } else if (vistaGeneraCertificado.getRdbtnDefuncionIssste().isSelected()) {
                defuncion.setUnidadMedica("ISSTE");
            } else if (vistaGeneraCertificado.getRdbtnDefuncionSemar().isSelected()) {
                defuncion.setUnidadMedica("SEMAR");
            } else if (vistaGeneraCertificado.getRdbtnDefuncionImssBienestar().isSelected()) {
                defuncion.setUnidadMedica("IMSS BIENESTAR");
            } else if (vistaGeneraCertificado.getRdbtnDefuncionPemex().isSelected()) {
                defuncion.setUnidadMedica("PEMEX");
            } else if (vistaGeneraCertificado.getRdbtnDefuncionOtraUnidadMedica().isSelected()) {
                defuncion.setUnidadMedica("Otra unidad médica pública");
            } else if (vistaGeneraCertificado.getRdbtnDefuncionImss().isSelected()) {
                defuncion.setUnidadMedica("IMSS");
            } else if (vistaGeneraCertificado.getRdbtnDefuncionSedena().isSelected()) {
                defuncion.setUnidadMedica("SEDENA");
            } else if (vistaGeneraCertificado.getRdbtnDefuncionUnidadMedicaPrivada().isSelected()) {
                defuncion.setUnidadMedica("Unidad médica privada");
            } else {
                errores.append("El campo Sitio donde sucedió la defunción no debe ser vacío\n");
            }

            if (!vistaGeneraCertificado.getTxtDefuncionNombreUnidadMedica().getText().isEmpty()
                    && !vistaGeneraCertificado.getTxtDefuncionNombreUnidadMedica().getText().isBlank()) {
                defuncion.setNombreUnidadMedica(vistaGeneraCertificado.getTxtDefuncionNombreUnidadMedica().getText().trim());
            } else {
                errores.append("El campo Nombre Unidad Medica no debe ser vacío\n");
            }
            if (!vistaGeneraCertificado.getTxtDefuncionClaveUnicaEstablecimientos().getText().isEmpty()
                    && !vistaGeneraCertificado.getTxtDefuncionClaveUnicaEstablecimientos().getText().isBlank()) {
                defuncion.setClues(vistaGeneraCertificado.getTxtDefuncionClaveUnicaEstablecimientos().getText().trim());
            } else {
                errores.append("El campo Clave Unica Establecimientos no debe ser vacío");
            }
        }

        if (errores.length() > 0) {
            // Mostrar mensaje con los errores encontrados
            JOptionPane.showMessageDialog(
                    null,
                    errores.toString(),
                    "Errores de validación",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            // Avanzar a la siguiente pestaña del formulario si no hay errores
            vistaGeneraCertificado.getTabbedPaneCertificadoDefuncion().setSelectedIndex(7);
        }
    }

    public static void recopilarInformacionVista8() {
        var errores = new StringBuilder();

        if (!vistaGeneraCertificado.getTxtTipoVialidadDefuncion().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtTipoVialidadDefuncion().getText().isBlank()) {
            defuncion.setTipoVialidad(vistaGeneraCertificado.getTxtTipoVialidadDefuncion().getText().trim());
        } else {
            errores.append("El campo Tipo de Vialidad no debe ser vacío\n");
        }
        if (!vistaGeneraCertificado.getTxtNombreVialidadDefuncion().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtNombreVialidadDefuncion().getText().isBlank()) {
            defuncion.setVialidadNombre(vistaGeneraCertificado.getTxtNombreVialidadDefuncion().getText().trim());
        } else {
            errores.append("El campo Nombre de Vialidad no debe ser vacío\n");
        }
        if (!vistaGeneraCertificado.getTxtNumExtDefuncion().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtNumExtDefuncion().getText().isBlank()) {
            defuncion.setNumeroExterior(vistaGeneraCertificado.getTxtNumExtDefuncion().getText().trim());
        } else {
            errores.append("El campo Número Exterior no debe ser vacío\n");
        }
        if (!vistaGeneraCertificado.getTxtNumIntDefuncion().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtNumIntDefuncion().getText().isBlank()) {
            defuncion.setNumeroInterior(vistaGeneraCertificado.getTxtNumIntDefuncion().getText().trim());
        } else {
            errores.append("El campo Número Interior no debe ser vacío\n");
        }
        if (!vistaGeneraCertificado.getTxtCodigoPostalDefuncion().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtCodigoPostalDefuncion().getText().isBlank()) {
            defuncion.setCodigoPostal(vistaGeneraCertificado.getTxtCodigoPostalDefuncion().getText().trim());
        } else {
            errores.append("El campo Código Postal no debe ser vacío\n");
        }
        if (!vistaGeneraCertificado.getTxtTipoAsentamientoHumanoDefuncion().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtTipoAsentamientoHumanoDefuncion().getText().isBlank()) {
            defuncion.setTipoAsentamientoHumano(vistaGeneraCertificado.getTxtTipoAsentamientoHumanoDefuncion().getText().trim());
        } else {
            errores.append("El campo Tipo de Asentamiento Humano no debe ser vacío\n");
        }
        if (!vistaGeneraCertificado.getTxtNombreAsentamientoHumanoDefuncion().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtNombreAsentamientoHumanoDefuncion().getText().isBlank()) {
            defuncion.setNombreAsentamientoHumano(vistaGeneraCertificado.getTxtNombreAsentamientoHumanoDefuncion().getText().trim());
        } else {
            errores.append("El campo Nombre de Asentamiento Humano no debe ser vacío\n");
        }
        if (!vistaGeneraCertificado.getTxtLocalidadDefuncion().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtLocalidadDefuncion().getText().isBlank()) {
            defuncion.setLocalidad(vistaGeneraCertificado.getTxtLocalidadDefuncion().getText().trim());
        } else {
            errores.append("El campo Localidad no debe ser vacío\n");
        }
        if (!vistaGeneraCertificado.getTxtMunicipioDefuncion().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtMunicipioDefuncion().getText().isBlank()) {
            defuncion.setMunicipioAlcaldia(vistaGeneraCertificado.getTxtMunicipioDefuncion().getText().trim());
        } else {
            errores.append("El campo Municipio o Alcaldía no debe ser vacío\n");
        }
        if (!vistaGeneraCertificado.getTxtEntidadFederativaDefuncion().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtEntidadFederativaDefuncion().getText().isBlank()) {
            defuncion.setEntidadFederativa(vistaGeneraCertificado.getTxtEntidadFederativaDefuncion().getText().trim());
        } else {
            errores.append("El campo Entidad Federativa no debe ser vacío");
        }

        if (errores.length() > 0) {
            // Mostrar mensaje con los errores encontrados
            JOptionPane.showMessageDialog(
                    null,
                    errores.toString(),
                    "Errores de validación",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            // Avanzar a la siguiente pestaña del formulario si no hay errores
            vistaGeneraCertificado.getTabbedPaneCertificadoDefuncion().setSelectedIndex(8);
        }
    }

    public static void recopilarInformacionVista9() {
        var errores = new StringBuilder();

//        Validar que los campos no esten vacios
        if ((!vistaGeneraCertificado.getTxtDiaDefuncion().getText().isEmpty() && !vistaGeneraCertificado.getTxtDiaDefuncion().getText().isBlank())
                && (!vistaGeneraCertificado.getTxtMesDefuncion().getText().isEmpty() && !vistaGeneraCertificado.getTxtMesDefuncion().getText().isBlank())
                && (!vistaGeneraCertificado.getTxtAñoDefuncion().getText().isEmpty() && !vistaGeneraCertificado.getTxtAñoDefuncion().getText().isBlank())
                && (!vistaGeneraCertificado.getTxtHorasDefuncion().getText().isEmpty() && !vistaGeneraCertificado.getTxtHorasDefuncion().getText().isBlank())
                && (!vistaGeneraCertificado.getTxtMinutosDefuncion().getText().isEmpty() && !vistaGeneraCertificado.getTxtMinutosDefuncion().getText().isBlank())) {
//            Validar que los datos ingresados sean numericos y esten en el formato correcto
            try {
                String diaText = vistaGeneraCertificado.getTxtDiaDefuncion().getText().trim();
                String mesText = vistaGeneraCertificado.getTxtMesDefuncion().getText().trim();
                String anioText = vistaGeneraCertificado.getTxtAñoDefuncion().getText().trim();
                int dia = Integer.parseInt(diaText);
                int mes = Integer.parseInt(mesText);
                int anio = Integer.parseInt(anioText);

                String fechaDefuncion = diaText + "/" + mesText + "/" + anioText;

//                Condición que evalua los valores de día, mes y año
                if (fechaDefuncion.matches("^\\d{2}/\\d{2}/\\d{4}$")) {
                    java.time.LocalDate.of(anio, mes, dia);
                    defuncion.setDia(dia);
                    defuncion.setMes(mes);
                    defuncion.setAnio(anio);
                } else {
                    errores.append("El formato de la fecha debe ser dd/mm/aaaa\n");
                }


            } catch (NumberFormatException e) {
                errores.append("Los datos ingresados en la fecha de defunción deben ser numéricos\n");
            } catch (java.time.DateTimeException e) {
                errores.append("La fecha de defunción ingresada no es válida\n");
            }

            try {
                int horas = Integer.parseInt(vistaGeneraCertificado.getTxtHorasDefuncion().getText().trim());
                int minutos = Integer.parseInt(vistaGeneraCertificado.getTxtMinutosDefuncion().getText().trim());
                if (horas > 0 && horas <= 23 && minutos > 0 && minutos <= 59) {
                    defuncion.setHoras(horas);
                    defuncion.setMinutos(minutos);
                } else {
                    errores.append("Formato de hora inválido\n");
                }
            } catch (NumberFormatException e) {
                errores.append("Los datos ingresados en la hora de defunción deben ser numéricos\n");
            }

        } else {
            errores.append("El campo Fecha y Hora de la Defunción no debe ser vacío\n");
        }

        if (vistaGeneraCertificado.getRdbtnSiRealizoCirugia().isSelected()) {
            defuncion.setCirugiaUltimas4Semanas("Si");
            if ((!vistaGeneraCertificado.getTxtDiaRealizoCirugia().getText().isEmpty() && !vistaGeneraCertificado.getTxtDiaRealizoCirugia().getText().isBlank())
                    && (!vistaGeneraCertificado.getTxtMesRealizoCirugia().getText().isEmpty() && !vistaGeneraCertificado.getTxtMesRealizoCirugia().getText().isBlank())
                    && (!vistaGeneraCertificado.getTxtAñoRealizoCirugia().getText().isEmpty() && !vistaGeneraCertificado.getTxtAñoRealizoCirugia().getText().isBlank())) {
//            Validar que los datos ingresados sean numericos y esten en el formato correcto
                try {
                    String diaText = vistaGeneraCertificado.getTxtDiaRealizoCirugia().getText().trim();
                    String mesText = vistaGeneraCertificado.getTxtMesRealizoCirugia().getText().trim();
                    String anioText = vistaGeneraCertificado.getTxtAñoRealizoCirugia().getText().trim();
                    int dia = Integer.parseInt(diaText);
                    int mes = Integer.parseInt(mesText);
                    int anio = Integer.parseInt(anioText);

                    String fechaCirugia = diaText + "/" + mesText + "/" + anioText;

//                Condición que evalua los valores de día, mes y año
                    if (fechaCirugia.matches("^\\d{2}/\\d{2}/\\d{4}$")) {
                        java.time.LocalDate.of(anio, mes, dia);
                        defuncion.setCirugiaDia(dia);
                        defuncion.setCirugiaMes(mes);
                        defuncion.setCirugiaAnio(anio);
                    } else {
                        errores.append("El formato de la fecha debe ser dd/mm/aaaa\n");
                    }


                } catch (NumberFormatException e) {
                    errores.append("Los datos ingresados en la fecha de cirugía deben ser numéricos\n");
                } catch (java.time.DateTimeException e) {
                    errores.append("La fecha ingresada en cirugía no es válida\n");
                }
            } else {
                errores.append("El campo Fecha y Hora de la Cirugía no debe ser vacío\n");
            }

            if (!vistaGeneraCertificado.getTxtMotivoCirugia().getText().isEmpty()
                    && !vistaGeneraCertificado.getTxtMotivoCirugia().getText().isBlank()) {
                defuncion.setCirugiaMotivo(vistaGeneraCertificado.getTxtMotivoCirugia().getText().trim());
            } else {
                errores.append("El campo Motivo Cirugia no debe ser vacío\n");
            }
        } else if (vistaGeneraCertificado.getRdbtnNoRealizoCirugia().isSelected()) {
            defuncion.setCirugiaUltimas4Semanas("No");
        } else if (vistaGeneraCertificado.getRdbtnSeIgnoraRealizoCirugia().isSelected()) {
            defuncion.setCirugiaUltimas4Semanas("Se ignora");
        } else {
            errores.append("El campo Cirugía no debe ser vacío\n");
        }

        if (vistaGeneraCertificado.getRdbtnSiAtencionMedica().isSelected()) {
            defuncion.setAtencionMedica("Si");
        } else if (vistaGeneraCertificado.getRdbtnNoAtencionMedica().isSelected()) {
            defuncion.setAtencionMedica("No");
        } else if (vistaGeneraCertificado.getRdbtnSeIgnoraAtencionMedica().isSelected()) {
            defuncion.setAtencionMedica("Se ignora");
        } else {
            errores.append("El campo Atención Médica no debe ser vacío\n");
        }

        if (vistaGeneraCertificado.getRdbtnSiDefuncionAccidentalViolenta().isSelected()) {
            defuncion.setAccidentalViolenta("Si");
        } else if (vistaGeneraCertificado.getRdbtnNoDefuncionAccidentalViolenta().isSelected()) {
            defuncion.setAccidentalViolenta("No");
        } else if (vistaGeneraCertificado.getRdbtnSeIgnoraDefuncionAccidentalViolenta().isSelected()) {
            defuncion.setAccidentalViolenta("Se ignora");
        } else {
            errores.append("El campo Accidental o Violenta no debe ser vacío");
        }

        if (errores.length() > 0) {
            // Mostrar mensaje con los errores encontrados
            JOptionPane.showMessageDialog(
                    null,
                    errores.toString(),
                    "Errores de validación",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            // Avanzar a la siguiente pestaña del formulario si no hay errores
            vistaGeneraCertificado.getTabbedPaneCertificadoDefuncion().setSelectedIndex(9);
        }
    }

    public static void recopilarInformacionVista10() {
        var errores = new StringBuilder();

        if (vistaGeneraCertificado.getRdbtnTipoDefuncionEnfermedad().isSelected()) {
            defuncion.setDefuncionTipo("Enfermedad");
        } else if (vistaGeneraCertificado.getRdbtnTipoDefuncionAccidente().isSelected()) {
            defuncion.setDefuncionTipo("Accidente");
        } else if (vistaGeneraCertificado.getRdbtnTipoDefuncionAgresion().isSelected()) {
            defuncion.setDefuncionTipo("Agresión");
        } else if (vistaGeneraCertificado.getRdbtnTipoDefuncionIntencionalmente().isSelected()) {
            defuncion.setDefuncionTipo("Lesiones autoinfligidas intencionalmente");
        } else if (vistaGeneraCertificado.getRdbtnTipoDefuncionIntervencionLegal().isSelected()) {
            defuncion.setDefuncionTipo("Intervención legal");
        } else if (vistaGeneraCertificado.getRdbtnTipoDefuncionSeIgnora().isSelected()) {
            defuncion.setDefuncionTipo("Se ignora");
        } else {
            errores.append("El campo Tipo de Defunción no debe ser vacío\n");
        }

        if ((!vistaGeneraCertificado.getTxtCausaDefuncionA().getText().isEmpty() && !vistaGeneraCertificado.getTxtCausaDefuncionA().getText().isBlank())
                && (!vistaGeneraCertificado.getTxtCausaDefuncionB().getText().isEmpty() && !vistaGeneraCertificado.getTxtCausaDefuncionB().getText().isBlank())
                && (!vistaGeneraCertificado.getTxtCausaDefuncionC().getText().isEmpty() && !vistaGeneraCertificado.getTxtCausaDefuncionC().getText().isBlank())
                && (!vistaGeneraCertificado.getTxtCausaDefuncionD().getText().isEmpty() && !vistaGeneraCertificado.getTxtCausaDefuncionD().getText().isBlank())) {
            defuncion.getCausas()[0] = vistaGeneraCertificado.getTxtCausaDefuncionA().getText().trim();
            defuncion.getCausas()[1] = vistaGeneraCertificado.getTxtCausaDefuncionB().getText().trim();
            defuncion.getCausas()[2] = vistaGeneraCertificado.getTxtCausaDefuncionC().getText().trim();
            defuncion.getCausas()[3] = vistaGeneraCertificado.getTxtCausaDefuncionD().getText().trim();

        } else {
            errores.append("Las causas de defunción no deben ser vacías\n");
        }

        if (errores.length() > 0) {
            // Mostrar mensaje con los errores encontrados
            JOptionPane.showMessageDialog(
                    null,
                    errores.toString(),
                    "Errores de validación",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            // Avanzar a la siguiente pestaña del formulario si no hay errores
            vistaGeneraCertificado.getTabbedPaneCertificadoDefuncion().setSelectedIndex(10);
        }
    }

    public static void recopilarInformacionVista11() {
        var errores = new StringBuilder();

        if ((!vistaGeneraCertificado.getTxtIntervaloEnfermedadDefuncionA().getText().isEmpty() && !vistaGeneraCertificado.getTxtIntervaloEnfermedadDefuncionA().getText().isBlank())
                && (!vistaGeneraCertificado.getTxtIntervaloEnfermedadDefuncionB().getText().isEmpty() && !vistaGeneraCertificado.getTxtIntervaloEnfermedadDefuncionB().getText().isBlank())
                && (!vistaGeneraCertificado.getTxtIntervaloEnfermedadDefuncionC().getText().isEmpty() && !vistaGeneraCertificado.getTxtIntervaloEnfermedadDefuncionC().getText().isBlank())
                && (!vistaGeneraCertificado.getTxtIntervaloEnfermedadDefuncionD().getText().isEmpty() && !vistaGeneraCertificado.getTxtIntervaloEnfermedadDefuncionD().getText().isBlank())) {
            defuncion.getIntervaloTiempo()[0] = vistaGeneraCertificado.getTxtIntervaloEnfermedadDefuncionA().getText().trim();
            defuncion.getIntervaloTiempo()[1] = vistaGeneraCertificado.getTxtIntervaloEnfermedadDefuncionB().getText().trim();
            defuncion.getIntervaloTiempo()[2] = vistaGeneraCertificado.getTxtIntervaloEnfermedadDefuncionC().getText().trim();
            defuncion.getIntervaloTiempo()[3] = vistaGeneraCertificado.getTxtIntervaloEnfermedadDefuncionD().getText().trim();
        } else {
            errores.append("Los campos de intervalo de tiempo no deben ser vacíos\n");
        }

        if ((!vistaGeneraCertificado.getTxtCodigoCieA().getText().isEmpty() && !vistaGeneraCertificado.getTxtCodigoCieA().getText().isBlank())
                && (!vistaGeneraCertificado.getTxtCodigoCieB().getText().isEmpty() && !vistaGeneraCertificado.getTxtCodigoCieB().getText().isBlank())
                && (!vistaGeneraCertificado.getTxtCodigoCieC().getText().isEmpty() && !vistaGeneraCertificado.getTxtCodigoCieC().getText().isBlank())
                && (!vistaGeneraCertificado.getTxtCodigoCieD().getText().isEmpty() && !vistaGeneraCertificado.getTxtCodigoCieD().getText().isBlank())) {
            defuncion.getCodigoCie()[0] = vistaGeneraCertificado.getTxtCodigoCieA().getText().trim();
            defuncion.getCodigoCie()[1] = vistaGeneraCertificado.getTxtCodigoCieB().getText().trim();
            defuncion.getCodigoCie()[2] = vistaGeneraCertificado.getTxtCodigoCieC().getText().trim();
            defuncion.getCodigoCie()[3] = vistaGeneraCertificado.getTxtCodigoCieD().getText().trim();
        } else {
            errores.append("Los campos códigos CIE no deben ser vacíos\n");
        }

        if ((!vistaGeneraCertificado.getTxtOtrasCondiciones().getText().isEmpty() && !vistaGeneraCertificado.getTxtOtrasCondiciones().getText().isBlank())
                && (!vistaGeneraCertificado.getTxtCodigoCieIntervaloTiempo().getText().isEmpty() && !vistaGeneraCertificado.getTxtCodigoCieIntervaloTiempo().getText().isBlank())) {
            defuncion.getCausas()[4] = vistaGeneraCertificado.getTxtOtrasCondiciones().getText().trim();
            defuncion.getCodigoCie()[4] = vistaGeneraCertificado.getTxtCodigoCieIntervaloTiempo().getText().trim();
        }

        if (errores.length() > 0) {
            // Mostrar mensaje con los errores encontrados
            JOptionPane.showMessageDialog(
                    null,
                    errores.toString(),
                    "Errores de validación",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            // Avanzar a la siguiente pestaña del formulario si no hay errores
            vistaGeneraCertificado.getTabbedPaneCertificadoDefuncion().setSelectedIndex(11);

            //Funcion extra para la siguiente vista, para las mujeres entre 10 y 54 años
//            if(fallecido.getEdadAnios() >= 10 && fallecido.getEdadAnios() <= 54){
//                habilitarCamposMujer();
//            }
            int edadFallecido = calcularEdad(fallecido.getDiaNacimiento(), fallecido.getMesNacimiento(), fallecido.getAnioNacimiento());
            if ((fallecido.getSexo().equals("Mujer")) && (edadFallecido >= 10 && edadFallecido <= 54)) {
                isMujer1054 = true;
                habilitarCamposMujer();
            }
        }
    }

    public static void recopilarInformacionVista12() {
        var errores = new StringBuilder();

        if (vistaGeneraCertificado.getRdbtnSiPracticoNecropsia().isSelected()) {
            defuncion.setNecropsia("Si");
            if (vistaGeneraCertificado.getRdbtnSiUtilizaronHallazgos().isSelected()) {
                defuncion.setHallazgosCertificacion("Si");
            } else if (vistaGeneraCertificado.getRdbtnNoUtilizaronHallazgos().isSelected()) {
                defuncion.setHallazgosCertificacion("No");
            } else {
                errores.append("El campo se utilizaron hallazgos no debe ser vacío\n");
            }
        } else if (vistaGeneraCertificado.getRdbtnNoPracticoNecropsia().isSelected()) {
            defuncion.setNecropsia("No");
        } else if (vistaGeneraCertificado.getRdbtnSeIgnoraPracticoNecropsia().isSelected()) {
            defuncion.setNecropsia("Se ignora");
        } else {
            errores.append("El campo Necropsia no debe ser vacío\n");
        }

        if (vistaGeneraCertificado.getRdbtnSiMuerteEncefalica().isSelected()) {
            defuncion.setMuerteEncefalica("Si");
            if (vistaGeneraCertificado.getRdbtnSiDonadorOrganos().isSelected()) {
                defuncion.setDonadorOrganos("Si");
            } else if (vistaGeneraCertificado.getRdbtnNoDonadorOrganos().isSelected()) {
                defuncion.setDonadorOrganos("No");
            } else {
                errores.append("El campo donador de órganos no debe ser vacío\n");
            }
        } else if (vistaGeneraCertificado.getRdbtnNoMuerteEncefalica().isSelected()) {
            defuncion.setMuerteEncefalica("No");
        } else {
            errores.append("El campo muerte encéfalica no debe ser vacío\n");
        }

        if (isMujer1054) {
            if (vistaGeneraCertificado.getRdbtnMuerteDuranteEmbarazo().isSelected()) {
                defuncion.setMuerteDurante("Embarazo");
            } else if (vistaGeneraCertificado.getRdbtnMuerteDuranteParto().isSelected()) {
                defuncion.setMuerteDurante("Parto");
            } else if (vistaGeneraCertificado.getRdbtnMuerteDurantePuerperio().isSelected()) {
                defuncion.setMuerteDurante("Puerperio");
            } else if (vistaGeneraCertificado.getRdbtnMuerteDespuesPartoAborto().isSelected()) {
                defuncion.setMuerteDurante("43 días a 11 meses después del parto o aborto");
            } else if (vistaGeneraCertificado.getRdbtnNoEstuvoEmbarazadaPreviamente().isSelected()) {
                defuncion.setMuerteDurante("No embarazo últimos 11 meses previos a la defunción");
            } else {
                errores.append("El campo Específique la muerte no debe ser vacío\n");
            }

            if (vistaGeneraCertificado.getRdbtnSiComplicacionesPropias().isSelected()) {
                defuncion.setCausasComplicacionesPropiasEmbarazoPartoPuerperio("Si");
            } else if (vistaGeneraCertificado.getRdbtnNoComplicacionesPropias().isSelected()) {
                defuncion.setCausasComplicacionesPropiasEmbarazoPartoPuerperio("No");
            } else {
                errores.append("Debe seleccionar una opción en las causas de muerte\n");
            }

            if (vistaGeneraCertificado.getRdbtnSiComplicaciones().isSelected()) {
                defuncion.setCausasComplicaronEmbarazoPartoPuerperio("Si");
            } else if (vistaGeneraCertificado.getRdbtnNoComplicaciones().isSelected()) {
                defuncion.setCausasComplicaronEmbarazoPartoPuerperio("No");
            } else {
                errores.append("Debe seleccionar una opción en las causas de muerte\n");
            }
        }

        if (errores.length() > 0) {
            // Mostrar mensaje con los errores encontrados
            JOptionPane.showMessageDialog(
                    null,
                    errores.toString(),
                    "Errores de validación",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            // Avanzar a la siguiente pestaña del formulario si no hay errores
            vistaGeneraCertificado.getTabbedPaneCertificadoDefuncion().setSelectedIndex(12);
        }
    }

    public static void recopilarInformacionVista13() {
        situacion = new Situacion();

        var errores = new StringBuilder();

        if (!vistaGeneraCertificado.getTxtCausaBasicaDefuncion().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtCausaBasicaDefuncion().getText().isBlank()) {
            defuncion.setCausaBasica(vistaGeneraCertificado.getTxtCausaBasicaDefuncion().getText().trim());
        } else {
            errores.append("El campo Causa Básica de Defunción no debe ser vacío\n");
        }
        if (!vistaGeneraCertificado.getTxtCodigoAdicional().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtCodigoAdicional().getText().isBlank()) {
            defuncion.setCodigoCieCausaBasica(vistaGeneraCertificado.getTxtCodigoAdicional().getText().trim());
        } else {
            errores.append("El campo Código Adicional no debe ser vacío\n");
        }

        if (vistaGeneraCertificado.getRdbtnSiOcurrioDesempeñoTrabajo().isSelected()) {
            situacion.setDuranteTrabajo("Si");
        } else if (vistaGeneraCertificado.getRdbtnNoOcurrioDesempeñoTrabajo().isSelected()) {
            situacion.setDuranteTrabajo("No");
        } else if (vistaGeneraCertificado.getRdbtnSeIgnoraOcurrioDesempeñoTrabajo().isSelected()) {
            situacion.setDuranteTrabajo("Se ignora");
        } else {
            errores.append("Seleccione un opcion Ocurrió en el Desempeño Trabajo\n");
        }

        if (vistaGeneraCertificado.getRdbtnViviendaParticular().isSelected()) {
            situacion.setLugarLesion("Vivienda particular");
        } else if (vistaGeneraCertificado.getRdbtnEscuelauOficinaPublica().isSelected()) {
            situacion.setLugarLesion("Escuela u oficina pública");
        } else if (vistaGeneraCertificado.getRdbtnViviendaColectiva().isSelected()) {
            situacion.setLugarLesion("Vivienda Colectiva");
        } else if (vistaGeneraCertificado.getRdbtnAreaDeportiva().isSelected()) {
            situacion.setLugarLesion("Área deportiva");
        } else if (vistaGeneraCertificado.getRdbtnCalleoCarretera().isSelected()) {
            situacion.setLugarLesion("Calle o carretera");
        } else if (vistaGeneraCertificado.getRdbtnAreaComercialoServicio().isSelected()) {
            situacion.setLugarLesion("Área comercial o de servicio");
        } else if (vistaGeneraCertificado.getRdbtnAreaIndustrial().isSelected()) {
            situacion.setLugarLesion("Área industrial");
        } else if (vistaGeneraCertificado.getRdbtnGranja().isSelected()) {
            situacion.setLugarLesion("Granja");
        } else if (vistaGeneraCertificado.getRdbtnOtroLugar().isSelected()) {
            situacion.setLugarLesion("Otro");
        } else if (vistaGeneraCertificado.getRdbtnSeIgnoraLugar().isSelected()) {
            situacion.setLugarLesion("Se ignora");
        } else {
            errores.append("El campo Lugar no debe ser vacío\n");
        }

        if (!vistaGeneraCertificado.getTxtNumeroActa().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtNumeroActa().getText().isBlank()) {
            situacion.setActaNumero(vistaGeneraCertificado.getTxtNumeroActa().getText().trim());
        } else {
            errores.append("El campo Número de Acta no debe ser vacío");
        }

        if (errores.length() > 0) {
            // Mostrar mensaje con los errores encontrados
            JOptionPane.showMessageDialog(
                    null,
                    errores.toString(),
                    "Errores de validación",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            // Avanzar a la siguiente pestaña del formulario si no hay errores
            vistaGeneraCertificado.getTabbedPaneCertificadoDefuncion().setSelectedIndex(13);
        }
    }

    public static void recopilarInformacionVista14() {
        var errores = new StringBuilder();

        if (!vistaGeneraCertificado.getTxtRelacionAgresorFallecido().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtRelacionAgresorFallecido().getText().isBlank()) {
            situacion.setRelacionAgresorFallecido(vistaGeneraCertificado.getTxtRelacionAgresorFallecido().getText().trim());
        } else {
            errores.append("El campo Relación del Agresor no debe ser vacío\n");
        }
        if (!vistaGeneraCertificado.getTxtDescripcionSituacion().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtDescripcionSituacion().getText().isBlank()) {
            situacion.setDescripcionAgresion(vistaGeneraCertificado.getTxtDescripcionSituacion().getText().trim());
        } else {
            errores.append("El campo Descripción no debe ser vacío\n");
        }
        if (vistaGeneraCertificado.getRdbtnInvestigacionPendiente().isSelected()) {
            situacion.setSituacionAgresion("Investigación pendiente");
        } else if (vistaGeneraCertificado.getRdbtnNoPuedeDeterminarse().isSelected()) {
            situacion.setSituacionAgresion("No puede determinarse");
        } else {
            errores.append("El campo de Investigación no puede ser vacío");
        }

        if (errores.length() > 0) {
            // Mostrar mensaje con los errores encontrados
            JOptionPane.showMessageDialog(
                    null,
                    errores.toString(),
                    "Errores de validación",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            // Avanzar a la siguiente pestaña del formulario si no hay errores
            vistaGeneraCertificado.getTabbedPaneCertificadoDefuncion().setSelectedIndex(14);
        }
    }

    public static void recopilarInformacionVista15() {
        var errores = new StringBuilder();

        if (!vistaGeneraCertificado.getTxtTipoVialidadLesion().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtTipoVialidadLesion().getText().isBlank()) {
            situacion.setTipoVialidad(vistaGeneraCertificado.getTxtTipoVialidadLesion().getText().trim());
        } else {
            errores.append("El campo Tipo Vialidad no puede ser vacío\n");
        }
        if (!vistaGeneraCertificado.getTxtNombreVialidadLesion().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtNombreVialidadLesion().getText().isBlank()) {
            situacion.setVialidadNombre(vistaGeneraCertificado.getTxtNombreVialidadLesion().getText().trim());
        } else {
            errores.append("El campo Nombre Vialidad no debe ser vacío\n");
        }
        if (!vistaGeneraCertificado.getTxtNumExtLesion().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtNumExtLesion().getText().isBlank()) {
            situacion.setNumeroExterior(vistaGeneraCertificado.getTxtNumExtLesion().getText().trim());
        } else {
            errores.append("El campo Número Exterior no debe ser vacío\n");
        }
        if (!vistaGeneraCertificado.getTxtNumIntLesion().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtNumIntLesion().getText().isBlank()) {
            situacion.setNumeroInterior(vistaGeneraCertificado.getTxtNumIntLesion().getText().trim());
        } else {
            errores.append("El campo Número Interior no debe ser vacío\n");
        }
        if (!vistaGeneraCertificado.getTxtTipoAsentamientoHumanoLesion().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtTipoAsentamientoHumanoLesion().getText().isBlank()) {
            situacion.setTipoAsentamientoHumano(vistaGeneraCertificado.getTxtTipoAsentamientoHumanoLesion().getText().trim());
        } else {
            errores.append("El campo Tipo Asentamiento Humano no debe ser vacío\n");
        }
        if (!vistaGeneraCertificado.getTxtNombreAsentamientoHumanoLesion().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtNombreAsentamientoHumanoLesion().getText().isBlank()) {
            situacion.setNombreAsentamientoHumano(vistaGeneraCertificado.getTxtNombreAsentamientoHumanoLesion().getText().trim());
        } else {
            errores.append("El campo Nombre Asentamiento Humano no debe ser vacío\n");
        }
        if (!vistaGeneraCertificado.getTxtCodigoPostalLesion().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtCodigoPostalLesion().getText().isBlank()) {
            situacion.setCodigoPostal(vistaGeneraCertificado.getTxtCodigoPostalLesion().getText().trim());
        } else {
            errores.append("El campo Código Postal no debe ser vacío\n");
        }
        if (!vistaGeneraCertificado.getTxtLocalidadLesion().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtLocalidadLesion().getText().isBlank()) {
            situacion.setLocalidad(vistaGeneraCertificado.getTxtLocalidadLesion().getText().trim());
        } else {
            errores.append("El campo Localidad no debe ser vacío\n");
        }
        if (!vistaGeneraCertificado.getTxtMunicipioAlcaldiaLesion().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtMunicipioAlcaldiaLesion().getText().isBlank()) {
            situacion.setMunicipioAlcaldia(vistaGeneraCertificado.getTxtMunicipioAlcaldiaLesion().getText().trim());
        } else {
            errores.append("El campo Municipio o Alcaldía no debe ser vacío\n");
        }
        if (!vistaGeneraCertificado.getTxtEntidadFederativaLesion().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtEntidadFederativaLesion().getText().isBlank()) {
            situacion.setEntidadFederativa(vistaGeneraCertificado.getTxtEntidadFederativaLesion().getText().trim());
        } else {
            errores.append("El campo Entidad Federativa no debe ser vacío");
        }

        if (errores.length() > 0) {
            // Mostrar mensaje con los errores encontrados
            JOptionPane.showMessageDialog(
                    null,
                    errores.toString(),
                    "Errores de validación",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            // Avanzar a la siguiente pestaña del formulario si no hay errores
            vistaGeneraCertificado.getTabbedPaneCertificadoDefuncion().setSelectedIndex(15);
        }
    }

    public static void recopilarInformacionVista16() {
        certificante = new Certificante();
        var errores = new StringBuilder();

        if (vistaGeneraCertificado.getRdbtnMedicoTratante().isSelected()) {
            certificante.setCertificante("Médica/o tratante");
        } else if (vistaGeneraCertificado.getRdbtnMedicoLegista().isSelected()) {
            certificante.setCertificante("Médica/o legista");
        } else if (vistaGeneraCertificado.getRdbtnOtroMedico().isSelected()) {
            certificante.setCertificante("Otra/o médica/o");
        } else if (vistaGeneraCertificado.getRdbtnPersonaAutorizada().isSelected()) {
            certificante.setCertificante("Persona autorizada por la Secretaría de Salud");
        } else if (vistaGeneraCertificado.getRdbtnAutoridadCivil().isSelected()) {
            certificante.setCertificante("Autoridad Civil");
        } else if (vistaGeneraCertificado.getRdbtnOtro().isSelected()) {
            certificante.setCertificante("Otro");
            if (!vistaGeneraCertificado.getTxtEspecifiqueOtraAutoridad1().getText().isEmpty()
                    && !vistaGeneraCertificado.getTxtEspecifiqueOtraAutoridad1().getText().isBlank()) {
                certificante.setEspecifiqueCertificante(vistaGeneraCertificado.getTxtEspecifiqueOtraAutoridad1().getText().trim());
            } else {
                errores.append("El campo Específique no debe ser vacío\n");
            }
        } else {
            errores.append("El campo Certificante no debe ser vacío\n");
        }

        if (vistaGeneraCertificado.getRdbtnMedicoTratante().isSelected()
                || vistaGeneraCertificado.getRdbtnMedicoLegista().isSelected()
                || vistaGeneraCertificado.getRdbtnOtroMedico().isSelected()) {
            if (!vistaGeneraCertificado.getTxtNumCedProfesional().getText().isEmpty()
                    && !vistaGeneraCertificado.getTxtNumCedProfesional().getText().isBlank()) {
                certificante.setNumeroCedulaProfesional(vistaGeneraCertificado.getTxtNumCedProfesional().getText().trim());
            } else {
                errores.append("El campo Número Cédula Profesional no debe ser vacío");
            }
        }

        if (!vistaGeneraCertificado.getTxtNombreCertificante().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtNombreCertificante().getText().isBlank()) {
            certificante.setNombre(vistaGeneraCertificado.getTxtNombreCertificante().getText().trim());
        } else {
            errores.append("El campo Nombre no debe ser vacío\n");
        }
        if (!vistaGeneraCertificado.getTxtApellidoPCertificante().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtApellidoPCertificante().getText().isBlank()) {
            certificante.setApellidoPaterno(vistaGeneraCertificado.getTxtApellidoPCertificante().getText().trim());
        } else {
            errores.append("El campo Apellido Paterno no debe ser vacío\n");
        }
        if (!vistaGeneraCertificado.getTxtApellidoMCertificante().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtApellidoMCertificante().getText().isBlank()) {
            certificante.setApellidoMaterno(vistaGeneraCertificado.getTxtApellidoMCertificante().getText().trim());
        } else {
            errores.append("El campo Apellido Materno no debe ser vacío\n");
        }


        if (errores.length() > 0) {
            // Mostrar mensaje con los errores encontrados
            JOptionPane.showMessageDialog(
                    null,
                    errores.toString(),
                    "Errores de validación",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            // Avanzar a la siguiente pestaña del formulario si no hay errores
            vistaGeneraCertificado.getTabbedPaneCertificadoDefuncion().setSelectedIndex(16);
        }
    }

    public static void recopilarInformacionVista17() {
        var errores = new StringBuilder();

        if (!vistaGeneraCertificado.getTxtTipoVialidadCertificante().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtTipoVialidadCertificante().getText().isBlank()) {
            certificante.setTipoVialidad(vistaGeneraCertificado.getTxtTipoVialidadCertificante().getText().trim());
        } else {
            errores.append("El campo Tipo Vialidad no puede ser vacío\n");
        }
        if (!vistaGeneraCertificado.getTxtNombreVialidadCertificante().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtNombreVialidadCertificante().getText().isBlank()) {
            certificante.setVialidadNombre(vistaGeneraCertificado.getTxtNombreVialidadCertificante().getText().trim());
        } else {
            errores.append("El campo Nombre Vialidad no debe ser vacío\n");
        }
        if (!vistaGeneraCertificado.getTxtNumExtCertificante().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtNumExtCertificante().getText().isBlank()) {
            certificante.setNumeroExterior(vistaGeneraCertificado.getTxtNumExtCertificante().getText().trim());
        } else {
            errores.append("El campo Número Exterior no debe ser vacío\n");
        }
        if (!vistaGeneraCertificado.getTxtNumIntCertificante().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtNumIntCertificante().getText().isBlank()) {
            certificante.setNumeroInterior(vistaGeneraCertificado.getTxtNumIntCertificante().getText().trim());
        } else {
            errores.append("El campo Número Interior no debe ser vacío\n");
        }
        if (!vistaGeneraCertificado.getTxtTipoAsentamientoHumanoCertificante().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtTipoAsentamientoHumanoCertificante().getText().isBlank()) {
            certificante.setTipoAsentamientoHumano(vistaGeneraCertificado.getTxtTipoAsentamientoHumanoCertificante().getText().trim());
        } else {
            errores.append("El campo Tipo Asentamiento Humano no debe ser vacío\n");
        }
        if (!vistaGeneraCertificado.getTxtNombreAsentamientoHumanoCertificante().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtNombreAsentamientoHumanoCertificante().getText().isBlank()) {
            certificante.setNombreAsentamientoHumano(vistaGeneraCertificado.getTxtNombreAsentamientoHumanoCertificante().getText().trim());
        } else {
            errores.append("El campo Nombre Asentamiento Humano no debe ser vacío\n");
        }
        if (!vistaGeneraCertificado.getTxtCodigoPostalCertificante().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtCodigoPostalCertificante().getText().isBlank()) {
            certificante.setCodigoPostal(vistaGeneraCertificado.getTxtCodigoPostalCertificante().getText().trim());
        } else {
            errores.append("El campo Código Postal no debe ser vacío\n");
        }
        if (!vistaGeneraCertificado.getTxtLocalidadCertificante().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtLocalidadCertificante().getText().isBlank()) {
            certificante.setLocalidad(vistaGeneraCertificado.getTxtLocalidadCertificante().getText().trim());
        } else {
            errores.append("El campo Localidad no debe ser vacío\n");
        }
        if (!vistaGeneraCertificado.getTxtMunicipioAlcaldiaCertificante().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtMunicipioAlcaldiaCertificante().getText().isBlank()) {
            certificante.setMunicipioAlcaldia(vistaGeneraCertificado.getTxtMunicipioAlcaldiaCertificante().getText().trim());
        } else {
            errores.append("El campo Municipio o Alcaldía no debe ser vacío\n");
        }
        if (!vistaGeneraCertificado.getTxtEntidadFederativaCertificante().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtEntidadFederativaCertificante().getText().isBlank()) {
            certificante.setEntidadFederativa(vistaGeneraCertificado.getTxtEntidadFederativaCertificante().getText().trim());
        } else {
            errores.append("El campo Entidad Federativa no debe ser vacío");
        }
        if (!vistaGeneraCertificado.getTxtTelefonoCertificante().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtTelefonoCertificante().getText().isBlank()) {
            certificante.setTelefono(vistaGeneraCertificado.getTxtTelefonoCertificante().getText().trim());
        } else {
            errores.append("El campo Teléfono no debe ser vacío");
        }

        if (errores.length() > 0) {
            // Mostrar mensaje con los errores encontrados
            JOptionPane.showMessageDialog(
                    null,
                    errores.toString(),
                    "Errores de validación",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            // Avanzar a la siguiente pestaña del formulario si no hay errores
            vistaGeneraCertificado.getTabbedPaneCertificadoDefuncion().setSelectedIndex(17);
        }
    }

    public static void recopilarInformacionVista18() {
        registroCivil = new RegistroCivil();
        var errores = new StringBuilder();

        if ((!vistaGeneraCertificado.getTxtFechaCertificacionDia().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtFechaCertificacionDia().getText().isBlank())
                && (!vistaGeneraCertificado.getTxtFechaCertificacionMes().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtFechaCertificacionMes().getText().isBlank())
                && (!vistaGeneraCertificado.getTxtFechaCertificacionAño().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtFechaCertificacionAño().getText().isBlank())) {
            //            Validar que los datos ingresados sean numericos y esten en el formato correcto
            try {
                String diaText = vistaGeneraCertificado.getTxtFechaCertificacionDia().getText().trim();
                String mesText = vistaGeneraCertificado.getTxtFechaCertificacionMes().getText().trim();
                String anioText = vistaGeneraCertificado.getTxtFechaCertificacionAño().getText().trim();
                int dia = Integer.parseInt(diaText);
                int mes = Integer.parseInt(mesText);
                int anio = Integer.parseInt(anioText);

                String fechaCertificacion = diaText + "/" + mesText + "/" + anioText;

//                Condición que evalua los valores de día, mes y año
                if (fechaCertificacion.matches("^\\d{2}/\\d{2}/\\d{4}$")) {
                    java.time.LocalDate.of(anio, mes, dia);
                    fallecido.setDiaCertificacion(dia);
                    fallecido.setMesCertificacion(mes);
                    fallecido.setAnioCertificacion(anio);
                } else {
                    errores.append("El formato de la fecha debe ser dd/mm/aaaa\n");
                }


            } catch (NumberFormatException e) {
                errores.append("Los datos ingresados en la fecha de defunción deben ser numéricos\n");
            } catch (java.time.DateTimeException e) {
                errores.append("La fecha de defunción ingresada no es válida\n");
            }
        } else {
            errores.append("El campo Fecha Certificación no debe ser vacío\n");
        }

        if (!vistaGeneraCertificado.getTxtNumFiscalia().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtNumFiscalia().getText().isBlank()) {
            registroCivil.setNumero(vistaGeneraCertificado.getTxtNumFiscalia().getText().trim());
        } else {
            errores.append("El campo Número Fiscalía no debe ser vacío\n");
        }
        if (!vistaGeneraCertificado.getTxtNumLibroFiscalia().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtNumLibroFiscalia().getText().isBlank()) {
            registroCivil.setNumeroLibro(vistaGeneraCertificado.getTxtNumLibroFiscalia().getText().trim());
        } else {
            errores.append("El campo Número Libro Fiscalía no debe ser vacío\n");
        }
        if (!vistaGeneraCertificado.getTxtNumActaFiscalia().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtNumActaFiscalia().getText().isBlank()) {
            registroCivil.setNumeroActa(vistaGeneraCertificado.getTxtNumActaFiscalia().getText().trim());
        } else {
            errores.append("El campo Número Acta Fiscalía no debe ser vacío\n");
        }

        if (!vistaGeneraCertificado.getTxtLocalidadRegistro().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtLocalidadRegistro().getText().isBlank()) {
            registroCivil.setLocalidad(vistaGeneraCertificado.getTxtLocalidadRegistro().getText().trim());
        } else {
            errores.append("El campo Localidad no debe ser vacío\n");
        }
        if (!vistaGeneraCertificado.getTxtMunicipioRegistro().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtMunicipioRegistro().getText().isBlank()) {
            registroCivil.setMunicipioAlcaldia(vistaGeneraCertificado.getTxtMunicipioRegistro().getText().trim());
        } else {
            errores.append("El campo Municipio o Alcaldía no debe ser vacío\n");
        }
        if (!vistaGeneraCertificado.getTxtEntidadFederativaRegistro().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtEntidadFederativaRegistro().getText().isBlank()) {
            registroCivil.setEntidadFederativa(vistaGeneraCertificado.getTxtEntidadFederativaRegistro().getText().trim());
        } else {
            errores.append("El campo Entidad Federativa no debe ser vacío\n");
        }

        if ((!vistaGeneraCertificado.getTxtDiaRegistro().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtDiaRegistro().getText().isBlank())
                && (!vistaGeneraCertificado.getTxtMesRegistro().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtMesRegistro().getText().isBlank())
                && (!vistaGeneraCertificado.getTxtAñoRegistro().getText().isEmpty()
                && !vistaGeneraCertificado.getTxtAñoRegistro().getText().isBlank())) {
            try {
                String diaText = vistaGeneraCertificado.getTxtDiaRegistro().getText().trim();
                String mesText = vistaGeneraCertificado.getTxtMesRegistro().getText().trim();
                String anioText = vistaGeneraCertificado.getTxtAñoRegistro().getText().trim();
                int dia = Integer.parseInt(diaText);
                int mes = Integer.parseInt(mesText);
                int anio = Integer.parseInt(anioText);

                String fechaRegistro = diaText + "/" + mesText + "/" + anioText;

//                Condición que evalua los valores de día, mes y año
                if (fechaRegistro.matches("^\\d{2}/\\d{2}/\\d{4}$")) {
                    java.time.LocalDate.of(anio, mes, dia);
                    registroCivil.setDiaRegistro(dia);
                    registroCivil.setMesRegistro(mes);
                    registroCivil.setAnioRegistro(anio);
                } else {
                    errores.append("El formato de la fecha debe ser dd/mm/aaaa\n");
                }
            } catch (NumberFormatException e) {
                errores.append("Los datos ingresados en la fecha de defunción deben ser numéricos\n");
            } catch (java.time.DateTimeException e) {
                errores.append("La fecha de defunción ingresada no es válida\n");
            }
        } else {
            errores.append("El campo Fecha Registro no debe ser vacío\n");
        }

        if (errores.length() > 0) {
            // Mostrar mensaje con los errores encontrados
            JOptionPane.showMessageDialog(
                    null,
                    errores.toString(),
                    "Errores de validación",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            // Avanzar a la siguiente pestaña del formulario si no hay errores
            //generarActa();
        }
    }

    public static void deshabilitarHabilitarCamposEdadCumplida() {
        // Verifica y habilita o deshabilita los campos según la selección de la edad cumplida
        try {
            if (vistaGeneraCertificado.getRdbtnSeIgnoraEdad().isSelected()) {
                // Si la edad es "Se ignora", deshabilita todos los campos relacionados
                vistaGeneraCertificado.getTxtFolioCertificadoNacimiento().setEditable(false);
                vistaGeneraCertificado.getTxtFolioCertificadoNacimiento().setEnabled(false);
                vistaGeneraCertificado.getTxtSemanasGestacion().setEditable(false);
                vistaGeneraCertificado.getTxtSemanasGestacion().setEnabled(false);
                vistaGeneraCertificado.getTxtPesoAlNacer().setEditable(false);
                vistaGeneraCertificado.getTxtPesoAlNacer().setEnabled(false);
                vistaGeneraCertificado.getTxtEdadTiempo().setEditable(false);
                vistaGeneraCertificado.getTxtEdadTiempo().setEnabled(false);
                vistaGeneraCertificado.getjComboBoxTiempo().setEnabled(false);
            } else {
                vistaGeneraCertificado.getTxtEdadTiempo().setEditable(true);
                vistaGeneraCertificado.getTxtEdadTiempo().setEnabled(true);
                vistaGeneraCertificado.getjComboBoxTiempo().setEnabled(true);
                if ((vistaGeneraCertificado.getjComboBoxTiempo().getSelectedItem().equals("Días")
                        && Integer.parseInt(vistaGeneraCertificado.getTxtEdadTiempo().getText()) < 28)
                        || vistaGeneraCertificado.getjComboBoxTiempo().getSelectedItem().equals("Minutos")
                        || vistaGeneraCertificado.getjComboBoxTiempo().getSelectedItem().equals("Horas")) {

                    // Si la edad está en días, minutos u horas y es menor a 28 días, habilita todos los campos relacionados
                    vistaGeneraCertificado.getTxtFolioCertificadoNacimiento().setEditable(true);
                    vistaGeneraCertificado.getTxtFolioCertificadoNacimiento().setEnabled(true);
                    vistaGeneraCertificado.getTxtSemanasGestacion().setEditable(true);
                    vistaGeneraCertificado.getTxtSemanasGestacion().setEnabled(true);
                    vistaGeneraCertificado.getTxtPesoAlNacer().setEditable(true);
                    vistaGeneraCertificado.getTxtPesoAlNacer().setEnabled(true);
                } else {
                    // Para todos los demás casos, deshabilita los campos relacionados
                    vistaGeneraCertificado.getTxtFolioCertificadoNacimiento().setEditable(false);
                    vistaGeneraCertificado.getTxtFolioCertificadoNacimiento().setEnabled(false);
                    vistaGeneraCertificado.getTxtSemanasGestacion().setEditable(false);
                    vistaGeneraCertificado.getTxtSemanasGestacion().setEnabled(false);
                    vistaGeneraCertificado.getTxtPesoAlNacer().setEditable(false);
                    vistaGeneraCertificado.getTxtPesoAlNacer().setEnabled(false);
                }
            }
        } catch (NumberFormatException e) {
            // Captura y maneja errores de formato numérico si se ingresa un valor inválido
        }
    }

    public static void deshabilitarHabilitarTextFieldNombre() {
        // Habilita o deshabilita los campos de texto del nombre según si la persona es desconocida
        if (vistaGeneraCertificado.getRdbtnPersonaDesconocida().isSelected()) {
            // Si la persona es desconocida, deshabilita los campos de nombre y apellidos
            vistaGeneraCertificado.getTxtNombre().setEnabled(false);
            vistaGeneraCertificado.getTxtApellidoPaterno().setEnabled(false);
            vistaGeneraCertificado.getTxtApellidoMaterno().setEnabled(false);
        } else {
            // Si la persona no es desconocida, habilita los campos de nombre y apellidos
            vistaGeneraCertificado.getTxtNombre().setEnabled(true);
            vistaGeneraCertificado.getTxtApellidoPaterno().setEnabled(true);
            vistaGeneraCertificado.getTxtApellidoMaterno().setEnabled(true);
        }
    }

    public static void deshabilitarHabilitarOcupacionDescripcion() {
        // Habilita o deshabilita el campo de descripción de ocupación habitual según la selección
        if (vistaGeneraCertificado.getRdbtnOcupacionHabitualSeIgnora().isSelected()) {
            // Si se ignora la ocupación habitual, deshabilita y limpia el campo
            vistaGeneraCertificado.getTxtOcupacionHabitual().setEnabled(false);
            vistaGeneraCertificado.getTxtOcupacionHabitual().setEditable(false);
            vistaGeneraCertificado.getTxtOcupacionHabitual().setText("");
        } else if (!vistaGeneraCertificado.getRdbtnOcupacionHabitualSeIgnora().isSelected()) {
            // Si no se ignora, habilita el campo
            vistaGeneraCertificado.getTxtOcupacionHabitual().setEnabled(true);
            vistaGeneraCertificado.getTxtOcupacionHabitual().setEditable(true);
        }
    }

    public static void deshabilitarHabilitarTipoEscolaridad() {
        // Habilita o deshabilita las opciones de tipo de escolaridad según la selección de nivel educativo
        if (vistaGeneraCertificado.getRdbtnEscolaridadSeIgnora().isSelected()
                || vistaGeneraCertificado.getRdbtnEscolaridadNinguna().isSelected()) {
            // Si se ignora la escolaridad o no tiene escolaridad, deshabilita las opciones
            vistaGeneraCertificado.getRdbtnEscolaridadCompleta().setEnabled(false);
            vistaGeneraCertificado.getRdbtnEscolaridadIncompleta().setEnabled(false);
        } else {
            // Si tiene algún nivel educativo, habilita las opciones
            vistaGeneraCertificado.getRdbtnEscolaridadCompleta().setEnabled(true);
            vistaGeneraCertificado.getRdbtnEscolaridadIncompleta().setEnabled(true);
        }
    }

    public static void deshabilitarHabilitarCamposServicioSalud() {
        // Habilita o deshabilita los campos relacionados con servicios de salud según la selección
        if (vistaGeneraCertificado.getRdbtnServiciosDeSaludSeIgnora().isSelected()
                || vistaGeneraCertificado.getRdbtnServiciosDeSaludNinguna().isSelected()) {
            // Si se ignoran o no tienen servicios de salud, deshabilita los campos relacionados
            vistaGeneraCertificado.getTxtNumeroSeguridadSocial().setEditable(false);
            vistaGeneraCertificado.getTxtNumeroSeguridadSocial().setEnabled(false);
            vistaGeneraCertificado.getTxtServiciosDeSaludEspecifique().setEnabled(false);
            vistaGeneraCertificado.getTxtServiciosDeSaludEspecifique().setEditable(false);
        } else if (vistaGeneraCertificado.getRdbtnServiciosDeSaludOtra().isSelected()) {
            // Si el servicio es "Otro", habilita los campos para especificar
            vistaGeneraCertificado.getTxtServiciosDeSaludEspecifique().setEnabled(true);
            vistaGeneraCertificado.getTxtServiciosDeSaludEspecifique().setEditable(true);
            vistaGeneraCertificado.getTxtNumeroSeguridadSocial().setEditable(true);
            vistaGeneraCertificado.getTxtNumeroSeguridadSocial().setEnabled(true);
        } else {
            // En los demás casos, habilita solo el número de seguridad social
            vistaGeneraCertificado.getTxtServiciosDeSaludEspecifique().setEnabled(false);
            vistaGeneraCertificado.getTxtServiciosDeSaludEspecifique().setEditable(false);
            vistaGeneraCertificado.getTxtNumeroSeguridadSocial().setEditable(true);
            vistaGeneraCertificado.getTxtNumeroSeguridadSocial().setEnabled(true);
        }
    }

    public static void deshabilitarHabilitarEspecifiqueLenguaIndigena() {
        if (vistaGeneraCertificado.getRdbtnSiLenguaIndigena().isSelected()) {
            vistaGeneraCertificado.getTxtEspecifiqueLenguaIndigena().setEnabled(true);
            vistaGeneraCertificado.getTxtEspecifiqueLenguaIndigena().setEditable(true);
        } else {
            vistaGeneraCertificado.getTxtEspecifiqueLenguaIndigena().setEnabled(false);
            vistaGeneraCertificado.getTxtEspecifiqueLenguaIndigena().setEditable(false);
            vistaGeneraCertificado.getTxtEspecifiqueLenguaIndigena().setText("");
        }
    }

    public static void deshabilitarHabilitarEspecifiqueNacionalidad() {
        if (vistaGeneraCertificado.getRdbtnOtraNacionalidad().isSelected()) {
            vistaGeneraCertificado.getTxtEspecifiqueNacionalidad().setEnabled(true);
            vistaGeneraCertificado.getTxtEspecifiqueNacionalidad().setEditable(true);
        } else {
            vistaGeneraCertificado.getTxtEspecifiqueNacionalidad().setEnabled(false);
            vistaGeneraCertificado.getTxtEspecifiqueNacionalidad().setEditable(false);
            vistaGeneraCertificado.getTxtEspecifiqueNacionalidad().setText("");
        }
    }

    public static void deshabilitarHabilitarCamposSitioDefuncion() {
        if (vistaGeneraCertificado.getRdbtnDefuncionViaPublica().isSelected()
                || vistaGeneraCertificado.getRdbtnDefuncionHogar().isSelected()
                || vistaGeneraCertificado.getRdbtnDefuncionOtroLugar().isSelected()
                || vistaGeneraCertificado.getRdbtnDefuncionSeIgnora().isSelected()) {

            vistaGeneraCertificado.getTxtDefuncionNombreUnidadMedica().setEnabled(false);
            vistaGeneraCertificado.getTxtDefuncionNombreUnidadMedica().setEditable(false);
            vistaGeneraCertificado.getTxtDefuncionClaveUnicaEstablecimientos().setEnabled(false);
            vistaGeneraCertificado.getTxtDefuncionClaveUnicaEstablecimientos().setEditable(false);
        } else {
            vistaGeneraCertificado.getTxtDefuncionNombreUnidadMedica().setEnabled(true);
            vistaGeneraCertificado.getTxtDefuncionNombreUnidadMedica().setEditable(true);
            vistaGeneraCertificado.getTxtDefuncionClaveUnicaEstablecimientos().setEnabled(true);
            vistaGeneraCertificado.getTxtDefuncionClaveUnicaEstablecimientos().setEditable(true);
        }
    }

    public static void deshabilitarHabilitarCamposCirugia() {
        if (vistaGeneraCertificado.getRdbtnSiRealizoCirugia().isSelected()) {
            vistaGeneraCertificado.getTxtDiaRealizoCirugia().setEnabled(true);
            vistaGeneraCertificado.getTxtDiaRealizoCirugia().setEditable(true);
            vistaGeneraCertificado.getTxtMesRealizoCirugia().setEnabled(true);
            vistaGeneraCertificado.getTxtMesRealizoCirugia().setEditable(true);
            vistaGeneraCertificado.getTxtAñoRealizoCirugia().setEnabled(true);
            vistaGeneraCertificado.getTxtAñoRealizoCirugia().setEditable(true);
            vistaGeneraCertificado.getTxtMotivoCirugia().setEnabled(true);
            vistaGeneraCertificado.getTxtMotivoCirugia().setEditable(true);
        } else if (vistaGeneraCertificado.getRdbtnNoRealizoCirugia().isSelected()
                || vistaGeneraCertificado.getRdbtnSeIgnoraRealizoCirugia().isSelected()) {
            vistaGeneraCertificado.getTxtDiaRealizoCirugia().setEnabled(false);
            vistaGeneraCertificado.getTxtDiaRealizoCirugia().setEditable(false);
            vistaGeneraCertificado.getTxtMesRealizoCirugia().setEnabled(false);
            vistaGeneraCertificado.getTxtMesRealizoCirugia().setEditable(false);
            vistaGeneraCertificado.getTxtAñoRealizoCirugia().setEnabled(false);
            vistaGeneraCertificado.getTxtAñoRealizoCirugia().setEditable(false);
            vistaGeneraCertificado.getTxtMotivoCirugia().setEnabled(false);
            vistaGeneraCertificado.getTxtMotivoCirugia().setEditable(false);
        }
    }

    public static void deshabilitarHabilitarCamposNecropsia() {
        if (vistaGeneraCertificado.getRdbtnSiPracticoNecropsia().isSelected()) {
            vistaGeneraCertificado.getRdbtnSiUtilizaronHallazgos().setEnabled(true);
            vistaGeneraCertificado.getRdbtnNoUtilizaronHallazgos().setEnabled(true);
        } else if (vistaGeneraCertificado.getRdbtnNoPracticoNecropsia().isSelected()
                || vistaGeneraCertificado.getRdbtnSeIgnoraPracticoNecropsia().isSelected()) {
            vistaGeneraCertificado.getRdbtnSiUtilizaronHallazgos().setEnabled(false);
            vistaGeneraCertificado.getRdbtnNoUtilizaronHallazgos().setEnabled(false);
        }
    }

    public static void deshabilitarHabilitarCamposEncefalica() {
        if (vistaGeneraCertificado.getRdbtnSiMuerteEncefalica().isSelected()) {
            vistaGeneraCertificado.getRdbtnSiDonadorOrganos().setEnabled(true);
            vistaGeneraCertificado.getRdbtnNoDonadorOrganos().setEnabled(true);
        } else if (vistaGeneraCertificado.getRdbtnNoMuerteEncefalica().isSelected()) {
            vistaGeneraCertificado.getRdbtnSiDonadorOrganos().setEnabled(false);
            vistaGeneraCertificado.getRdbtnNoDonadorOrganos().setEnabled(false);
        }
    }

    /**
     * Calculates the age based on the given day, month, and year.
     *
     * @param day   The day of birth.
     * @param month The month of birth.
     * @param year  The year of birth.
     * @return The calculated age as an integer.
     * @throws IllegalArgumentException If the birth date is invalid or in the future.
     */
    public static int calcularEdad(int day, int month, int year) {
        // Create a LocalDate object from the provided day, month, and year
        LocalDate birthDate = LocalDate.of(year, month, day);

        // Validate the input
        if (birthDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Birth date cannot be in the future.");
        }

        // Calculate the period (difference) between the birthdate and today
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public static void habilitarCamposMujer() {
        vistaGeneraCertificado.getRdbtnMuerteDuranteEmbarazo().setEnabled(true);
        vistaGeneraCertificado.getRdbtnMuerteDuranteParto().setEnabled(true);
        vistaGeneraCertificado.getRdbtnMuerteDurantePuerperio().setEnabled(true);
        vistaGeneraCertificado.getRdbtnMuerteDespuesPartoAborto().setEnabled(true);
        vistaGeneraCertificado.getRdbtnNoEstuvoEmbarazadaPreviamente().setEnabled(true);
        vistaGeneraCertificado.getRdbtnSiComplicacionesPropias().setEnabled(true);
        vistaGeneraCertificado.getRdbtnNoComplicacionesPropias().setEnabled(true);
        vistaGeneraCertificado.getRdbtnSiComplicaciones().setEnabled(true);
        vistaGeneraCertificado.getRdbtnNoComplicaciones().setEnabled(true);
    }

    public static void deshabilitarHabilitarCedulaMedico() {
        if (vistaGeneraCertificado.getRdbtnMedicoTratante().isSelected()
                || vistaGeneraCertificado.getRdbtnMedicoLegista().isSelected()
                || vistaGeneraCertificado.getRdbtnOtroMedico().isSelected()) {
            vistaGeneraCertificado.getTxtNumCedProfesional().setEnabled(true);
            vistaGeneraCertificado.getTxtNumCedProfesional().setEditable(true);
        } else {
            vistaGeneraCertificado.getTxtNumCedProfesional().setEnabled(false);
            vistaGeneraCertificado.getTxtNumCedProfesional().setEditable(false);
        }
    }

    public static void deshabilitarHabilitarEspecifiqueCertificante() {
        if (vistaGeneraCertificado.getRdbtnOtro().isSelected()
                || vistaGeneraCertificado.getRdbtnOtroMedico().isSelected()) {
            vistaGeneraCertificado.getTxtEspecifiqueOtraAutoridad1().setEnabled(true);
            vistaGeneraCertificado.getTxtEspecifiqueOtraAutoridad1().setEditable(true);
        } else {
            vistaGeneraCertificado.getTxtEspecifiqueOtraAutoridad1().setEnabled(false);
            vistaGeneraCertificado.getTxtEspecifiqueOtraAutoridad1().setEditable(false);
        }
    }
}
