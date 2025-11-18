package com.example.javafx.northwind;

import com.example.javafx.northwind.database.*;
import com.example.javafx.northwind.model.*;
import javafx.collections.*;
import javafx.event.*;
import javafx.scene.control.*;
import javafx.stage.*;

import java.sql.*;
import java.util.*;

public class WarenkorbController {

    private Database database;
    private ObservableList<CartItem> cartItems;

    public WarenkorbController(WarenkorbModel model) throws SQLException {
        database = Database.getInstance();
        cartItems = FXCollections.observableList(model.getCartItems());
    }

    public void bind(WarenkorbView view, Stage stage) {
        view.tableCart.setItems(cartItems);
        view.textInput.textProperty().addListener((obs, oldVal, newVal) -> updateProductTable(view.tableProducts, newVal));
        view.buttonToCart.setOnAction(event -> {
            Product selectedItem = view.tableProducts.getSelectionModel().getSelectedItem();
            addToCart(selectedItem, view.textAmount.getText());
        });
        view.buttonNewWindow.setOnAction(this::openNewWindow);

        this.updateProductTable(view.tableProducts, view.textInput.getText());

        view.buildStage(stage);
    }

    public void updateProductTable(TableView<Product> table, String search) {
        try {
            Collection<Product> products = database.getProductsLike(search);
            table.setItems(FXCollections.observableArrayList(products));
        } catch (Exception e) {
            showAlert("Fehler beim Laden der Produkte: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void addToCart(Product selected, String amountText) {
        if (selected == null) {
            showAlert("Bitte wähle ein Produkt aus.");
            return;
        }

        try {
            int amount = Integer.parseInt(amountText);
            if (amount <= 0) {
                showAlert("Anzahl muss positiv sein.");
                return;
            }

            cartItems.add(new CartItem(selected.getName(), amount, selected.getPrice()));
        } catch (NumberFormatException e) {
            showAlert("Ungültige Anzahl: " + amountText);
        }
    }

    private void showAlert(String msg) {
        new Alert(Alert.AlertType.ERROR, msg, ButtonType.OK).showAndWait();
    }

    private void openNewWindow(ActionEvent event) {
        WarenkorbView newView = new WarenkorbView();
        Stage newStage = new Stage();
        bind(newView, newStage);
    }

}
