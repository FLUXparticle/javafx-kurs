package com.example.javafx.cocktails.model;

import java.util.*;

public record Cocktails(List<Ingredient> zutaten, List<Rezept> rezepte) {

    public String getZutatName(int zutatId) {
        // TODO
        return null;
    }

}
