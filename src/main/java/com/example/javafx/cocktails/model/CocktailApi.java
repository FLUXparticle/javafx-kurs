package com.example.javafx.cocktails.model;

import retrofit2.*;
import retrofit2.http.*;

import java.util.*;

public interface CocktailApi {

    // 5. Alle Cocktails
    @GET("/api/cocktails")
    Call<List<CocktailSummary>> getCocktailList();

    // 6. Detail-Abruf eines Cocktails
    @GET("/api/cocktails/{cocktailId}")
    Call<CocktailDetails> getCocktailDetails(
            @Path("cocktailId") int cocktailId
    );

    // 1. Zutaten laden
    @GET("/api/ingredients")
    Call<List<Ingredient>> getIngredients();

    // 2. Fridge laden
    @GET("/api/user/{userId}/fridge")
    Call<List<FridgeIngredient>> getFridge(
        @Path("userId") String userId
    );

    // 3. Fridge aktualisieren (hinzufügen oder entfernen)
    // Hier als PATCH mit einfachem Body-Flag
    class FridgeUpdate {
        public boolean inFridge;
        public FridgeUpdate(boolean inFridge) { this.inFridge = inFridge; }
    }
    @PATCH("/api/user/{userId}/fridge/{ingredientId}")
    Call<Void> updateFridge(
        @Path("userId") String userId,
        @Path("ingredientId") int ingredientId,
        @Body FridgeUpdate update
    );

    // 4. Mögliche Rezepte für aktuellen Kühlschrank
    @GET("/api/user/{userId}/possible")
    Call<List<RezeptOhneZutaten>> getPossibleRecipes(
            @Path("userId") String userId
    );

    @GET("/api/user/{userId}/contains")
    Call<List<RezeptOhneZutaten>> getContainsRecipes(
            @Path("userId") String userId
    );

    // 7. Freitextsuche (z.B. nach Cocktail-Name)
    @GET("/api/search")
    Call<List<CocktailSummary>> searchCocktails(
        @Query("q") String query
    );

    // (Optional) 8. Einkaufsliste
    @GET("/api/user/{userId}/shopping")
    Call<List<Ingredient>> getShoppingList(
        @Path("userId") String userId
    );


    // DTO-Klassen (Beispiele; musst du an dein JSON anpassen):
    class CocktailSummary {
        public int id;
        public String name;
    }
    class CocktailDetails {
        public int id;
        public String name;
        public List<Instruction> instructions;
    }
    class Instruction {
        public int amount;
        public Ingredient ingredient;
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();

            if (amount > 0) {
                sb.append(amount);
                sb.append("cl ");
            }
            sb.append(ingredient.name());

            return sb.toString();
        }
    }
}
