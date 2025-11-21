package com.example.javafx.test;

import javafx.application.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;

public class TestApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("test.fxml"));
        Parent root = loader.load();

        primaryStage.setTitle("Passwort (FXML)");
        primaryStage.setScene(new Scene(root, 400, 150));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
