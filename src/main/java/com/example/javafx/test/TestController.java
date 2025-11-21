package com.example.javafx.test;

import javafx.fxml.*;
import javafx.scene.control.*;

public class TestController {

    @FXML
    private TextField inputField;

    @FXML
    private TextField outputField;

    private TestModel model;

    public TestController() {
        this.model = new TestModel();
    }

    @FXML
    private void initialize() {
        // Hier können Initialisierungen vorgenommen werden
    }

    @FXML
    private void handleInput() {
        String input = inputField.getText();
        String output = model.evaluate(input);
        outputField.setText(output);
    }

}
