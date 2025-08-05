package com.example.javafx.pferderennen.javafx;

import com.example.javafx.pferderennen.model.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.stage.*;

public class PferderennenViewJavaFX extends VBox {

    final Pane rennbahn = new Pane();
    final Rectangle[] pferde;
    final Button startButton;

    public PferderennenViewJavaFX(PferderennenModel model) {
        setSpacing(10);
        setPadding(new Insets(10));
        setAlignment(Pos.CENTER);

        rennbahn.setPrefSize(580, 340);
        rennbahn.setStyle("-fx-background-color: gray");
        getChildren().add(rennbahn);

        Pferd[] modelPferde = model.getPferde();
        pferde = new Rectangle[modelPferde.length];

        for (int i = 0; i < pferde.length; i++) {
            pferde[i] = new Rectangle(80, 30, Color.BLUE);
            pferde[i].setY(i * 40);
            rennbahn.getChildren().add(pferde[i]);
        }

        startButton = new Button("Lauf!");
        getChildren().add(startButton);
    }

    public void buildStage(Stage stage) {
        stage.setTitle("Pferderennen FX");
        stage.setScene(new Scene(this));
        stage.show();
    }

}
