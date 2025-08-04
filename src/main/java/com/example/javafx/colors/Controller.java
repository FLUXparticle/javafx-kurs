package com.example.javafx.colors;

import javafx.event.*;
import javafx.scene.control.*;

public class Controller {

    private final View view;

    public Controller(View view) {
        this.view = view;

        for (Button button : view.buttons) {
            button.setOnAction(this::click);
        }
    }

    private void click(ActionEvent event) {
        Button source = (Button) event.getSource();
        String name = (String) source.getUserData();
        Farbe farbe = Farbe.valueOf(name);
        System.out.println("Gewählt: " + farbe);
    }

}
