package com.example.javafx.zahlenschloss;

import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class ZahlenschlossView extends FlowPane {

    private final ZahlenschlossModel model = new ZahlenschlossModel();

    public ZahlenschlossView() {
        setPadding(new Insets(10));
        setHgap(10);
        setVgap(10);
        setAlignment(Pos.TOP_CENTER);
    }

    public void buildStage(Stage stage) {
        Scene scene = new Scene(this, 130, 200);
        stage.setTitle("Zahlenschloss");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

}
