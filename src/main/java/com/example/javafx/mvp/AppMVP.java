package com.example.javafx.mvp;

import com.example.javafx.mvp.model.*;
import javafx.application.*;
import javafx.stage.*;

public class AppMVP extends Application {

    @Override
    public void start(Stage primaryStage) {
        View view = new View();
        new Model(view);

        view.buildStage(primaryStage);
    }

    public static void main(String[] args) {
        launch();
    }

}
