package com.example.javafx.rechnung;

import com.example.javafx.rechnung.data.*;
import javafx.beans.property.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.layout.*;

import java.util.*;
import java.util.function.*;

public class AddressesView extends VBox {

    final TableView<Address> tableView;
    final Button addButton;
    final Button removeButton;
    final Button saveButton;
    final Label statusLabel;
    final List<TableColumn<Address, String>> columns;

    public AddressesView() {
        setPadding(new Insets(10));
        setSpacing(10);

        tableView = new TableView<>();
        tableView.setEditable(true);

        columns = List.of(
                column("ID", Address::getId, Address::setId),
                column("Name", Address::getName, Address::setName),
                column("Straße", Address::getStreet, Address::setStreet),
                column("Stadt", Address::getCity, Address::setCity),
                column("USt-ID", Address::getVatId, Address::setVatId)
        );
        tableView.getColumns().addAll(columns);

        addButton = new Button("+");
        removeButton = new Button("-");
        saveButton = new Button("Speichern");

        statusLabel = new Label();
        statusLabel.setPadding(new Insets(5, 0, 0, 0));

        HBox buttonBar = new HBox(10, addButton, removeButton, saveButton);
        buttonBar.setPadding(new Insets(10));
        buttonBar.setAlignment(Pos.CENTER_RIGHT);

        getChildren().addAll(tableView, buttonBar, statusLabel);
    }

    private static TableColumn<Address, String> column(String name, Function<Address, String> getter, BiConsumer<Address, String> setter) {
        TableColumn<Address, String> column = new TableColumn<>(name);
        column.setCellFactory(TextFieldTableCell.forTableColumn());
        column.setCellValueFactory(param -> {
            Address address = param.getValue();
            String value = getter.apply(address);
            return new ReadOnlyObjectWrapper<>(value);
        });
        column.setOnEditCommit(event -> {
            Address address = event.getRowValue();
            String value = event.getNewValue();
            setter.accept(address, value);
        });
        return column;
    }

}
