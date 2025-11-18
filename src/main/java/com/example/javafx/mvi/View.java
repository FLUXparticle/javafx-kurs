package com.example.javafx.mvi;

import com.example.javafx.mvi.model.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class View extends VBox {

    private final Model model;

    private final TextField tfInput = new TextField();
    private final TextField tfOutput = new TextField();

    public View() {
        this.model = new Model();

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
        model.stateProperty().addListener((v, o, newState) -> {
            tfOutput.setText(newState.output());
        });

        // dispatch intent on enter
        tfInput.setOnAction(event -> {
            var intent = new Intent.Submit(tfInput.getText());
            model.dispatch(intent);
        });
    }

}
