package com.example.javafx.geheim;

import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        Controller controller = new Controller();
        Parent root = controller.getRoot();

        primaryStage.setTitle("Passwort");
        primaryStage.setScene(new Scene(root, 400, 150));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
