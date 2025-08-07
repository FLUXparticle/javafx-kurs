package com.example.javafx.cocktails;

import javafx.scene.control.*;
import javafx.scene.layout.*;

public class AsyncListView<T> extends StackPane {

    private final ListView<T> listView = new ListView<>();

    private final ProgressIndicator progressIndicator = new ProgressIndicator();

    public AsyncListView() {
        getChildren().addAll(listView, progressIndicator);
    }

    public ListView<T> getListView() {
        return listView;
    }

    public ProgressIndicator getProgressIndicator() {
        return progressIndicator;
    }

}
