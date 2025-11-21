package com.example.javafx.counter;

import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class CounterView extends VBox {

    public TextField textField = new TextField();
    public ProgressBar progressBar = new ProgressBar(0);
    public Button startButton = new Button("Start");

    public CounterView() {
        setSpacing(10);
        setPadding(new Insets(20));

        textField.setPrefWidth(200);
        progressBar.setPrefWidth(200);

        getChildren().addAll(textField, progressBar, startButton);
    }

}