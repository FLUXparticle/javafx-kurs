package com.example.javafx.rechnung;

import com.example.javafx.rechnung.data.*;
import javafx.beans.property.*;
import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.layout.*;
import javafx.util.*;
import javafx.util.converter.*;

import java.util.function.*;

public class InvoiceView extends VBox {

    // UI-Elemente
    final ComboBox<String> typeComboBox;
    final ComboBox<String> companyComboBox;
    final DatePicker datePicker;
    final ListView<String> linesListView;
    final Button addLineButton;
    final Button removeLineButton;
    final TableView<Position> positionsTable;
    final Button addPositionButton;
    final Button removePositionButton;
    final Button saveButton;
    final Label statusLabel;
    final TableColumn<Position, String> colText;
    final TableColumn<Position, Double> colPrice;
    final TableColumn<Position, Double> colTaxRate;

    private static final ObservableList<Double> DEFAULT_TAX_RATES =
        FXCollections.observableArrayList(0.0, 7.0, 19.0);

    public InvoiceView() {
        setPadding(new Insets(10));
        setSpacing(10);

        // --- Kopfbereich: Type, Company, Datum ---
        typeComboBox = new ComboBox<>(FXCollections.observableArrayList("online", "präsenz"));
        typeComboBox.setPromptText("Typ");

        companyComboBox = new ComboBox<>(FXCollections.observableArrayList("companyA", "companyB"));
        companyComboBox.setPromptText("Firma");

        datePicker = new DatePicker();
        datePicker.setPromptText("Datum");

        HBox topRow = new HBox(10, new Label("Typ:"), typeComboBox, new Label("Firma:"), companyComboBox, new Label("Datum:"), datePicker);
        topRow.setAlignment(Pos.CENTER_LEFT);

        // --- Kursbeschreibung (ListView mit + / - Buttons) ---
        linesListView = new ListView<>();
        linesListView.setEditable(true);
        linesListView.setCellFactory(TextFieldListCell.forListView());

        addLineButton = new Button("+");
        removeLineButton = new Button("-");
        HBox lineButtons = new HBox(5, addLineButton, removeLineButton);
        lineButtons.setAlignment(Pos.CENTER_LEFT);

        VBox linesBox = new VBox(5, new Label("Kursbeschreibung:"), linesListView, lineButtons);
        linesBox.setPrefHeight(150);

        // --- Positionen (Tabelle) ---
        positionsTable = new TableView<>();
        positionsTable.setEditable(true);

        colText = column("Text", 300, new DefaultStringConverter(), Position::getText, Position::setText);
        positionsTable.getColumns().add(colText);
        colPrice = column("Preis", 100, new DoubleStringConverter(), Position::getPrice, Position::setPrice);
        positionsTable.getColumns().add(colPrice);

        colTaxRate = comboColumn("Steuersatz (%)", 140, DEFAULT_TAX_RATES, Position::getTaxRate, Position::setTaxRate);
        positionsTable.getColumns().add(colTaxRate);

        addPositionButton = new Button("+");
        removePositionButton = new Button("-");
        HBox positionButtons = new HBox(5, addPositionButton, removePositionButton);
        positionButtons.setAlignment(Pos.CENTER_LEFT);

        // --- Buttons & Status ---
        saveButton = new Button("Speichern");
        statusLabel = new Label();
        statusLabel.setPadding(new Insets(5, 0, 0, 0));

        HBox bottomBar = new HBox(10, saveButton);
        bottomBar.setAlignment(Pos.CENTER_RIGHT);

        getChildren().addAll(topRow, linesBox, new Label("Positionen:"), positionsTable, positionButtons, bottomBar, statusLabel);
        VBox.setVgrow(linesBox, Priority.ALWAYS);
        VBox.setVgrow(positionsTable, Priority.ALWAYS);
    }

    private static <T> TableColumn<Position, T> column(String name, double width, StringConverter<T> converter, Function<Position, T> getter, BiConsumer<Position, T> setter) {
        TableColumn<Position, T> column = new TableColumn<>(name);
        column.setPrefWidth(width);
        column.setCellFactory(TextFieldTableCell.forTableColumn(converter));
        column.setCellValueFactory(param -> {
            Position position = param.getValue();
            T value = getter.apply(position);
            return new ReadOnlyObjectWrapper<>(value);
        });
        column.setOnEditCommit(event -> {
            Position position = event.getRowValue();
            T value = event.getNewValue();
            setter.accept(position, value);
        });
        return column;
    }

    private static <T> TableColumn<Position, T> comboColumn(String name, double width, ObservableList<T> choices,
                                                            Function<Position, T> getter, BiConsumer<Position, T> setter) {
        TableColumn<Position, T> column = new TableColumn<>(name);
        column.setPrefWidth(width);
        column.setCellFactory(ComboBoxTableCell.forTableColumn(choices));
        column.setCellValueFactory(param -> {
            Position position = param.getValue();
            T value = getter.apply(position);
            if (value == null && !choices.isEmpty()) {
                value = choices.get(0);
                setter.accept(position, value);
            }
            return new ReadOnlyObjectWrapper<>(value);
        });
        column.setOnEditCommit(event -> {
            Position position = event.getRowValue();
            T value = event.getNewValue();
            setter.accept(position, value);
        });
        return column;
    }

}
