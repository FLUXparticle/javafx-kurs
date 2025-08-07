package com.example.javafx.cocktails.model;

import org.jetbrains.annotations.*;

public record RezeptOhneZutaten(int id, String name, int missing) {

    @NotNull
    @Override
    public String toString() {
        return name + " (" + missing + ")";
    }

}
