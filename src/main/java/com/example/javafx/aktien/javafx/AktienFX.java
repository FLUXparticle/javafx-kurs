package com.example.javafx.aktien.javafx;

import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;

public class AktienFX extends Application {

    @Override
    public void start(Stage primaryStage) {
        // View und Controller anlegen
        AktienView view = new AktienView();
        new AktienController(view);

        // Szene & Stage
        Scene scene = new Scene(view, 750, 500);
        primaryStage.setTitle("Aktien");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
