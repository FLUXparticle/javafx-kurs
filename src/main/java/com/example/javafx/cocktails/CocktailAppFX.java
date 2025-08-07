package com.example.javafx.cocktails;

import com.example.javafx.cocktails.model.*;
import javafx.application.*;
import javafx.stage.*;

public class CocktailAppFX extends Application {

    private CocktailController controller;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        CocktailModel model = new CocktailModel();
        CocktailView view = new CocktailView();
        controller = new CocktailController(model, view);

        view.build(stage);
        controller.bind();
    }

    @Override
    public void stop() {
        System.out.println("Stop!");
        BackgroundService.shutdown();
    }

}
