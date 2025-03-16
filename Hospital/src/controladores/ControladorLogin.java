package controladores;

import javax.swing.JOptionPane;
import modelos.Usuario;
import modelos.UsuarioDAO;
import vistas.Login;

import vistas.VistaOlvidoPassword;

public class ControladorLogin {

    public static Login vistaLogin = new Login();
    public static VistaOlvidoPassword vistaOlvidoPassword = new VistaOlvidoPassword();

    public static void mostrar() {
        vistaLogin.setVisible(true);
    }

    public static void login() {
        var errores = new StringBuilder();
        if ((vistaLogin.getTxtUsuario().getText().isEmpty()
                || vistaLogin.getTxtUsuario().getText().isBlank())
                || !(vistaLogin.getPwdContrasena().getPassword().length > 0)) {
            errores.append("El usuario y contraseña es obligatorio");
        }

        if (errores.length() > 0) {
            JOptionPane.showMessageDialog(
                    null,
                    errores.toString(),
                    "Errores de validación",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            String username = vistaLogin.getTxtUsuario().getText().trim();
            char[] passArr = vistaLogin.getPwdContrasena().getPassword();
            String pass = new String(passArr);
            Usuario usuario = new UsuarioDAO().login(username, pass);
            if (usuario != null) {
                ControladorSistema.iniciarGenerarCertificadoDefuncion();
                vistaLogin.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null,
                        "Usuario o contraseña incorrectos",
                        "Error al iniciar sesión",
                        JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    public static void cambiarPassword() {
        var errores = new StringBuilder();
        if ((vistaOlvidoPassword.getTxtUsername().getText().isEmpty()
                || vistaOlvidoPassword.getTxtUsername().getText().isBlank())
                || !(vistaOlvidoPassword.getPasswordFieldNewPassword().getPassword().length > 0)) {
            errores.append("El usuario y contraseña es obligatorio");
        }

        if (errores.length() > 0) {
            JOptionPane.showMessageDialog(
                    null,
                    errores.toString(),
                    "Errores de validación",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            String username = vistaOlvidoPassword.getTxtUsername().getText().trim();
            char[] passArr = vistaOlvidoPassword.getPasswordFieldNewPassword().getPassword();
            String pass = new String(passArr);
            if(new UsuarioDAO().cambiarPassword(username, pass)){
                JOptionPane.showMessageDialog(null,
                        "Cambio de contraseña exitoso",
                        "Cambio de contraseña",
                        JOptionPane.INFORMATION_MESSAGE);
                        vistaOlvidoPassword.dispose();
            }else{
                 JOptionPane.showMessageDialog(null,
                        "Surgió un error al intentar cambiar la contraseña, verifique que el usuario exista",
                        "Cambio de contraseña",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void mostrarVistaOlvidoPassword() {
        vistaOlvidoPassword.setVisible(true);
    }

    public static void cerrarVistaOlvidoPassword() {
        vistaOlvidoPassword.dispose();
        limpiarVistaOlvidoPassword();
    }
    
    private static void limpiarVistaOlvidoPassword(){
        vistaOlvidoPassword.getTxtUsername().setText("");
        vistaOlvidoPassword.getPasswordFieldNewPassword().setText("");
    }
}
