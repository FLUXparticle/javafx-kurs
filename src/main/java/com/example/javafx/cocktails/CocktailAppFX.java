package com.example.javafx.cocktails;

import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;

public class CocktailAppFX extends Application {

    private CocktailController controller;

    @Override
    public void start(Stage stage) {
        controller = new CocktailController();
        Parent root = controller.getRoot();

        stage.setScene(new Scene(root, 800, 600));
        stage.setTitle("Cocktails");
        stage.show();
    }

    @Override
    public void stop() {
        System.out.println("Stop!");
        controller.shutdown();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
