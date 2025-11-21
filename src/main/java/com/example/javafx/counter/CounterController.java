package com.example.javafx.counter;

import com.tobiasdiez.easybind.*;
import javafx.concurrent.*;
import javafx.event.*;
import javafx.scene.*;

public class CounterController {

    private final CounterView view;

    private final Service<Void> service = new Service<>() {
        @Override
        protected Task<Void> createTask() {
            return new CounterTask();
        }
    };

    public CounterController() {
        this.view = new CounterView();
        this.view.startButton.setOnAction(this::startCounting);

        EasyBinding<Double> vProgress = EasyBind.map(service.progressProperty(), progress -> Math.max(0, progress.doubleValue()));
        view.progressBar.progressProperty().bind(vProgress);
        view.textField.textProperty().bind(service.messageProperty());
    }

    public Parent getRoot() {
        return view;
    }

    private void startCounting(ActionEvent event) {
        service.restart();
    }

    private static class CounterTask extends Task<Void> {
        @Override
        protected Void call() {
            try {
                for (int i = 1; i <= 100; i++) {
                    Thread.sleep(50);
                    System.out.println(Thread.currentThread().getName() + ": " + i);
                    updateMessage(String.valueOf(i));
                    updateProgress(i, 100);
                    if (Thread.interrupted()) throw new InterruptedException();
                }
            } catch (InterruptedException e) {
                System.out.println("Interrupted!");
            }
            return null;
        }
    }

}
