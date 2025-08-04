package com.example.javafx.zahlenschloss;

import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;

public class ZahlenschlossController {

    private final ZahlenschlossModel model;
    private final ZahlenschlossView view;

    public ZahlenschlossController(ZahlenschlossModel model, ZahlenschlossView view) {
        this.model = model;
        this.view = view;

        for (Zahl zahl : Zahl.values()) {
            Button button = new Button(zahl.toString());
            button.setOnAction(event -> click(zahl));
            view.getChildren().add(button);
        }

        updateBackground(LockState.WAITING);
    }

    private void click(Zahl zahl) {
        LockState state = model.play(zahl);
        updateBackground(state);
    }

    private void updateBackground(LockState state) {
        Color fxColor = switch (state) {
            case OPEN -> Color.GREEN;
            case CLOSED -> Color.RED;
            case WAITING -> Color.LIGHTGRAY;
        };

        view.setBackground(new Background(new BackgroundFill(fxColor, CornerRadii.EMPTY, Insets.EMPTY)));
    }

}
