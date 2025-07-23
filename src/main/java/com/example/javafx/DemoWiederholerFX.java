package com.example.javafx;

import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class DemoWiederholerFX extends Application {

    private TextField tfInput;
    private TextField tfOutput;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Wiederholer");

        FlowPane root = new FlowPane();
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        root.getChildren().add(new Label("Eingabe:"));

        tfInput = new TextField();
        tfInput.setPrefColumnCount(15);
        tfInput.setOnAction(this::repeat);
        root.getChildren().add(tfInput);

        root.getChildren().add(new Label("Ausgabe:"));

        tfOutput = new TextField();
        tfOutput.setPrefColumnCount(15);
        tfOutput.setEditable(false); // optional, wie bei Swing üblich
        root.getChildren().add(tfOutput);

        Scene scene = new Scene(root, 300, 150);
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    private void repeat(ActionEvent event) {
        String input = tfInput.getText();
        tfOutput.setText(input);
    }

    public static void main(String[] args) {
        launch(args);
    }
}