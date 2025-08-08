package com.example.javafx.mvi.model;

import javafx.beans.property.*;

public class Model {

    private static final String GEHEIM = "Geheim";

    private int counter;

    private final ObjectProperty<ViewState> state = new SimpleObjectProperty<>(new ViewState(""));

    public Model() {
        this.counter = 0;
    }

    public ObjectProperty<ViewState> stateProperty() {
        return state;
    }

    public ViewState getState() {
        return state.get();
    }

    public void dispatch(Intent intent) {
        if (intent instanceof Intent.Submit submit) {
            String input = submit.input();
            counter++;
            String out;
            if (GEHEIM.equals(input)) {
                out = "Richtig!";
            } else if (counter < 3) {
                out = "Falsch";
            } else {
                out = "Verloren";
            }
            state.set(new ViewState(out));
        }
    }
}
