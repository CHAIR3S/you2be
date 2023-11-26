package com.tecnm.you2be;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tecnm.you2be.youtube.models.Search;
import com.tecnm.you2be.youtube.models.Video;
import com.tecnm.you2be.youtube.models.YoutubeResponse;
import com.tecnm.you2be.youtube.service.YoutubeVideoService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        //Compilador de ventana inicial
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();


        //Compilar ventana con WebView
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage.setTitle("Simple Youtube video Player");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        stage.setScene(new Scene(root, width, height));
        stage.show();
    }

    public static void main(String[] args) {

        launch();
    }
}