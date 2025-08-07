package com.example.javafx.config;

import com.example.javafx.config.data.*;
import com.example.javafx.config.model.*;
import jakarta.xml.bind.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;

public class ConfigController {

    private final ConfigModel model;

    final ConfigView view;

    public ConfigController(ConfigModel model, ConfigView view) throws JAXBException {
        this.model = model;
        this.view = view;

        model.loadConfiguration();

        TreeItem<String> root = buildTree(model.getConfiguration());
        view.treeView.setRoot(root);
        view.treeView.setCellFactory(TextFieldTreeCell.forTreeView());
    }

    private TreeItem<String> buildTree(Configuration config) {
        TreeItem<String> root = new TreeItem<>();
        for (Api api : config.getApis()) {
            TreeItem<String> apiItem = new TreeItem<>(api.getName());
            for (Endpoint endpoint : api.getEndpoints()) {
                TreeItem<String> endpointItem = new TreeItem<>(endpoint.getPath(), new Label("Path:"));
                {
                    endpointItem.getChildren().add(new TreeItem<>(endpoint.getVerb(), new Label("Verb:")));
                    endpointItem.getChildren().add(new TreeItem<>(Boolean.toString(endpoint.isAuthRequired()), new Label("Auth:")));
                }
                apiItem.getChildren().add(endpointItem);
            }
            root.getChildren().add(apiItem);
        }
        return root;
    }

}
