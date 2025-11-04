package com.example.javafx.rechnung;

import com.example.javafx.rechnung.data.*;
import javafx.beans.property.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.layout.*;

import java.util.function.*;

public class AddressesView extends VBox {

    final TableView<Address> tableView;
    final Label statusLabel;

    public AddressesView() {
        setPadding(new Insets(10));
        setSpacing(10);

        tableView = new TableView<>();
        tableView.setEditable(true);
        getChildren().addAll(tableView);

        statusLabel = new Label();
        statusLabel.setPadding(new Insets(5, 0, 0, 0));
        getChildren().addAll(statusLabel);
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
