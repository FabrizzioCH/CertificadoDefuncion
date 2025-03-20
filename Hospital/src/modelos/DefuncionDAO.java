package modelos;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DefuncionDAO {
    Connection conexion;
    String query;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    public DefuncionDAO() {
        conexion = new MySQLConexion().getConnection();
    }

    /// # METHODS PARA INSERTAR DATOS EN LA BASE DE DATOS

    //    Method para insertar datos en la tabla defuncion
    public boolean registrarDefuncion(Defuncion defuncion, int idFallecido) {
        final String INSERT_QUERY = "INSERT INTO defuncion(id_fallecido, sitio, fecha, atencion_medica, " +
                "accidental_violenta, defuncion_tipo) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(INSERT_QUERY)) {
            // Insertar parametros
            preparedStatement.setInt(1, idFallecido);
            setPreparedStatementValor(preparedStatement, 2, defuncion.getSitio());
            preparedStatement.setTimestamp(3, createSqlTimestamp(defuncion));
            setPreparedStatementValor(preparedStatement, 4, defuncion.getAtencionMedica());
            setPreparedStatementValor(preparedStatement, 5, defuncion.getAccidentalViolenta());
            setPreparedStatementValor(preparedStatement, 6, defuncion.getDefuncionTipo());

            // Ejecutar query retornar el resultado
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al registrar en defuncion: " + e.getMessage());
        } finally {
            // Cerrar la conexion
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException e) {
                    System.err.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
        return false;
    }

    //    Method para insertar datos en la tabla defuncion_unidad_medica
    public boolean registrarDefuncionUnidadMedica(Defuncion defuncion, int idDefuncion) {
        final String INSERT_QUERY = "INSERT INTO defuncion_unidad_medica(id_defuncion, unidad_medica, " +
                "nombre_unidad_medica, clues) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(INSERT_QUERY)) {
//            Insertar parametros
            preparedStatement.setInt(1, idDefuncion);
            setPreparedStatementValor(preparedStatement, 2, defuncion.getUnidadMedica());
            setPreparedStatementValor(preparedStatement, 3, defuncion.getNombreUnidadMedica());
            setPreparedStatementValor(preparedStatement, 4, defuncion.getClues());

            // Ejecutar query retornar el resultado
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al registrar en defuncion_unidad_medica: " + e.getMessage());
        } finally {
            // Cerrar la conexion
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException e) {
                    System.err.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
        return false;
    }

    //    Method para insertar datos en la tabla defuncion_mujer_10_54_anios
    public boolean registrarDefuncionMujer1054Anios(Defuncion defuncion, int idDefuncion) {
        final String INSERT_QUERY = "INSERT INTO defuncion_mujer_10_54_anios(id_defuncion, muerte_durante, " +
                "causas_complicaciones_propias_embarazo_parto_puerperio, causas_complicaron_embarazo_parto_puerperio) " +
                "VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setInt(1, idDefuncion);
            setPreparedStatementValor(preparedStatement, 2, defuncion.getMuerteDurante());
            setPreparedStatementValor(preparedStatement, 3, defuncion.getCausasComplicacionesPropiasEmbarazoPartoPuerperio());
            setPreparedStatementValor(preparedStatement, 4, defuncion.getCausasComplicaronEmbarazoPartoPuerperio());

            // Ejecutar query retornar el resultado
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al registrar en defuncion_mujer_10_54_anios: " + e.getMessage());
        } finally {
            // Cerrar la conexion
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException e) {
                    System.err.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
        return false;
    }

    // Method para insertar datos en la tabla defuncion_encefalica
    public boolean registrarDefuncionEncefalica(Defuncion defuncion, int idDefuncion) {
        final String INSERT_QUERY = "INSERT INTO defuncion_encefalica(id_defuncion, muerte_encefalica, donador_organos) " +
                "VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setInt(1, idDefuncion);
            setPreparedStatementValor(preparedStatement, 2, defuncion.getMuerteEncefalica());
            setPreparedStatementValor(preparedStatement, 3, defuncion.getDonadorOrganos());

            // Ejecutar query y retornar resultado
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al registrar en defuncion_encefalica: " + e.getMessage());
        } finally {
            // Cerrar la conexion
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException e) {
                    System.err.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
        return false;
    }

    // Method para insertar datos en la tabla defuncion_domiclio
    public boolean registrarDefuncionDomicilio(Defuncion defuncion, int idDefuncion) {
        final String INSERT_QUERY = "INSERT INTO defuncion_domiclio(id_defuncion, tipo_vialidad, vialidad_nombre, " +
                "numero_exterior, numero_interior, tipo_asentamiento_humano, nombre_asentamiento_humano, codigo_postal, " +
                "localidad, municipio_alcaldia, entidad_federativa) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setInt(1, idDefuncion);
            setPreparedStatementValor(preparedStatement, 2, defuncion.getTipoVialidad());
            setPreparedStatementValor(preparedStatement, 3, defuncion.getVialidadNombre());
            setPreparedStatementValor(preparedStatement, 4, defuncion.getNumeroExterior());
            setPreparedStatementValor(preparedStatement, 5, defuncion.getNumeroInterior());
            setPreparedStatementValor(preparedStatement, 6, defuncion.getTipoAsentamientoHumano());
            setPreparedStatementValor(preparedStatement, 7, defuncion.getNombreAsentamientoHumano());
            setPreparedStatementValor(preparedStatement, 8, defuncion.getCodigoPostal());
            setPreparedStatementValor(preparedStatement, 9, defuncion.getLocalidad());
            setPreparedStatementValor(preparedStatement, 10, defuncion.getMunicipioAlcaldia());
            setPreparedStatementValor(preparedStatement, 11, defuncion.getEntidadFederativa());

            // Ejecutar query y retornar resultado
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al registrar en defuncion_domiclio: " + e.getMessage());
        } finally {
            // Cerrar la conexion
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException e) {
                    System.err.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
        return false;
    }

    // Method para insertar datos en la tabla defuncion_cirugia
    public boolean registrarDefuncionCirugia(Defuncion defuncion, int idDefuncion) {
        final String INSERT_QUERY = "INSERT INTO defuncion_cirugia(id_defuncion, cirugia_ultimas_4_semanas, " +
                "cirugia_fecha, cirugia_motivo) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setInt(1, idDefuncion);
            setPreparedStatementValor(preparedStatement, 2, defuncion.getCirugiaUltimas4Semanas());
            preparedStatement.setDate(3, createSqlDate(defuncion));
            setPreparedStatementValor(preparedStatement, 4, defuncion.getCirugiaMotivo());

            // Ejecutar query y retornar resultado
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al registrar en defuncion_cirugia: " + e.getMessage());
        } finally {
            // Cerrar la conexion
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException e) {
                    System.err.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
        return false;
    }

    // Method para insertar datos en la tabla defuncion_necropsia
    public boolean registrarDefuncionNecropsia(Defuncion defuncion, int idDefuncion) {
        final String INSERT_QUERY = "INSERT INTO defuncion_necropsia(id_defuncion, necropsia, hallazgos_certificacion) " +
                "VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setInt(1, idDefuncion);
            setPreparedStatementValor(preparedStatement, 2, defuncion.getNecropsia());
            setPreparedStatementValor(preparedStatement, 3, defuncion.getHallazgosCertificacion());

            // Ejecutar query y retornar resultado
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al registrar en defuncion_necropsia: " + e.getMessage());
        } finally {
            // Cerrar la conexion
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException e) {
                    System.err.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
        return false;
    }

    // Method para insertar datos en la tabla defuncion_causas
    public boolean registrarDefuncionCausas(String causa, String intervaloTiempo, String codigoCie, int idDefuncion) {
        final String INSERT_QUERY = "INSERT INTO defuncion_causas(id_defuncion, causa, intervalo_tiempo, codigo_cie) " +
                "VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setInt(1, idDefuncion);
            setPreparedStatementValor(preparedStatement, 2, causa);
            setPreparedStatementValor(preparedStatement, 3, intervaloTiempo);
            setPreparedStatementValor(preparedStatement, 4, codigoCie);

            // Ejecutar query y retornar resultado
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al registrar en defuncion_causas: " + e.getMessage());
        } finally {
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException e) {
                    System.err.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
        return false;
    }

    // Method para insertar datos en la tabla defuncion_causa_basica
    public boolean registrarDefuncionCausaBasica(Defuncion defuncion, int idDefuncion) {
        final String INSERT_QUERY = "INSERT INTO defuncion_causa_basica(id_defuncion, causa_basica, codigo_cie) " +
                "VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setInt(1, idDefuncion);
            setPreparedStatementValor(preparedStatement, 2, defuncion.getCausaBasica());
            setPreparedStatementValor(preparedStatement, 3, defuncion.getCodigoCieCausaBasica());

            // Ejecutar query y retornar resultado
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al registrar en defuncion_causa_basica: " + e.getMessage());
        } finally {
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException e) {
                    System.err.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
        return false;
    }

    /// # METHODS PARA OBTENER DATOS DE LA BASE DE DATOS

    // Method para obtener los datos de la tabla defuncion por id_fallecido
    public Defuncion getDefuncionByIdFallecido(int idFallecido) {
        Defuncion defuncion;
        final String SELECT_QUERY = "SELECT sitio, fecha, atencion_medica, accidental_violenta, defuncion_tipo " +
                "FROM defuncion WHERE id_fallecido = ?";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(SELECT_QUERY)) {
            preparedStatement.setInt(1, idFallecido);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                defuncion = new Defuncion();
                defuncion.setSitio(resultSet.getString("sitio"));
                setFechaDefuncion(defuncion, resultSet);
                defuncion.setAtencionMedica(resultSet.getString("atencion_medica"));
                defuncion.setAccidentalViolenta(resultSet.getString("accidental_violenta"));
                defuncion.setDefuncionTipo(resultSet.getString("defuncion_tipo"));
                return defuncion;
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener defuncion por id_fallecido: " + e.getMessage());
        } finally {
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException e) {
                    System.err.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
        return null;
    }

    // Method para obtener los datos de la tabla defuncion_unidad_medica por id_defuncion
    public Defuncion getDefuncionUnidadMedicaByIdDefuncion(int idDefuncion) {
        Defuncion defuncion;
        final String SELECT_QUERY = "SELECT unidad_medica, nombre_unidad_medica, clues " +
                "FROM defuncion_unidad_medica WHERE id_defuncion = ?";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(SELECT_QUERY)) {
            preparedStatement.setInt(1, idDefuncion);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                defuncion = new Defuncion();
                defuncion.setUnidadMedica(resultSet.getString("unidad_medica"));
                defuncion.setNombreUnidadMedica(resultSet.getString("nombre_unidad_medica"));
                defuncion.setClues(resultSet.getString("clues"));
                return defuncion;
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener defuncion_unidad_medica por id_defuncion: " + e.getMessage());
        } finally {
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException e) {
                    System.err.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
        return null;
    }

    // Method para obtener los datos de la tabla defuncion_mujer_10_54_anios por id_defuncion
    public Defuncion getDefuncionMujer1054AniosByIdDefuncion(int idDefuncion) {
        Defuncion defuncion;
        final String SELECT_QUERY = "SELECT muerte_durante, causas_complicaciones_propias_embarazo_parto_puerperio, " +
                "causas_complicaron_embarazo_parto_puerperio FROM defuncion_mujer_10_54_anios WHERE id_defuncion = ?";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(SELECT_QUERY)) {
            preparedStatement.setInt(1, idDefuncion);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                defuncion = new Defuncion();
                defuncion.setMuerteDurante(resultSet.getString("muerte_durante"));
                defuncion.setCausasComplicacionesPropiasEmbarazoPartoPuerperio(resultSet.getString(
                        "causas_complicaciones_propias_embarazo_parto_puerperio"));
                defuncion.setCausasComplicaronEmbarazoPartoPuerperio(resultSet.getString(
                        "causas_complicaron_embarazo_parto_puerperio"));
                return defuncion;
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener defuncion_mujer_10_54_anios por id_defuncion: " + e.getMessage());
        } finally {
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException e) {
                    System.err.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
        return null;
    }

    /// # METHODS AUXILIARES

    // Méthod auxiliar para establecer valores en el PreparedStatement
    private void setPreparedStatementValor(PreparedStatement ps, int indice, String valor) throws SQLException {
        if (valor != null) {
            ps.setString(indice, valor);
        } else {
            ps.setNull(indice, Types.VARCHAR);
        }
    }

    // Méthod auxiliar para crear un SQL timestamp de fechaDefuncion
    private Timestamp createSqlTimestamp(Defuncion defuncion) {
        LocalDateTime dateTime = LocalDateTime.of(defuncion.getAnio(), defuncion.getMes(), defuncion.getDia(), defuncion.getHoras(), defuncion.getMinutos());
        return Timestamp.valueOf(dateTime);
    }

    // Méthod auxiliar para crear un SQL date de cirugiaFecha
    private java.sql.Date createSqlDate(Defuncion defuncion) {
        LocalDate date = LocalDate.of(defuncion.getCirugiaAnio(), defuncion.getCirugiaMes(), defuncion.getCirugiaDia());
        return java.sql.Date.valueOf(date);
    }

    // Mehod auxiliar para insertar la fecha de defuncion
    private void setFechaDefuncion(Defuncion defuncion, ResultSet resultSet) throws SQLException {
        Timestamp fecha = resultSet.getTimestamp("fecha");
        defuncion.setAnio(fecha.toLocalDateTime().getYear());
        defuncion.setMes(fecha.toLocalDateTime().getMonthValue());
        defuncion.setDia(fecha.toLocalDateTime().getDayOfMonth());
        defuncion.setHoras(fecha.toLocalDateTime().getHour());
        defuncion.setMinutos(fecha.toLocalDateTime().getMinute());
    }
}
