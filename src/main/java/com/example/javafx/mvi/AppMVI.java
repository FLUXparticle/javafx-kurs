package com.example.javafx.mvi;

import com.example.javafx.mvi.model.*;
import javafx.application.*;
import javafx.stage.*;

public class AppMVI extends Application {

    @Override
    public void start(Stage primaryStage) {
        Model model = new Model();
        View view = new View(model);

        view.buildStage(primaryStage);
    }

    public static void main(String[] args) {
        launch();
    }

}
