package com.example.javafx.colors;

import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;

public class ColorApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Controller controller = new Controller();
        Parent root = controller.getRoot();

        primaryStage.setScene(new Scene(root, 400, 100));
        primaryStage.setTitle("Farbwahl");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
