package com.example.javafx.northwind;

import com.example.javafx.northwind.model.*;
import javafx.application.*;
import javafx.stage.*;

public class WarenkorbFX  extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        WarenkorbModel model = new WarenkorbModel();
        WarenkorbViewPage view = new WarenkorbViewPage();
        WarenkorbController controller = new WarenkorbController(model);
        controller.bind(view, primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
