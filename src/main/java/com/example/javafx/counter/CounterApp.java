package com.example.javafx.counter;

import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;

public class CounterApp extends Application {

    @Override
    public void start(Stage stage) {
        CounterController controller = new CounterController();
        Parent root = controller.getRoot();

        Scene scene = new Scene(root, 300, 200);
        stage.setScene(scene);
        stage.setTitle("Progress Demo");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
