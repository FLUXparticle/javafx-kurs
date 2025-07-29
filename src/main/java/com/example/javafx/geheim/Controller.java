package com.example.javafx.geheim;

import javafx.event.*;

public class Controller {

    private final Model model;
    private final View view;

    public Controller(View view, Model model) {
        this.model = model;
        this.view = view;

        view.tfInput.setOnAction(this::handleInput);
    }

    private void handleInput(ActionEvent event) {
        String input = view.tfInput.getText();
        String output = model.play(input);
        view.tfOutput.setText(output);
    }

}
