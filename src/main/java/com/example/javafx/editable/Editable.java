package com.example.javafx.editable;

import javafx.beans.property.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.io.*;

public class Editable extends Pane {

    @FXML
    private Label label;
    @FXML
    private TextField textField;

    private final StringProperty text = new SimpleStringProperty("Hallo");

    private final BooleanProperty editing = new SimpleBooleanProperty();

    public Editable() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Editable.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        loader.load();
    }

    public void initialize() {
        label.textProperty().bind(text);
        textField.textProperty().bindBidirectional(text);
        textField.visibleProperty().bind(editing);

        label.setOnMouseClicked(event -> {
            editing.setValue(true);
        });
        textField.setOnAction(event -> {
            editing.setValue(false);
        });
    }

}
