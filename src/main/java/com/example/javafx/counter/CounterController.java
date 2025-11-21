package com.example.javafx.counter;

import javafx.event.*;
import javafx.scene.*;

public class CounterController {

    private final CounterView view;

    public CounterController() {
        this.view = new CounterView();
        this.view.startButton.setOnAction(this::startCounting);
    }

    public Parent getRoot() {
        return view;
    }

    private void startCounting(ActionEvent event) {
        // BLOCKIERT den JavaFX-Thread komplett!
        for (int i = 1; i <= 100; i++) {
            try { Thread.sleep(50); } catch (Exception ignored) {}

            System.out.println(Thread.currentThread().getName() + ": " + i);
            view.textField.setText(String.valueOf(i));
            view.progressBar.setProgress(i / 100.0);
        }
    }

}
