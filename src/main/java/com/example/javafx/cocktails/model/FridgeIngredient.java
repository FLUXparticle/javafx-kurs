package com.example.javafx.cocktails.model;

public record FridgeIngredient(int id, String name, boolean inFridge) {

    @Override
    public String toString() {
        return name;
    }

}
