package controladores;

import javax.swing.JOptionPane;
import modelos.Usuario;
import modelos.UsuarioDAO;
import vistas.Login;

import vistas.VistaOlvidoPassword;

public class ControladorLogin {
    // Vista para la pantalla de inicio de sesión
    public static Login vistaLogin = new Login();
    // Vista para la pantalla de olvido de contraseña
    public static VistaOlvidoPassword vistaOlvidoPassword = new VistaOlvidoPassword();

    // Método para mostrar la vista de login
    public static void mostrar() {
        vistaLogin.setVisible(true);
    }

    // Método para manejar la funcionalidad de inicio de sesión
    public static void login() {
        var errores = new StringBuilder();
        // Validar entrada del usuario
        if ((vistaLogin.getTxtUsuario().getText().isEmpty()
                || vistaLogin.getTxtUsuario().getText().isBlank())
                || !(vistaLogin.getPwdContrasena().getPassword().length > 0)) {
            errores.append("El usuario y contraseña es obligatorio");
        }

        // Mostrar error si falla la validación
        if (errores.length() > 0) {
            JOptionPane.showMessageDialog(
                    null,
                    errores.toString(),
                    "Errores de validación",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            // Obtener el nombre de usuario y contraseña
            String username = vistaLogin.getTxtUsuario().getText().trim();
            char[] passArr = vistaLogin.getPwdContrasena().getPassword();
            String pass = new String(passArr);
            // Realizar el inicio de sesión usando UsuarioDAO
            Usuario usuario = new UsuarioDAO().login(username, pass);
            if (usuario != null) {
                // Si el inicio de sesión es exitoso, iniciar el proceso del sistema y ocultar la vista
                ControladorSistema.iniciarGenerarCertificadoDefuncion();
                vistaLogin.setVisible(false);
            } else {
                // Mostrar error si el inicio de sesión falla
                JOptionPane.showMessageDialog(null,
                        "Usuario o contraseña incorrectos",
                        "Error al iniciar sesión",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Método para manejar la funcionalidad de cambio de contraseña
    public static void cambiarPassword() {
        var errores = new StringBuilder();
        // Validar entrada del usuario
        if ((vistaOlvidoPassword.getTxtUsername().getText().isEmpty()
                || vistaOlvidoPassword.getTxtUsername().getText().isBlank())
                || !(vistaOlvidoPassword.getPasswordFieldNewPassword().getPassword().length > 0)) {
            errores.append("El usuario y contraseña es obligatorio");
        }

        // Mostrar error si falla la validación
        if (errores.length() > 0) {
            JOptionPane.showMessageDialog(
                    null,
                    errores.toString(),
                    "Errores de validación",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            // Obtener el nombre de usuario y la nueva contraseña
            String username = vistaOlvidoPassword.getTxtUsername().getText().trim();
            char[] passArr = vistaOlvidoPassword.getPasswordFieldNewPassword().getPassword();
            String pass = new String(passArr);
            // Realizar el cambio de contraseña usando UsuarioDAO
            if (new UsuarioDAO().cambiarPassword(username, pass)) {
                // Mostrar mensaje de éxito si el cambio es exitoso
                JOptionPane.showMessageDialog(null,
                        "Cambio de contraseña exitoso",
                        "Cambio de contraseña",
                        JOptionPane.INFORMATION_MESSAGE);
                vistaOlvidoPassword.dispose();
            } else {
                // Mostrar error si el cambio de contraseña falla
                JOptionPane.showMessageDialog(null,
                        "Surgió un error al intentar cambiar la contraseña, verifique que el usuario exista",
                        "Cambio de contraseña",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Método para mostrar la vista de olvido de contraseña
    public static void mostrarVistaOlvidoPassword() {
        vistaOlvidoPassword.setVisible(true);
    }

    // Método para cerrar la vista de olvido de contraseña
    public static void cerrarVistaOlvidoPassword() {
        vistaOlvidoPassword.dispose();
        limpiarVistaOlvidoPassword();
    }

    // Método para restablecer los campos en la vista de olvido de contraseña
    private static void limpiarVistaOlvidoPassword() {
        vistaOlvidoPassword.getTxtUsername().setText("");
        vistaOlvidoPassword.getPasswordFieldNewPassword().setText("");
    }
}
