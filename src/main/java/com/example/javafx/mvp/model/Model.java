package com.example.javafx.mvp.model;

public class Model {

    private static final String GEHEIM = "Geheim";

    private int counter;

    private final Presenter presenter;

    public Model(Presenter presenter) {
        this.presenter = presenter;
        this.counter = 0;
        presenter.onInput(this::play);
    }

    public void play() {
        String input = presenter.getInput();
        counter++;
        if (input.equals(GEHEIM)) {
            presenter.setOutput("Richtig!");
        } else if (counter < 3) {
            presenter.setOutput("Falsch");
        } else {
            presenter.setOutput("Verloren");
        }
    }

}
