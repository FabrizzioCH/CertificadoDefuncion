package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    Connection conexion;
    String query;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    public UsuarioDAO() {
        conexion = new MySQLConexion().getConnection();
    }

    public Usuario login(String usuario, String password) {
        Usuario usuarioLogueado = null;
        try {
            query = "SELECT nombre, apellido_paterno, usuario, tipo_usuario "
                    + "FROM usuarios WHERE usuario=? AND password=?;";
            preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setString(1, usuario);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                usuarioLogueado = new Usuario();
                usuarioLogueado.setNombre(resultSet.getString("nombre"));
                usuarioLogueado.setApellidoPaterno(resultSet.getString("apellido_paterno"));
                usuarioLogueado.setUsername(resultSet.getString("usuario"));
                usuarioLogueado.setTipoUsuario(resultSet.getString("tipo_usuario"));
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
        return usuarioLogueado;
    }

    public boolean cambiarPassword(String usuario, String password) {
        try {
            query = "UPDATE usuarios SET password=? WHERE usuario=?";
            preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, usuario);
            var numResultados = preparedStatement.executeUpdate();
            if (numResultados == 1) {
                return true;
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
        return false;
    }
}
