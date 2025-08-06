package com.example.javafx.rechnung;

import com.example.javafx.rechnung.data.*;
import com.example.javafx.rechnung.model.*;
import jakarta.xml.bind.*;
import javafx.beans.property.*;
import javafx.collections.*;
import javafx.event.*;

public class AddressesController {

    private final AddressesModel model;
    final AddressesView view;
    private final ObservableList<Address> addresses;
    private final BooleanProperty dirty = new SimpleBooleanProperty(false);

    public AddressesController() throws JAXBException {
        model = new AddressesModel();
        view = new AddressesView();

        model.loadAddresses();

        addresses = FXCollections.observableList(model.getAddresses());

        // TODO Verbindungen herstellen
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

}
