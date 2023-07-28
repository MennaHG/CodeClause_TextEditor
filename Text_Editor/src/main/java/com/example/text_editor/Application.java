package com.example.text_editor;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException, URISyntaxException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("teUI.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        URL css = Application.class.getResource("Stylesheet.css");
       scene.getStylesheets().add(css.toString());
       Image icon = new Image(getClass().getResource("icon.png").toURI().toString());
       stage.getIcons().add(icon);
        stage.setTitle("Text Editor");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}