package com.example.javafx.geheim;

import javafx.event.*;
import javafx.scene.*;

public class Controller {

    private final Model model;
    private final View view;

    public Controller() {
        this.model = new Model();
        this.view = new View();

        view.tfInput.setOnAction(this::handleInput);
    }

    private void handleInput(ActionEvent event) {
        String input = view.tfInput.getText();
        String output = model.play(input);
        view.tfOutput.setText(output);
    }

    public Parent getRoot() {
        return view;
    }

}
