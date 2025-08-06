package com.example.javafx.northwind;

import com.example.javafx.northwind.database.*;
import com.example.javafx.northwind.model.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class WarenkorbView extends VBox {

    TableView<Product> tableProducts;
    TextField textInput;
    TextField textAmount;
    TableView<CartItem> tableCart;
    Button buttonToCart;
    Button buttonNewWindow;

    public WarenkorbView() {
        setSpacing(10);
        setPadding(new Insets(10));

        // Suchfeld
        HBox searchBox = new HBox(10);
        searchBox.getChildren().add(new Label("Suche:"));

        textInput = new TextField();
        textInput.setPrefColumnCount(15);
        searchBox.getChildren().add(textInput);
        getChildren().add(searchBox);

        // Produkttabelle
        tableProducts = new TableView<>();
        TableColumn<Product, String> colName = new TableColumn<>("Name");
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colName.setPrefWidth(200);

        TableColumn<Product, Double> colPrice = new TableColumn<>("Preis");
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colPrice.setPrefWidth(100);

        tableProducts.getColumns().addAll(colName, colPrice);
        getChildren().add(tableProducts);

        // Panel mit Anzahl + Button
        HBox inputBox = new HBox(10);
        inputBox.getChildren().add(new Label("Anzahl:"));

        textAmount = new TextField();
        textAmount.setPrefColumnCount(3);
        inputBox.getChildren().add(textAmount);

        buttonToCart = new Button("In Warenkorb");
        inputBox.getChildren().add(buttonToCart);
        getChildren().add(inputBox);

        // Warenkorb-Tabelle
        tableCart = new TableView<>();
        TableColumn<CartItem, String> colCartName = new TableColumn<>("Name");
        colCartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCartName.setPrefWidth(200);
        tableCart.getColumns().add(colCartName);

        TableColumn<CartItem, Integer> colCartAmount = new TableColumn<>("Anzahl");
        colCartAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colCartAmount.setPrefWidth(80);
        tableCart.getColumns().add(colCartAmount);

        TableColumn<CartItem, String> colCartPrice = new TableColumn<>("Preis");
        colCartPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        colCartPrice.setPrefWidth(100);
        tableCart.getColumns().add(colCartPrice);

        getChildren().add(tableCart);

        // Button für neues Fenster
        buttonNewWindow = new Button("Neues Fenster");
        getChildren().add(buttonNewWindow);
    }

    public void buildStage(Stage stage) {
        stage.setTitle("JavaFX Warenkorb");
        stage.setScene(new Scene(this, 400, 600));
        stage.show();

        textInput.requestFocus();
    }

}
