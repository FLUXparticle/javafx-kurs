package com.example.javafx.rechnung;

import com.example.javafx.rechnung.data.*;
import com.example.javafx.rechnung.model.*;
import jakarta.xml.bind.*;
import javafx.beans.property.*;
import javafx.collections.*;
import javafx.event.*;
import javafx.scene.control.*;
import javafx.scene.*;

public class AddressesController {

    private final AddressesModel model;
    private final AddressesView view;
    private final ObservableList<Address> addresses;
    private final BooleanProperty dirty = new SimpleBooleanProperty(false);

    public AddressesController() throws JAXBException {
        model = new AddressesModel();
        view = new AddressesView();

        model.loadAddresses();

        addresses = FXCollections.observableList(model.getAddresses());
        view.tableView.setItems(addresses);

        for (TableColumn<Address, String> column : view.columns) {
            var onEditCommit = column.getOnEditCommit();
            column.setOnEditCommit(event -> {
                onEditCommit.handle(event);
                dirty.set(true);
            });
        }

        dirty.addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                view.statusLabel.setText("");
            }
        });

        // "+" Button
        view.addButton.setOnAction(this::add);

        // "-" Button
        view.removeButton.setOnAction(this::remove);

        // Enable "-" only if something is selected
        view.removeButton.disableProperty().bind(
            view.tableView.getSelectionModel().selectedItemProperty().isNull()
        );

        // "Speichern"-Button
        view.saveButton.setOnAction(this::save);

        // Enable "Speichern" only if something was changed
        view.saveButton.disableProperty().bind(dirty.not());
    }

    private void add(ActionEvent event) {
        Address empty = new Address();
        addresses.add(empty);
        dirty.set(true);
    }

    private void remove(ActionEvent event) {
        Address selected = view.tableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            addresses.remove(selected);
            dirty.set(true);
        }
    }

    private void save(ActionEvent event) {
        try {
            model.saveAddresses();
            dirty.set(false);
            view.statusLabel.setText("Gespeichert.");
        } catch (JAXBException ex) {
            view.statusLabel.setText("Fehler beim Speichern: " + ex.getMessage());
        }
    }

    public Parent getRoot() {
        return view;
    }

}
