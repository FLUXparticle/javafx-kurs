package com.example.javafx.colors;

import javafx.application.*;
import javafx.stage.*;

public class ColorApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        View view = new View();
        new Controller(view);

        view.buildStage(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
