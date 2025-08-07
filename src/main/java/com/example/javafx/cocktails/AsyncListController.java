package com.example.javafx.cocktails;

import javafx.collections.*;
import javafx.concurrent.*;

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
        // clear and show indicator
        list.clear();
        Task<List<T>> task = new Task<>() {
            @Override
            protected List<T> call() throws Exception {
                return supplier.call();
            }
        };
        view.getProgressIndicator().visibleProperty().bind(task.runningProperty());

        task.setOnSucceeded(event -> {
            list.setAll(task.getValue());
        });
        task.setOnFailed(event -> {
            Throwable exception = event.getSource().getException();
            // optionally handle error: e.g. log or show alert
            exception.printStackTrace();
        });

        BackgroundService.runInBackground(task);
    }

    public ObservableList<T> getList() {
        return list;
    }

}
