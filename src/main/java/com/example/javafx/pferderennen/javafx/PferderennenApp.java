package com.example.javafx.pferderennen.javafx;

import javafx.application.*;
import javafx.stage.*;

public class PferderennenApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        PferderennenControllerJavaFX controller = new PferderennenControllerJavaFX();
        controller.view.buildStage(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
