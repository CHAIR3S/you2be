package com.tecnm.you2be;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tecnm.you2be.DAO.CardVideoDao;
import com.tecnm.you2be.models.CardVideo;
import com.tecnm.you2be.models.Usuario;
import com.tecnm.you2be.youtube.models.Search;
import com.tecnm.you2be.youtube.models.YoutubeResponse;
import com.tecnm.you2be.youtube.service.YoutubeVideoService;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.image.Image;
import javafx.util.Callback;
import org.w3c.dom.Text;



import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelloController implements Initializable {
    // DAOS
    CardVideoDao cardVideoDao = new CardVideoDao();
    private double xOffset = 0;
    private double yOffset = 0;

    List<CardVideo> listVideos = FXCollections.observableArrayList();

    @FXML
    private Label welcomeText;

    private final Pattern pattern = Pattern.compile("(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%\u200C\u200B2F|youtu.be%2F|%2Fv%2F)[^#\\&\\?\\n]*");
    private String url;

    //Inyeccion de servicio para videos de youtube
    YoutubeVideoService youtube = new YoutubeVideoService();

    private List<Image> imageList = new ArrayList<>();


    @FXML
    private WebView webView, webViewReproductor;

    @FXML
    private TextField txtUrl, txtBusqueda ;

    @FXML
    private AnchorPane anPaneInicio, anPaneMisVideos, anPaneReproductor;

    @FXML
    private Pane paneImages, paneVideos;

    @FXML
    private ListView<CardVideo> imageListView;

    Usuario usuario = new Usuario();

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }


    @FXML
    void play() {
        //Evalua con expresion regular y manda video con url embebida a WebView
        if(!txtUrl.getText().equals("")){
            Matcher matcher = pattern.matcher(this.txtUrl.getText());
            paneImages.setVisible(false);
            webView.setVisible(true);
            if(matcher.find()){
                this.url ="https://www.youtube.com/embed/"+matcher.group(0);
                webView.getEngine().load(this.url);
                System.out.println(this.url);
            }else{
                System.out.println("Invalid Url!");
            }
        }
    }

    void play(String url){
        Matcher matcher = pattern.matcher(url);
        paneVideos.setVisible(false);
        paneImages.setVisible(false);
        webViewReproductor.setVisible(true);
        if(matcher.find()){
            this.url ="https://www.youtube.com/embed/"+matcher.group(0);

            webViewReproductor.getEngine().load(this.url);
            System.out.println(this.url);
        }else{
            mostrarMensajeError("Invalid Url!");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

//        //Ejemplo traer video por id del Video
//        //El id del video se encuentra en el link, en el "v=": https://www.youtube.com/watch?v=NEJ3bnieiIU
//
//        try {
//            YoutubeResponse<Video> response = youtube.getVideoById("NEJ3bnieiIU");
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//            response.getItems().forEach(video -> {
//                System.out.println(video.getStatistics().getCommentCount() + "statistics");
//                System.out.println(video.getPlayer().getEmbedHtml() + "player");
//                System.out.println(video.getSnippet().toString() + "snippet");
//            });


//        //Ejemplo buscar videos en youtube por un nombre o cualquier busqueda normal que harias en youtube
//        //En el metodo dice para que es cada cosa
//            YoutubeResponse<Search> response = youtube.getVideoByTitle("The Weeknd", 5);
//            response.getItems().forEach(search -> {
//                System.out.println(search.getSnippet().getTitle());
//                System.out.println(search.getId());
//                System.out.println(search.getKind());
//            });

//        //Ejemplo buscar videos en youtube por un nombre de una pagina en especifico
//        //(Youtube trae resultados de busqueda por medio de paginas, si son 1000 resultados, te los va a traer en partes)
//        try {
//            YoutubeResponse<Search> responset = youtube.getVideoByTitleOtherPage("The Weeknd", 5, "CAIQAA");
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//            responset.getItems().forEach(search -> {
//                System.out.println(search.getSnippet().getTitle());
//                System.out.println(search.getId());
//                System.out.println(search.getKind());
//            });

    }

    public void onInicioOpen(ActionEvent actionEvent) {
        cerrarVentanas();
        anPaneInicio.setVisible(true);
    }
    @FXML
    void searchVideosInDatabase() throws SQLException {

        if(txtBusqueda.getText().trim().isEmpty()){
            mostrarMensajeError("Ingresa texto para buscar en la base de datos");
        }else{
            List<CardVideo> listVideos = cardVideoDao.getAllMyVideos(this.usuario, txtBusqueda.getText());

            if( listVideos.isEmpty() ){
                mostrarMensajeError("No se encontro ningun video dentro de la base de datos");
            }
            else{

                imageListView.getItems().addAll(listVideos);

                utilidadesDelListVies();
            }

        }
    }

    public void onMisVideosOpen(ActionEvent actionEvent) throws SQLException {
        cargarUtilidadesDeVideos();
//        listVideos = cardVideoDao.getAllMyVideos(this.usuario);

        listVideos = List.of(new CardVideo[]{
                new CardVideo(2, "GIJUB", "El temach siendo madreado", "https://github.githubassets.com/assets/GitHub-Mark-ea2971cee799.png", "https://youtu.be/MJkdaVFHrto", "Payed", 30, "El macho" )

        });

        finalizarUrilidadesDeVideos();
    }

    public void onFavoritosOpen(ActionEvent actionEvent) throws SQLException {
        cargarUtilidadesDeVideos();

//        listVideos = cardVideoDao.getAllMyFavoriteVideos(this.usuario);

        listVideos = List.of(new CardVideo[]{
                new CardVideo(2, "GIJUB", "El temach siendo madreado", "https://github.githubassets.com/assets/GitHub-Mark-ea2971cee799.png", "https://youtu.be/MJkdaVFHrto", "Payed", 30, "El macho" )

        });

        finalizarUrilidadesDeVideos();

    }

    public void onMiCuentaOpen(ActionEvent actionEvent) {

        try {
            // Código para abrir la nueva ventana
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/tecnm/you2be/cuenta-view.fxml"));
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


// Método para obtener datos del objet seleccionado de lista de videos
    public void handleListViewClick() {
        // Obtiene el ítem seleccionado


        CardVideo selectedCard = imageListView.getSelectionModel().getSelectedItem();

        if (selectedCard != null) {
            // Accede al título del objeto, url, etc, lo que se necesite
            String url = selectedCard.getLinkVideo();


            //Abrir reproductor
            cerrarVentanas();
            abrirReproductor(url);
        }
    }

    public void abrirReproductor(String url){
        anPaneReproductor.setVisible(true);
        play(url);
    }

    public void cerrarVentanas(){
        anPaneReproductor.setVisible(false);
        anPaneMisVideos.setVisible(false);
        anPaneInicio.setVisible(false);
    }

    public void informacionUsuario() {
        Usuario usuario = LoginController.getUsuarioActual();
        if (usuario != null) {
            // Acceder a los detalles del usuario que inicio sesion
            int idUsuario = usuario.getIdUsuario();
            String nombre = usuario.getNombre();
            String primerApellido = usuario.getPrimerApellido();
            String segundoApellido = usuario.getSegundoApellido();
            String correo = usuario.getEmail();
            Date nacimiento = usuario.getNacimiento();

            this.usuario = usuario;


        } else {
            mostrarMensajeError("Error al obtener informacion del usuario actual");
            this.usuario = null;
        }
    }

    private void mostrarMensajeError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void cargarUtilidadesDeVideos(){
        cerrarVentanas();
        anPaneMisVideos.setVisible(true);

        imageListView.getItems().removeAll();
        imageListView.getItems().clear();

        informacionUsuario();
    }

    private void finalizarUrilidadesDeVideos(){
        imageListView.getItems().addAll(listVideos);

        utilidadesDelListVies();

        // Ejecuta cuando se selecciona video
        imageListView.setOnMouseClicked(event -> handleListViewClick());
    }

    private void utilidadesDelListVies(){
        imageListView.setCellFactory(new Callback<ListView<CardVideo>, ListCell<CardVideo>>() {
            @Override
            public ListCell<CardVideo> call(ListView<CardVideo> param) {
                return new ListCell<CardVideo>() {
                    @Override
                    protected void updateItem(CardVideo item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            VBox vbox = new VBox(5);

                            // ImageView para la imagen
                            ImageView imageView = new ImageView(new Image(item.getLinkImage()));
                            imageView.setFitWidth(150);
                            imageView.setPreserveRatio(true);

                            // Etiqueta para el titulo
                            Label lblTitulo = new Label(item.getTitulo());
                            lblTitulo.setStyle("-fx-font-weight: bold");


                            // Etiqueta para descripcion
                            Label lblDescipcion = new Label(item.getDescripcion());
                            lblDescipcion.setStyle("-fx-text-fill: rgb(128,128,128)");


                            // Etiqueta para tipo: paga/gratuito
                            Label lblTipo = new Label(item.getTipo());
                            lblTipo.setStyle("-fx-font-weight: bold");


                            // Etiqueta para precio
                            Label lblPrecio = new Label("$ " + item.getPrecio());

                            // Etiqueta para canal
                            Label lblCanal = new Label(item.getCanal());
                            lblCanal.setStyle("-fx-font-size: 9.5");



                            HBox tipoVBox = new HBox();
                            tipoVBox.getChildren().addAll(lblTipo, lblPrecio);
                            tipoVBox.setSpacing(10);


                            //Si es gratis, no mostrar precio
                            if(item.getTipo().toUpperCase().equals("FREE"))
                                lblPrecio.setText("");
                            else
                                lblTipo.setText(item.getTipo().toUpperCase());


                            vbox.getChildren().addAll(lblTitulo, lblDescipcion, tipoVBox, lblCanal);

                            HBox hbox = new HBox();
                            hbox.getChildren().addAll(imageView, vbox);
                            hbox.setSpacing(20);

                            setGraphic(hbox);
                        } else {
                            setGraphic(null);
                        }
                    }
                };
            }

        });
    }
}