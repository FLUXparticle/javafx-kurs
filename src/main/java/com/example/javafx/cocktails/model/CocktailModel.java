package com.example.javafx.cocktails.model;

import retrofit2.*;
import retrofit2.converter.gson.*;

import java.io.*;
import java.util.*;

public class CocktailModel {

    private final CocktailApi api;

    public CocktailModel() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://cocktails.fluxparticle.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(CocktailApi.class);
    }

    public List<Ingredient> getAllZutaten() throws IOException {
        System.out.println("Lade Zutaten...");
        List<Ingredient> ingredients = api.getIngredients().execute().body();
        System.out.println("Zutaten: " + ingredients.size());
        return ingredients;
    }

    public List<Ingredient> getFridge(String userId) throws IOException {
        System.out.println("Lade Kühlschrank...");
        List<Ingredient> fridge = api.getFridge(userId).execute().body().stream()
                .filter(FridgeIngredient::inFridge)
                .map(fridgeIngredient -> new Ingredient(fridgeIngredient.id(), fridgeIngredient.name()))
                .toList();
        System.out.println("Kühlschrank: " + fridge.size());
        return fridge;
    }

    public void updateFridge(String userId, int ingredientId, boolean inFridge) throws IOException {
        System.out.println("Update Kühlschrank (" + ingredientId + "=" + inFridge + ") ...");
        api.updateFridge(userId, ingredientId, new CocktailApi.FridgeUpdate(inFridge)).execute();
        System.out.println("Updated Kühlschrank.");
    }

    public List<RezeptOhneZutaten> getContainsRecipes(String userId) throws IOException {
        System.out.println("Lade Rezepte...");
        List<RezeptOhneZutaten> rezeptOhneZutaten = api.getContainsRecipes(userId).execute().body();
        System.out.println("Geladen Rezepte.");
        return rezeptOhneZutaten;
    }

    public List<AnweisungText> getAnweisungenFor(int cocktailID) throws IOException {
        System.out.println("Lade Anweisungen (" + cocktailID + ")...");
        List<AnweisungText> anweisungTexts = api.getCocktailDetails(cocktailID).execute().body().instructions.stream()
                .map(instruction -> new AnweisungText(instruction.toString(), instruction.ingredient.id()))
                .toList();
        System.out.println("Geladen Anweisungen (" + cocktailID + ").");
        return anweisungTexts;
    }

}
