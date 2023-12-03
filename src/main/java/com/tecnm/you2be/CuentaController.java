package com.tecnm.you2be;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
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
    private ComboBox<?> cboTipo;

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



     private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
        actualizarCosto();
        btnActualizar.setVisible(false);
        btnPagar.setVisible(true);

    }

    private void actualizarCosto() {
        //aquí se decidirá cual de los dos tipos está seleccionado y al momento
        //que se seleccione en el combobox se actualizará el precio al seleccionado
    }

    public void onPagarAction(ActionEvent actionEvent) {

    }

    public void OnGetReportes(ActionEvent actionEvent) {

        anPaneCuenta.setVisible(false);
        anPaneReportes.setVisible(true);
        lblTitle.setText("Reportes");
    }
}

