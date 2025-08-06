package com.example.javafx.config;

import com.example.javafx.config.model.*;
import javafx.application.*;
import javafx.stage.*;

public class ConfigApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        var model = new ConfigModel();
        var view = new ConfigView();
        var controller = new ConfigController(model, view);
        controller.view.buildStage(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
