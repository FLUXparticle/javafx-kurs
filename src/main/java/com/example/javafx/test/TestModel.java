package com.example.javafx.test;

public class TestModel {

    private static final String SECRET = "Geheim";

    private int attempts;

    public TestModel() {
        attempts = 0;
    }

    public String evaluate(String input) {
        attempts++;
        if (input.equals(SECRET)) {
            return "Richtig!";
        } else if (attempts < 3) {
            return "Falsch";
        } else {
            return "Verloren";
        }
    }

}
