package com.example.javafx.geheim;

public class Model {

    private static final String GEHEIM = "Geheim";

    private int counter;

    public Model() {
        counter = 0;
    }

    public String play(String input) {
        counter++;
        if (input.equals(GEHEIM)) {
            return "Richtig!";
        } else if (counter < 3) {
            return "Falsch";
        } else {
            return "Verloren";
        }
    }

}
