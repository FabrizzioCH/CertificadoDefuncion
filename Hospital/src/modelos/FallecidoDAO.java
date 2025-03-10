package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;

public class FallecidoDAO {

    Connection conexion;
    String query;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    public FallecidoDAO() {
        conexion = new MySQLConexion().getConnection();
    }

    public int registrar(Fallecido fallecido) {
        try {
            LocalDate fechaNacimiento = LocalDate.of(fallecido.getAnioNacimiento(), fallecido.getMesNacimiento(), fallecido.getDiaNacimiento());
            // Convertir LocalDate a java.sql.Date
            Date sqlDate = Date.valueOf(fechaNacimiento);

            //Inserción de datos si se conoce a al persona y la nacionalidad es mexicana 
            if (!fallecido.isDesconocido() && fallecido.getNacionalidad().equals("Mexicana")) {
                query = "INSERT INTO fallecido("
                        + "desconocido, nombre, apellido_paterno, apellido_materno, "
                        + "fecha_nacimiento, sexo, entidad_nacimiento, curp, nacionalidad) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

                preparedStatement = conexion.prepareStatement(query);
                preparedStatement.setBoolean(1, fallecido.isDesconocido());
                preparedStatement.setString(2, fallecido.getNombre());
                preparedStatement.setString(3, fallecido.getApellidoPaterno());
                preparedStatement.setString(4, fallecido.getApellidoMaterno());
                preparedStatement.setDate(5, sqlDate);
                preparedStatement.setString(6, fallecido.getSexo());
                preparedStatement.setString(7, fallecido.getEntidadNacimiento());
                preparedStatement.setString(8, fallecido.getCurp());
                preparedStatement.setString(9, fallecido.getNacionalidad());
                var numRegistrosAfectados = preparedStatement.executeUpdate();

                if (numRegistrosAfectados > 0) {
                    return 1;
                }
            }
            //Inserción de datos si se conoce a al persona y la nacionalidad es otra
            if (!fallecido.isDesconocido() && fallecido.getNacionalidad().equals("Otra")) {
                query = "INSERT INTO fallecido("
                        + "desconocido, nombre, apellido_paterno, apellido_materno, "
                        + "fecha_nacimiento, sexo, entidad_nacimiento, curp, "
                        + "nacionalidad, especifique_nacionalidad) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                preparedStatement = conexion.prepareStatement(query);
                preparedStatement.setBoolean(1, fallecido.isDesconocido());
                preparedStatement.setString(2, fallecido.getNombre());
                preparedStatement.setString(3, fallecido.getApellidoPaterno());
                preparedStatement.setString(4, fallecido.getApellidoMaterno());
                preparedStatement.setDate(5, sqlDate);
                preparedStatement.setString(6, fallecido.getSexo());
                preparedStatement.setString(7, fallecido.getEntidadNacimiento());
                preparedStatement.setString(8, fallecido.getCurp());
                preparedStatement.setString(9, fallecido.getNacionalidad());
                preparedStatement.setString(10, fallecido.getEspecifiqueNacionalidad());
                var numRegistrosAfectados = preparedStatement.executeUpdate();

                if (numRegistrosAfectados > 0) {
                    return 1;
                }
            }
            
            //Inserción de datos si se desconoce a al persona y la nacionalidad es mexicana
            if (fallecido.isDesconocido() && fallecido.getNacionalidad().equals("Mexicana")) {
                query = "INSERT INTO fallecido("
                        + "desconocido, fecha_nacimiento, sexo, entidad_nacimiento, "
                        + "curp, nacionalidad) "
                        + "VALUES (?, ?, ?, ?, ?, ?)";

                preparedStatement = conexion.prepareStatement(query);
                preparedStatement.setBoolean(1, fallecido.isDesconocido());
                preparedStatement.setDate(2, sqlDate);
                preparedStatement.setString(3, fallecido.getSexo());
                preparedStatement.setString(4, fallecido.getEntidadNacimiento());
                preparedStatement.setString(5, fallecido.getCurp());
                preparedStatement.setString(6, fallecido.getNacionalidad());
                var numRegistrosAfectados = preparedStatement.executeUpdate();

                if (numRegistrosAfectados > 0) {
                    return 1;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                System.out.println(e);
            }

        }
        return 0;
    }

}
