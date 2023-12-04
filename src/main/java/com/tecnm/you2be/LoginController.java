package com.tecnm.you2be;

import com.tecnm.you2be.DAO.UsuarioDao;
import com.tecnm.you2be.models.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.util.Optional;
import java.io.IOException;


public class LoginController {

    @FXML
    Hyperlink hyperlinkInvitado, hyperlinkRegistrarse;

    @FXML
    TextField txtCorreo;

    @FXML
    PasswordField textFieldContraseña;
    UsuarioDao usuarioDao = new UsuarioDao();

    Usuario usuario = new Usuario();

    private static Usuario usuarioActual;

    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }

    //Metodo para el boton d inicio de sesión
    @FXML
    protected void OnInicioSesion(ActionEvent event) {
        String email = txtCorreo.getText();
        String password = textFieldContraseña.getText();
        Optional<Usuario> optionalUsuario = usuarioDao.login(email, password);

        if (optionalUsuario.isPresent()) {
            mostrarMensaje("Inicio de sesion exitoso");
            usuarioActual = optionalUsuario.get();
            abrirNuevaVentana("hello-view.fxml");

        } else {
            mostrarMensajeError("Credenciales inválidas");
        }
    }
    @FXML
    protected void OnRegistro(){
        //mostrarMensaje("Dirigiendose a la ventana de resgitro");
        abrirNuevaVentana("registro.fxml");
        cerrarVentanaActual();
    }

    // Método para mostrar un mensaje de éxito
    private void mostrarMensaje(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Éxito");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    // Método para mostrar un mensaje de error
    private void mostrarMensajeError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    // Método para abrir una nueva ventana
    public void abrirNuevaVentana(String name) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(name));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            // Cerrar la ventana actual
            cerrarVentanaActual();
        } catch (IOException e) {
            mostrarMensajeError("Error al cargar la nueva ventana");
            e.printStackTrace();
        }
    }


    // Método para cerrar la ventana actual
    private void cerrarVentanaActual() {
        Stage stage = (Stage) txtCorreo.getScene().getWindow();
        stage.close();
    }
}



