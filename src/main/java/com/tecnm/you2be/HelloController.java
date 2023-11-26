package com.tecnm.you2be;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tecnm.you2be.youtube.models.Search;
import com.tecnm.you2be.youtube.models.Video;
import com.tecnm.you2be.youtube.models.YoutubeResponse;
import com.tecnm.you2be.youtube.service.YoutubeVideoService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelloController implements Initializable {
    @FXML
    private Label welcomeText;

    private final Pattern pattern = Pattern.compile("(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%\u200C\u200B2F|youtu.be%2F|%2Fv%2F)[^#\\&\\?\\n]*");
    private String url;

    //Inyeccion de servicio para videos de youtube
    YoutubeVideoService youtube = new YoutubeVideoService();

    @FXML
    private WebView webView;

    @FXML
    private TextArea urlTxtArea;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }


    @FXML
    void play() {
        //Evalua con expresion regular y manda video con url embebida a WebView
        if(!urlTxtArea.getText().equals("")){
            Matcher matcher = pattern.matcher(this.urlTxtArea.getText());
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
        try {
            YoutubeResponse<Search> responset = youtube.getVideoByTitleOtherPage("The Weeknd", 5, "CAIQAA");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
//            responset.getItems().forEach(search -> {
//                System.out.println(search.getSnippet().getTitle());
//                System.out.println(search.getId());
//                System.out.println(search.getKind());
//            });

    }
}