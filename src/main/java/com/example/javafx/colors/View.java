package com.example.javafx.colors;

import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class View extends FlowPane {

    public final Button[] buttons;

    public View() {
        setHgap(10); setVgap(10);
        setPadding(new Insets(10));

        Farbe[] farben = Farbe.values();
        buttons = new Button[farben.length];

        for (int i = 0; i < farben.length; i++) {
            Farbe farbe = farben[i];
            Button button = new Button(farbe.toString());
            button.setUserData(farbe.name());
            buttons[i] = button;
            getChildren().add(button);
        }
    }

    public void buildStage(Stage stage) {
        stage.setScene(new Scene(this, 400, 100));
        stage.setTitle("Farbwahl");
        stage.show();
    }

}
