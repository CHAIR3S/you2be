package com.tecnm.you2be;

import com.tecnm.you2be.DAO.UsuarioDao;
import com.tecnm.you2be.models.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;

public class registroController {

    @FXML
    TextField txtFieldNombre;
    @FXML
    TextField txtFieldPrimerApellido, txtFieldSegundoApellido;
    @FXML
    TextField txtFieldCorreo;
    @FXML
    TextField txtFieldContraseña;
    @FXML
    TextField textFieldDirección;
    @FXML
    TextField textFieldTelefono;
    @FXML
    DatePicker datePickerFechaNacimiento;
    @FXML
    Button btnRegistrar;
    private Stage stage;

    UsuarioDao usuarioDao = new UsuarioDao();

    LoginController lc = new LoginController();

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    protected void registrarUsuario(ActionEvent event) {
        String nombre = txtFieldNombre.getText();
        String primeraAellido = txtFieldPrimerApellido.getText();
        String segundoApellido = txtFieldSegundoApellido.getText();
        String correo = txtFieldCorreo.getText();
        String contraseaña = txtFieldContraseña.getText();
        Date nacimiento = Date.valueOf(datePickerFechaNacimiento.getValue());

        // Validar que los campos no estén vacíos
        if (nombre.isEmpty() || primeraAellido.isEmpty()|| segundoApellido.isEmpty() || correo.isEmpty() || contraseaña.isEmpty() || nacimiento == null) {
            mostrarAlerta("Todos los campos son obligatorios.");
            return;
        }

        // Crear el objeto Usuario
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setPrimerApellido(primeraAellido);
        nuevoUsuario.setSegundoApellido(segundoApellido);
        nuevoUsuario.setEmail(correo);
        nuevoUsuario.setPassword(contraseaña);
        nuevoUsuario.setNacimiento(nacimiento);

        // Guardar en la base de datos
        if (usuarioDao.save(nuevoUsuario)) {
            mostrarAlerta("Usuario registrado exitosamente.");
            limpiarCampos();
        } else {
            mostrarAlerta("Error al registrar el usuario. Por favor, inténtelo de nuevo.");
        }
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void limpiarCampos() {
        txtFieldNombre.clear();
        txtFieldPrimerApellido.clear();
        txtFieldSegundoApellido.clear();
        txtFieldCorreo.clear();
        txtFieldContraseña.clear();
        datePickerFechaNacimiento.setValue(null);
        textFieldDirección.clear();
        textFieldTelefono.clear();
    }

    @FXML
    protected void OnAtras(){
        //lc.abrirNuevaVentana("login-view.fxml");
        //Stage stage = (Stage) txtFieldContraseña.getScene().getWindow();
        //stage.close();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login-view.fxml"));
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

    private void cerrarVentanaActual(){
        Stage stage = (Stage) txtFieldContraseña.getScene().getWindow();
        stage.close();
    }

    private void mostrarMensajeError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
