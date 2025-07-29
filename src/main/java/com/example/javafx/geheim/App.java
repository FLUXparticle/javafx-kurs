package com.example.javafx.geheim;

import javafx.application.*;
import javafx.stage.*;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        Model model = new Model();
        View view = new View();
        new Controller(view, model);

        view.buildStage(primaryStage);
    }

    public static void main(String[] args) {
        launch();
    }

}
