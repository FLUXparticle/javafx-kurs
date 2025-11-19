package com.example.javafx.todo;

import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;

public class ToDoApp extends Application {

    @Override
    public void start(Stage stage) {
        ToDoController controller = new ToDoController();
        Parent root = controller.getRoot();

        stage.setTitle("ToDo");
        stage.setScene(new Scene(root, 520, 420));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
