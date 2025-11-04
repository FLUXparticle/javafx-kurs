package com.example.javafx.aktien.javafx;

import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;

public class AktienFX extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Controller anlegen
        AktienController controller = new AktienController();
        Parent root = controller.getRoot();

        // Szene & Stage
        Scene scene = new Scene(root, 750, 500);
        primaryStage.setTitle("Aktien");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
