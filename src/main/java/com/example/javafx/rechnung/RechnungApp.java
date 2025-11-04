package com.example.javafx.rechnung;

import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;

public class RechnungApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        var controller = new RechnungController();
        Parent root = controller.getRoot();

        primaryStage.setTitle("Rechnungsverwaltung");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
