package com.example.javafx.mvvm;

import com.example.javafx.mvvm.model.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class View extends VBox {

    public View(ViewModel vm) {
        setPadding(new Insets(15));
        setSpacing(10);

        TextField tfInput  = new TextField();
        TextField tfOutput = new TextField();
        tfOutput.setEditable(false);

        // Bindings
        tfInput.textProperty().bindBidirectional(vm.inputProperty());
        tfOutput.textProperty().bind(vm.outputProperty());

        tfInput.setOnAction(event -> vm.checkPassword());

        getChildren().addAll(
            new Label("Eingabe:"), tfInput,
            new Label("Ausgabe:"), tfOutput
        );
    }

    public void buildStage(Stage stage) {
        stage.setTitle("Passwort (MVVM)");
        stage.setScene(new Scene(this, 400, 150));
        stage.setResizable(false);
        stage.show();
    }

}
