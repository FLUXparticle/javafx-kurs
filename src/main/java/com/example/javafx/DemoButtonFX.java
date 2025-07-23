package com.example.javafx;

import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DemoButtonFX extends Application {

    private FlowPane root;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Button");
        root = new FlowPane();
        root.setHgap(10); // horizontaler Abstand zwischen Komponenten
        root.setVgap(10); // vertikaler Abstand zwischen Komponenten
        root.setAlignment(Pos.CENTER); // zentriert den Inhalt

        Button button = new Button("Klick mich!");
        button.setOnAction(this::click);

        root.getChildren().add(button);

        Scene scene = new Scene(root, 300, 150);
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    private void click(ActionEvent event) {
        root.setStyle("-fx-background-color: green;");
    }

    public static void main(String[] args) {
        launch(args);
    }

}
