package com.example.javafx.mvp.model;

public interface Presenter {
    String getInput();
    void setOutput(String output);
    void onInput(Runnable handler);
}
