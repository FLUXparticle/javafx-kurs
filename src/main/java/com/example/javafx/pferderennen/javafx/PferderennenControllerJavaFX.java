package com.example.javafx.pferderennen.javafx;

import com.example.javafx.pferderennen.model.*;
import javafx.animation.*;
import javafx.application.*;
import javafx.scene.shape.*;

import static javafx.util.Duration.*;

public class PferderennenControllerJavaFX implements Anzeige {

    PferderennenModel model;
    PferderennenViewJavaFX view;

    public PferderennenControllerJavaFX() {
        this.model = new PferderennenModel(this);
        this.view = new PferderennenViewJavaFX(model);

        view.startButton.setOnAction(event -> model.starteRennen());
    }

    @Override
    public void rennenAnzeigen(Pferd[] pferde) {
        Platform.runLater(() -> {
            for (int i = 0; i < pferde.length; i++) {
                Rectangle pferd = view.pferde[i];
                double zielX = pferde[i].getMeter() / 2.0;

                TranslateTransition transition = new TranslateTransition(millis(100), pferd);
                transition.setToX(zielX);
                transition.play();
            }
        });
    }
}