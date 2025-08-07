package com.example.javafx.cocktails.model;

public record Ingredient(int id, String name) {

    @Override
    public String toString() {
        return name;
    }

}
