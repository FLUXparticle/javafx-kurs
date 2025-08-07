package com.example.javafx.cocktails;

import javafx.collections.*;
import javafx.concurrent.*;

import java.util.*;
import java.util.concurrent.*;

public class AsyncListController<T> {

    private final ObservableList<T> list = FXCollections.observableArrayList();

    private final AsyncListView<T> view;

    private final Service<List<T>> service;

    public AsyncListController(AsyncListView<T> view, Callable<List<T>> supplier) {
        this.view = view;

        service = new Service<>() {
            @Override
            protected Task<List<T>> createTask() {
                return new Task<>() {
                    @Override
                    protected List<T> call() throws Exception {
                        return supplier.call();
                    }
                };
            }
        };

        service.setOnSucceeded(event -> {
            List<T> result = service.getValue();
            list.setAll(result);
        });
        service.setOnFailed(event -> {
            Throwable exception = event.getSource().getException();
            // optionally handle error: e.g. log or show alert
            exception.printStackTrace();
        });

        view.getListView().setItems(list);
        view.getProgressIndicator().visibleProperty().bind(service.runningProperty());
    }

    public void update() {
        list.clear();
        service.restart();
    }

    public ObservableList<T> getList() {
        return list;
    }

}
