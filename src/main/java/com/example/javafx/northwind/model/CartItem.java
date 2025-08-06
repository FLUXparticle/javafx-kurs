package com.example.javafx.northwind.model;

// --- Hilfsklasse für den Warenkorb ---
public class CartItem {

    private final String name;
    private final int amount;
    private final String totalPrice;

    public CartItem(String name, int amount, double pricePerUnit) {
        this.name = name;
        this.amount = amount;
        this.totalPrice = String.format("%.2f", amount * pricePerUnit);
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

}
