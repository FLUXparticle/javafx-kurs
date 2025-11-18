package com.example.javafx.mvvm.model;

public class Model {

    private static final String GEHEIM = "Geheim";

    private int counter = 0;

    public String play(String input) {
        counter++;
        if (GEHEIM.equals(input)) {
            return "Richtig!";
        } else if (counter < 3) {
            return "Falsch";
        } else {
            return "Verloren";
        }
    }

}
