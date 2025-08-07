package com.example.javafx.cocktails.model;

public record AnweisungText(String text, int zutatId) {

    @Override
    public String toString() {
        return text;
    }

}
