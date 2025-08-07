package com.example.javafx.mvp;

import com.example.javafx.mvp.model.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class View extends VBox implements Presenter {

    private final TextField tfInput = new TextField();
    private final TextField tfOutput = new TextField();

    public View() {
        setPadding(new Insets(15));
        setSpacing(10);

        tfOutput.setEditable(false);

        getChildren().addAll(
            new Label("Eingabe:"),
            tfInput,
            new Label("Ausgabe:"),
            tfOutput
        );
    }

    @Override
    public String getInput() {
        return tfInput.getText();
    }

    @Override
    public void setOutput(String output) {
        tfOutput.setText(output);
    }

    @Override
    public void onInput(Runnable handler) {
        tfInput.setOnAction(e -> handler.run());
    }

    public void buildStage(Stage stage) {
        stage.setTitle("Passwort");
        stage.setScene(new Scene(this, 400, 150));
        stage.setResizable(false);
        stage.show();
    }

}
