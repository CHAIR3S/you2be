package com.tecnm.you2be;

import com.tecnm.you2be.DAO.SubscripcionDao;
import com.tecnm.you2be.models.Subscripcion;
import com.tecnm.you2be.models.Usuario;
import com.tecnm.you2be.reports.EstadoCuentaReport;
import com.tecnm.you2be.reports.MasVistos;
import com.tecnm.you2be.reports.MejorEvaluados;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.Label;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.scene.control.Button;

public class CuentaController implements Initializable{



    @FXML
    private Label lblUsuario;

    @FXML
    private Label lblNombre;

    @FXML
    private Label lblNacimiento;

    @FXML
    private ComboBox<String> cboTipo;

    @FXML
    private AnchorPane anPaneCompras;

    @FXML
    private AnchorPane anPaneInformacion;

    @FXML
    private Label lblContraseña;

    @FXML
    private Label lblEmail;

    @FXML
    private AnchorPane anPaneSuscripcion;

    @FXML
    private AnchorPane anPaneCuenta;

    @FXML
    private AnchorPane anPaneReportes;

    @FXML
    private Label lblTitle;

    @FXML
    private Label lblCosto;

    @FXML
    private AnchorPane anPaneMisVideos;

    @FXML
    private Label lblTipo;

    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnPagar;
     Usuario usuario = new Usuario();

     private MasVistos reporteVistos = new MasVistos();

     private MejorEvaluados mejorEvaluados = new MejorEvaluados();

     private EstadoCuentaReport estadoCuentaReport = new EstadoCuentaReport();


    public static final String DESTINO_MAS_VISTOS = "results/MasVistosReporte.pdf";


    public static final String DESTINO_MEJOR_EVALUADOS = "results/MejorEvaluadosReporte.pdf";


    public static final String DESTINO_ESTADO_CUENTA = "results/EstadoCuenta.pdf";

     private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> options = FXCollections.observableArrayList("premium", "basic");
    cboTipo.setItems(options);

    // También puedes seleccionar un valor por defecto si lo deseas
    cboTipo.getSelectionModel().selectFirst(); // Esto seleccionará el primer valor ("premium") por defecto

    // Agregar un listener al ComboBox para detectar cambios de selección
    cboTipo.setOnAction(event -> actualizarCosto(cboTipo.getValue()));
    }

    @FXML
    public void onActualizarAction(){

    }

    public void onRegresar(ActionEvent actionEvent) {

    try {
        // Código para abrir la nueva ventana
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/tecnm/you2be/hello-view.fxml"));
        Parent root = (Parent) fxmlLoader.load();

        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT); // Establece la ventana sin bordes
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT); // Hace la escena transparente
        stage.setScene(scene);
        stage.show();

        // Código para mover la ventana
        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });

        // Cerrar la ventana actual
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        currentStage.close();

    } catch (IOException e) {
        e.printStackTrace();
    }
    }

    public void OnGetInformacion(ActionEvent actionEvent) {
        anPaneCuenta.setVisible(false);
        anPaneInformacion.setVisible(true);
        lblTitle.setText("Informacion de la cuenta");

        Usuario usuario = LoginController.getUsuarioActual();
        if (usuario != null) {
            // Acceder a los detalles del usuario que inicio sesion
            lblNombre.setText(usuario.getNombre() + " " + usuario.getPrimerApellido() + " " + usuario.getSegundoApellido());
            lblEmail.setText(usuario.getEmail());
            lblContraseña.setText(usuario.getPassword());
            lblNacimiento.setText(String.valueOf(usuario.getNacimiento()));
            lblUsuario.setText(String.valueOf(usuario.getIdUsuario()));
        } else {
            mostrarMensajeError("Error al obtener informacion del usuario actual");
        }
    }
        private void mostrarMensajeError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void OnGetVideos(ActionEvent actionEvent) {
        anPaneCuenta.setVisible(false);
        anPaneMisVideos.setVisible(true);
        lblTitle.setText("Videos subidos");
    }

    public void OnGetSuscrip(ActionEvent actionEvent) {
        anPaneCuenta.setVisible(false);
        anPaneSuscripcion.setVisible(true);
        lblTitle.setText("Suscripcion");
    }

    public void OnGetCompras(ActionEvent actionEvent) {
        anPaneCuenta.setVisible(false);
        anPaneCompras.setVisible(true);
        lblTitle.setText("Mis compras");
    }

    public void onCuenta(ActionEvent actionEvent) {
        anPaneInformacion.setVisible(false);
        anPaneCompras.setVisible(false);
        anPaneMisVideos.setVisible(false);
        anPaneSuscripcion.setVisible(false);
        anPaneCuenta.setVisible(true);
        anPaneReportes.setVisible(false);
        lblTitle.setText("");
    }

    public void onActualizarAction(ActionEvent actionEvent) {

        lblTipo.setVisible(false);
        cboTipo.setVisible(true);
        btnActualizar.setVisible(false);
        btnPagar.setVisible(true);

    }

  private void actualizarCosto(String tipoSeleccionado) {
    if (tipoSeleccionado != null) {
        if (tipoSeleccionado.equals("premium")) {
            lblCosto.setText("100");
        } else if (tipoSeleccionado.equals("basic")) {
            lblCosto.setText("50"); // Cambiar el valor según el tipo seleccionado
        }
    }
}

    public void onPagarAction(ActionEvent actionEvent) {
        /*
        // Suponiendo que tienes una instancia de SubscripcionDao llamada subscripcionDao
    SubscripcionDao subscripcionDao = new SubscripcionDao();

    // Suponiendo que tienes el ID del registro de subscripción que estás trabajando
    int idSubscripcion = obtenerIdSubscripcion(); // Debes obtener el ID correspondiente

    Optional<Subscripcion> subscripcionOptional = subscripcionDao.findById(idSubscripcion);

    if (subscripcionOptional.isPresent()) {
        Subscripcion subscripcion = subscripcionOptional.get();
        int idPago = subscripcion.getIdPago();

        if (idPago != 0) {
            // idPago no es nulo, haz lo que necesites hacer si no es nulo
            // Por ejemplo, algún tipo de lógica para manejar el pago ya realizado
        } else {
            // idPago es nulo, puedes hacer algo aquí
            // Por ejemplo, permitir al usuario realizar un pago
        }
    } else {
        // No se encontró la subscripción, maneja esta situación según tu lógica de negocio
    }
         */
    }

    public void onReporteMasVistos(){

        try {
            File file = new File(DESTINO_MAS_VISTOS);
            file.getParentFile().mkdirs();
            reporteVistos.createPdf(DESTINO_MAS_VISTOS, usuario.getIdUsuario());
            openFile(DESTINO_MAS_VISTOS);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void onReporteMejorEvaluados(){

        try {
            File file = new File(DESTINO_MEJOR_EVALUADOS);
            file.getParentFile().mkdirs();
            mejorEvaluados.createPdf(DESTINO_MEJOR_EVALUADOS);
            openFile(DESTINO_MEJOR_EVALUADOS);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void onReporteEstadoCuenta(){

        try {
            File file = new File(DESTINO_ESTADO_CUENTA);
            file.getParentFile().mkdirs();
            estadoCuentaReport.createPdf(DESTINO_ESTADO_CUENTA, usuario.getIdUsuario());
            openFile(DESTINO_ESTADO_CUENTA);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public void OnGetReportes(ActionEvent actionEvent) {

        anPaneCuenta.setVisible(false);
        anPaneReportes.setVisible(true);
        lblTitle.setText("Reportes");
    }

    public void onAgregarTarjeta(ActionEvent actionEvent) {
    }

    public void onSubirVideo(ActionEvent actionEvent) {
    }

    private void openFile(String filename) {
        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File(filename);
                Desktop.getDesktop().open(myFile);
            } catch (IOException ex) {
                // no application registered for PDFs
            }
        }
    }

}

