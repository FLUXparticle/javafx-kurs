package com.example.javafx.zahlenschloss;

import javafx.application.*;
import javafx.stage.*;

public class ZahlenschlossApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        ZahlenschlossModel model = new ZahlenschlossModel();
        ZahlenschlossView view = new ZahlenschlossView();
        new ZahlenschlossController(model, view);

        view.buildStage(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
