package com.example.javafx.config;

import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class ConfigView extends BorderPane {

    public final TreeView<String> treeView = new TreeView<>();

    public ConfigView() {
        treeView.setShowRoot(false);
        treeView.setEditable(true);

        setCenter(treeView);
    }

    public void buildStage(Stage stage) {
        stage.setTitle("Konfiguration");
        stage.setScene(new Scene(this, 700, 800));
        stage.show();
    }

}
