package com.example.javafx.mvi;

import com.example.javafx.mvi.model.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class View extends VBox {

    private final TextField tfInput = new TextField();
    private final TextField tfOutput = new TextField();
    private final Model store;

    public View(Model store) {
        this.store = store;
        setPadding(new Insets(15));
        setSpacing(10);

        tfOutput.setEditable(false);

        getChildren().addAll(
            new Label("Eingabe:"),
            tfInput,
            new Label("Ausgabe:"),
            tfOutput
        );

        // subscribe to state updates
        store.stateProperty().addListener((v, o, newState) -> {
            tfOutput.setText(newState.output());
        });

        // dispatch intent on enter
        tfInput.setOnAction(event -> {
            var intent = new Intent.Submit(tfInput.getText());
            store.dispatch(intent);
        });
    }

    public void buildStage(Stage stage) {
        stage.setTitle("Passwort");
        stage.setScene(new Scene(this, 400, 150));
        stage.setResizable(false);
        stage.show();
    }
}
