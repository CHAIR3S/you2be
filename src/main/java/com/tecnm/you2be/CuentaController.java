package com.tecnm.you2be;

import com.tecnm.you2be.DAO.*;
import com.tecnm.you2be.models.*;
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

import javafx.scene.control.TextField;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import java.util.List;

public class CuentaController implements Initializable{



    @FXML
    private AnchorPane anPaneReportes;

    @FXML
    private Label lblNombre;

    @FXML
    private TextField txtNoTarjeta;

    @FXML
    private TextField txtTituloVideo;

    @FXML
    private Label lblContraseña;

    @FXML
    private ComboBox<String> cboMetodoPago;

    @FXML
    private TextField txtUrlLink;

    @FXML
    private TextField txtTipoVideo;

    @FXML
    private TextField txtPrecio;

    @FXML
    private ComboBox<String> cboTipoSub;

    @FXML
    private TextField txtCVV;

    @FXML
    private ComboBox<String> cboTipoTarjeta;

    @FXML
    private AnchorPane anPaneTarjeta;

    @FXML
    private Button btnPagar;

    @FXML
    private AnchorPane anPaneCuenta;

    @FXML
    private AnchorPane anPaneMisVideos;

    @FXML
    private Button btnActualizar;

    @FXML
    private TextField txtDescripcionVideo;

    @FXML
    private TextField txtMonto;

    @FXML
    private Label lblUsuario;

    @FXML
    private Label lblNacimiento;

    @FXML
    private AnchorPane anPaneCompras;

    @FXML
    private AnchorPane anPaneInformacion;

//    @FXML
//    private Label lblContraseña;

    @FXML
    private Label lblEmail;

    @FXML
    private AnchorPane anPaneSuscripcion;

    @FXML
    private TextField txtUrlImagen;

    @FXML
    private AnchorPane anPanePago;

    @FXML
    private Button onPagar;

    @FXML
    private ComboBox<String> cboTarjetaPago;

    @FXML
    private Label lblTitle;

    @FXML
    private Label lblCosto;

//    @FXML
//    private AnchorPane anPaneMisVideos;

    @FXML
    private Label lblTipo;

    TarjetaDao tarjetaDao = new TarjetaDao();
    PagoDao pagoDao = new PagoDao();

    VideoDao videoDao = new VideoDao();

    CanalDao canalDao = new CanalDao();

    SubscripcionDao subscripcionDao = new SubscripcionDao();
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

        ObservableList<String> optionsTipoSub = FXCollections.observableArrayList("premium", "basic");
        cboTipoSub.setItems(optionsTipoSub);

    // También puedes seleccionar un valor por defecto si lo deseas
    cboTipoSub.getSelectionModel().selectFirst(); // Esto seleccionará el primer valor ("premium") por defecto

    // Agregar un listener al ComboBox para detectar cambios de selección
    cboTipoSub.setOnAction(event -> actualizarCosto(cboTipoSub.getValue()));




    ObservableList<String> optionsTipoTarjeta = FXCollections.observableArrayList("credito", "debito");
    cboTipoTarjeta.setItems(optionsTipoTarjeta);
    cboTipoTarjeta.getSelectionModel().selectFirst();


    ObservableList<String> optionMetodoPago = FXCollections.observableArrayList("tarjeta puntos", "tarjeta credito/debito");
    cboMetodoPago.setItems(optionMetodoPago);
    cboMetodoPago.getSelectionModel().selectFirst();

    inicializarComboBox();
    }

    public void inicializarComboBox() {
        // Obtener todos los números de tarjeta usando el método del DAO
        List<String> numerosTarjeta = tarjetaDao.findAllNumerosTarjeta();

        // Convertir la lista de números de tarjeta a un ObservableList para el ComboBox
        ObservableList<String> numerosTarjetaObservable = FXCollections.observableArrayList(numerosTarjeta);

        // Asignar los items al ComboBox
        cboTarjetaPago.setItems(numerosTarjetaObservable);
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
        anPaneTarjeta.setVisible(false);
        anPanePago.setVisible(false);
        lblTitle.setText("");
    }

    public void onActualizarAction(ActionEvent actionEvent) {

        lblTipo.setVisible(false);
        cboTipoSub.setVisible(true);
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

    public void onMetodoPago(ActionEvent actionEvent) {
        anPaneTarjeta.setVisible(true);
        anPaneSuscripcion.setVisible(false);
    }

    public void onReporteMasVistos(){


        Usuario newUser = LoginController.getUsuarioActual();

        try {
            File file = new File(DESTINO_MAS_VISTOS);
            file.getParentFile().mkdirs();
            reporteVistos.createPdf(DESTINO_MAS_VISTOS, newUser.getIdUsuario());
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

        Usuario newUser = LoginController.getUsuarioActual();

        try {
            File file = new File(DESTINO_ESTADO_CUENTA);
            file.getParentFile().mkdirs();
            estadoCuentaReport.createPdf(DESTINO_ESTADO_CUENTA, newUser.getIdUsuario());
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
    // Obtener los datos de los TextField
    String numeroTarjeta = txtNoTarjeta.getText();
    String cvv = txtCVV.getText();

    // Obtener el tipo de tarjeta seleccionado en el ComboBox
    String tipo = cboTipoTarjeta.getSelectionModel().getSelectedItem();

    // Verificar si se ha seleccionado algún tipo de tarjeta
    if (tipo == null || tipo.isEmpty()) {
        System.out.println("Por favor, selecciona un tipo de tarjeta");
        // Puedes manejar este caso específico, por ejemplo, mostrar un mensaje al usuario
        return; // Salir del método ya que el tipo de tarjeta no está seleccionado
    }

    // Crear un objeto Tarjeta con los datos obtenidos
    Tarjeta nuevaTarjeta = new Tarjeta();
    nuevaTarjeta.setNumero(numeroTarjeta);
    nuevaTarjeta.setCvv(cvv);
    nuevaTarjeta.setTipo(tipo);

    // Guardar la nueva tarjeta en la base de datos usando el método save
    boolean guardadoExitoso = tarjetaDao.save(nuevaTarjeta); // Suponiendo que 'save' es un método de la misma clase que contiene la lógica para guardar en la base de datos

    if (guardadoExitoso) {
        // Éxito al guardar la tarjeta en la base de datos
        System.out.println("Tarjeta guardada exitosamente en la base de datos");
        // Puedes realizar otras acciones aquí, como limpiar los TextField o mostrar un mensaje de éxito
        anPaneTarjeta.setVisible(false);
        anPanePago.setVisible(true);
    } else {
        // Error al guardar la tarjeta en la base de datos
        System.out.println("Error al guardar la tarjeta en la base de datos");
        // Puedes manejar el error mostrando un mensaje al usuario o realizando alguna acción específica
    }


    }


    //Metodo para subir videos----------------------------------------------------------------------------------------------------------------------------------
    public void onSubirVideo(ActionEvent actionEvent) {
    Video video = new Video();

    // Obtener los valores de los TextField
    String link = txtUrlLink.getText();
    String imagen = txtUrlImagen.getText();
    String titulo = txtTituloVideo.getText();
    String descripcion = txtDescripcionVideo.getText();
    String tipo = txtTipoVideo.getText();
    String precio = txtPrecio.getText();

    // Validar si algún TextField está vacío
    if (link.isEmpty() || imagen.isEmpty() || titulo.isEmpty() || descripcion.isEmpty() || tipo.isEmpty() || precio.isEmpty()) {
        // Mostrar un mensaje indicando que algún campo está vacío
        mostrarMensajeError("Por favor, completa todos los campos.");
        return; // Salir del método si algún campo está vacío
    }

    // Si todos los campos están llenos, continuar con la lógica para guardar el video

    try {
        BigDecimal bDPrecio = new BigDecimal(precio);

        String url = link + " " + imagen;

        video.setLink(url);
        video.setTitulo(titulo);
        video.setDescripcion(descripcion);
        video.setTipo(tipo);
        video.setPrecio(bDPrecio);

        // Guardar el video en la base de datos usando el método save
        boolean guardadoExitoso = videoDao.save(video);

        if (guardadoExitoso) {
            // Éxito al guardar el video en la base de datos
            System.out.println("Video guardado exitosamente en la base de datos");
            // Puedes realizar otras acciones aquí, como limpiar los TextField o mostrar un mensaje de éxito
        } else {
            // Error al guardar el video en la base de datos
            System.out.println("Error al guardar el video en la base de datos");
            // Puedes manejar el error mostrando un mensaje al usuario o realizando alguna acción específica
        }
    } catch (NumberFormatException e) {
        // Manejar la excepción si el campo de precio no se puede convertir a BigDecimal
        mostrarMensajeError("El precio debe ser un número válido.");
    }
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

    public void onPagarAction(ActionEvent actionEvent) {


        // Obtener los datos de los TextField
    String metodo = cboMetodoPago.getSelectionModel().getSelectedItem();
    String monto = txtMonto.getText();

    // Verificar si se ha seleccionado algún método de pago
    if (metodo == null || metodo.isEmpty()) {
        System.out.println("Por favor, selecciona un método de pago");
        // Puedes manejar este caso específico, por ejemplo, mostrar un mensaje al usuario
        return; // Salir del método ya que el método de pago no está seleccionado
    }

    // Obtener el número de tarjeta seleccionado en el ComboBox
    String numeroTarjetaSeleccionado = cboTarjetaPago.getSelectionModel().getSelectedItem();

    // Obtener el ID de la tarjeta seleccionada desde la base de datos usando el DAO
    int idTarjetaSeleccionada = tarjetaDao.getIdByNumero(numeroTarjetaSeleccionado);

    if (idTarjetaSeleccionada != -1) { // Verificar si se encontró el ID de la tarjeta
        Pago pago = new Pago();
        pago.setMetodo(metodo);
        pago.setMonto(Double.parseDouble(monto));
        pago.setIdTarjeta(idTarjetaSeleccionada); // Asignar el ID de la tarjeta al pago

        // Guardar el pago en la base de datos usando el método save
        boolean guardadoExitoso = pagoDao.save(pago); // Suponiendo que 'save' es un método de la misma clase que contiene la lógica para guardar en la base de datos

        if (guardadoExitoso) {
            // Éxito al guardar el pago en la base de datos
            System.out.println("Pago realizado exitosamente en la base de datos");
            // Puedes realizar otras acciones aquí, como limpiar los TextField o mostrar un mensaje de éxito
            anPanePago.setVisible(false);
            anPaneSuscripcion.setVisible(true);
        } else {
            // Error al guardar el pago en la base de datos
            System.out.println("Error al realizar el pago en la base de datos");
            // Puedes manejar el error mostrando un mensaje al usuario o realizando alguna acción específica
        }
    } else {
        // No se encontró el ID de la tarjeta
        System.out.println("No se encontró el ID de la tarjeta seleccionada");
        // Puedes manejar este caso específico, por ejemplo, mostrando un mensaje al usuario
    }

    addSubscripcion();

    }

    private void addSubscripcion() {

    Subscripcion subscripcion = new Subscripcion(); // Suponiendo que tienes una instancia de Subscripcion

    // Asignar valores al objeto Subscripcion desde tu lógica
    BigDecimal costo = new BigDecimal(lblCosto.getText()); // Obtener el costo desde un label, por ejemplo
    String tipo = cboTipoSub.getValue(); // Obtener el tipo desde el ComboBox


    // Asignar los valores al objeto Subscripcion
    subscripcion.setCosto(costo);
    subscripcion.setTipo(tipo);

    // Ahora, puedes usar tu método save para guardar la subscripción en la base de datos
    boolean guardadoExitoso = subscripcionDao.save(subscripcion);

    if (guardadoExitoso) {
        // Éxito al guardar la subscripción en la base de datos
        System.out.println("Subscripción guardada exitosamente en la base de datos");
        // Puedes realizar otras acciones aquí, como limpiar los valores o mostrar un mensaje de éxito
    } else {
        // Error al guardar la subscripción en la base de datos
        System.out.println("Error al guardar la subscripción en la base de datos");
        // Puedes manejar el error mostrando un mensaje al usuario o realizando alguna acción específica
    }
    }
}

