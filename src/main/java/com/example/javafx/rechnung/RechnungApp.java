package com.example.javafx.rechnung;

import javafx.application.*;
import javafx.stage.*;

public class RechnungApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        var controller = new RechnungController();
        controller.view.buildStage(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
