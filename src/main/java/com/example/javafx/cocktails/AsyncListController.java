package com.example.javafx.cocktails;

import javafx.application.*;
import javafx.collections.*;

import java.util.*;
import java.util.concurrent.*;

public class AsyncListController<T> {

    private final ObservableList<T> list = FXCollections.observableArrayList();

    private final AsyncListView<T> view;

    public AsyncListController(AsyncListView<T> view) {
        this.view = view;
        view.getListView().setItems(list);
        view.getProgressIndicator().setVisible(false);
    }

    public void setItemsAsync(Callable<List<T>> supplier) {
        view.getProgressIndicator().setVisible(true);
        list.clear();
        BackgroundService.runInBackground(() -> {
            try {
                List<T> data = supplier.call();
                Platform.runLater(() -> {
                    list.setAll(data);
                    view.getProgressIndicator().setVisible(false);
                });
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public ObservableList<T> getList() {
        return list;
    }

}
