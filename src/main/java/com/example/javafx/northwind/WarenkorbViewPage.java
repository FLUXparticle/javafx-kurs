package com.example.javafx.northwind;

import com.example.javafx.northwind.database.*;
import com.example.javafx.northwind.model.*;
import com.tobiasdiez.easybind.*;
import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.util.*;
import java.util.stream.*;

public class WarenkorbViewPage extends VBox {

    private static final int PAGE_SIZE = 10;
    TableView<Product> tableProducts;

    TextField textInput;
    TextField textAmount;
    TableView<CartItem> tableCart;
    Button buttonToCart;
    Button buttonNewWindow;
    ObservableList<Product> products = FXCollections.observableArrayList();

    public WarenkorbViewPage() {
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
//        getChildren().add(tableProducts);


        Pagination pagination = new Pagination();
        EasyBinding<Long> sizeProperty = EasyBind.wrapList(products).reduce(Stream::count);
        pagination.pageCountProperty().bind(sizeProperty.map(size -> {
            System.out.println("size = " + size);
            return (size - 1) / PAGE_SIZE + 1;
        }));
        pagination.setPageFactory(pageIndex -> {
            System.out.println("pageIndex = " + pageIndex);
            int from = pageIndex * PAGE_SIZE;

            tableProducts = getProductTableView();

            // Falls die Liste kürzer wird als eine Seite
            EasyBinding<ObservableList<Product>> listEasyBinding = EasyBind.wrapList(products)
                    .reduce((Stream<? extends Product> stream) -> {
                        List<Product> list = stream
                                .skip(from)
                                .limit(PAGE_SIZE)
                                .map(p -> (Product) p) // Eigentlich unnötig, aber ohne funktioniert es nicht
                                .toList();
                        return FXCollections.observableList(list);
                    });
            tableProducts.itemsProperty().bind(listEasyBinding);

            return tableProducts;
        });
        getChildren().add(pagination);

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

    private static TableView<Product> getProductTableView() {
        TableView<Product> tableProducts = new TableView<>();

        TableColumn<Product, String> colName = new TableColumn<>("Name");
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colName.setPrefWidth(200);

        TableColumn<Product, Double> colPrice = new TableColumn<>("Preis");
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colPrice.setPrefWidth(100);

        tableProducts.getColumns().addAll(colName, colPrice);
        return tableProducts;
    }

    public void buildStage(Stage stage) {
        stage.setTitle("JavaFX Warenkorb");
        stage.setScene(new Scene(this, 400, 600));
        stage.show();

        textInput.requestFocus();
    }

}
