package com.gviktor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nu.pattern.OpenCV;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {

    //TODO:
    //DefinePolygonEventHandler kikapcsolni mikor már kész a polygon(Mivan ha több polygon?)
    //Polygon kiválasztás? Eggyik vonalához  közel kattintás
    //Több polygon egyszerre való kezelése?
    //Polygon betöltő képernyő.
    @Override
    public void start(Stage stage) throws IOException {
        OpenCV.loadShared();
        URL fxmlLocation = getClass().getResource("/main.fxml");

        FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
        Scene scene = new Scene(fxmlLoader.load(), 700, 700);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}