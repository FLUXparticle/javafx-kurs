package com.example.javafx.geheim;

import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class View extends VBox {

    public final TextField tfInput = new TextField();
    public final TextField tfOutput = new TextField();

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

}
