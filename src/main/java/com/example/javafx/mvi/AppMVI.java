package com.example.javafx.mvi;

import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;

public class AppMVI extends Application {

    @Override
    public void start(Stage primaryStage) {
        View view = new View();

        primaryStage.setTitle("Passwort");
        primaryStage.setScene(new Scene(view, 400, 150));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
