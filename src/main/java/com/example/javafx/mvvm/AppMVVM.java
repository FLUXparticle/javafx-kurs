package com.example.javafx.mvvm;

import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;

public class AppMVVM extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        View view = new View();

        primaryStage.setTitle("Passwort (MVVM)");
        primaryStage.setScene(new Scene(view, 400, 150));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
