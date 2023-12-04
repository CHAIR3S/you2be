package com.tecnm.you2be;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tecnm.you2be.models.CardVideo;
import com.tecnm.you2be.models.Usuario;
import com.tecnm.you2be.youtube.models.Search;
import com.tecnm.you2be.youtube.models.YoutubeResponse;
import com.tecnm.you2be.youtube.service.YoutubeVideoService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelloController implements Initializable {
    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private Label welcomeText;

    private final Pattern pattern = Pattern.compile("(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%\u200C\u200B2F|youtu.be%2F|%2Fv%2F)[^#\\&\\?\\n]*");
    private String url;

    //Inyeccion de servicio para videos de youtube
    YoutubeVideoService youtube = new YoutubeVideoService();

    private List<Image> imageList = new ArrayList<>();


    @FXML
    private WebView webView;

    @FXML
    private TextField txtUrl;

    @FXML
    private AnchorPane anPaneInicio, anPaneMisVideos, anPaneReproductor;

    @FXML
    private Pane paneImages;

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

    public void onMisVideosOpen(ActionEvent actionEvent) {
        cerrarVentanas();
        anPaneMisVideos.setVisible(true);

        imageListView.getItems().removeAll();
        imageListView.getItems().clear();


        CardVideo[] listVideos = {
                new CardVideo(1, "THE LAST OF US", "Video de el mejor juego de mundo", "data:image/jpeg;base64,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", "Free", 34, "Naughty Dog"),
                new CardVideo(2, "THE WEEKND", "La cancion que pasara a la historia", "http://i.ytimg.com/vi/ryisnb5QYXU/hqdefault.jpg", "Payed", 34, "TheWeeknd"),
                new CardVideo(2, "GIJUB", "El villando de una historia mal contada?", "https://github.githubassets.com/assets/GitHub-Mark-ea2971cee799.png", "Free", 34, "AngelySaras"),
                new CardVideo(2, "GIJUB", "El nuevo giju", "https://github.githubassets.com/assets/GitHub-Mark-ea2971cee799.png", "Free", 34, "Bad bunny vevo"),
                new CardVideo(2, "GIJUB", "El temach siendo madreado", "https://github.githubassets.com/assets/GitHub-Mark-ea2971cee799.png", "Payed", 34, "El mencho")

        };

        imageListView.getItems().addAll(listVideos);

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
                            ImageView imageView = new ImageView(new Image(item.getLink()));
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




        // Ejecuta cuando se selecciona video
        imageListView.setOnMouseClicked(event -> handleListViewClick());


    }

    public void onFavoritosOpen(ActionEvent actionEvent) {
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
            String title = selectedCard.getTitulo();

            //Abrir reproductor
            cerrarVentanas();
            abrirReproductor();
        }
    }

    public void abrirReproductor(){
        cerrarVentanas();
        anPaneReproductor.setVisible(true);
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
}