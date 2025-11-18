package com.example.javafx.rechnung;

import com.example.javafx.rechnung.data.*;
import com.example.javafx.rechnung.model.*;
import jakarta.xml.bind.*;
import javafx.beans.property.*;
import javafx.collections.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;

import java.io.*;
import java.time.*;
import java.time.format.*;

public class InvoiceController {

    private final InvoiceModel model;
    private final InvoiceView view;
    private final ObservableList<Position> positions;
    private final ObservableList<String> lines;
    private final BooleanProperty dirty = new SimpleBooleanProperty(false);

    public InvoiceController(File file) throws JAXBException {
        model = new InvoiceModel(file);
        view = new InvoiceView();

        model.loadInvoice();

        Invoice invoice = model.getInvoice();
        positions = FXCollections.observableList(invoice.getPositions());

        // Datum-Format vorbereiten
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        // Kopf-Daten initialisieren
        view.typeComboBox.setValue(invoice.getCourse().getType());
        view.companyComboBox.setValue(invoice.getCompany());
        view.datePicker.setValue(LocalDate.parse(invoice.getDate(), dtf));

        // Kurs-Beschreibungsliste
        lines = FXCollections.observableList(invoice.getCourse().getLines());
        view.linesListView.setItems(lines);

        // Zeilen hinzufügen/löschen konfigurieren
        view.addLineButton.setOnAction(e -> {
            lines.add("");
            dirty.set(true);
        });
        view.removeLineButton.setOnAction(e -> {
            String sel = view.linesListView.getSelectionModel().getSelectedItem();
            if (sel != null) {
                lines.remove(sel);
                dirty.set(true);
            }
        });
        view.removeLineButton.disableProperty().bind(
            view.linesListView.getSelectionModel().selectedItemProperty().isNull()
        );
        // Änderungen in der Liste beobachten
        lines.addListener((ListChangeListener<String>) change -> dirty.set(true));

        // Positionen-Tabelle befüllen und Edit-Commit-Handler anpassen
        view.positionsTable.setItems(positions);

        setOnCommit(view.colText);
        setOnCommit(view.colPrice);

        // Buttons für Positionen hinzufügen/löschen konfigurieren
        view.addPositionButton.setOnAction(e -> {
            positions.add(new Position());
            dirty.set(true);
        });
        view.removePositionButton.setOnAction(e -> {
            Position selected = view.positionsTable.getSelectionModel().getSelectedItem();
            if (selected != null) {
                positions.remove(selected);
                dirty.set(true);
            }
        });
        view.removePositionButton.disableProperty().bind(
            view.positionsTable.getSelectionModel().selectedItemProperty().isNull()
        );

        // Änderungen an Kopf-Feldern tracken
        view.typeComboBox.valueProperty().addListener((obs, o, n) -> {
            invoice.getCourse().setType(n);
            dirty.set(true);
        });
        view.companyComboBox.valueProperty().addListener((obs, o, n) -> {
            invoice.setCompany(n);
            dirty.set(true);
        });
        view.datePicker.valueProperty().addListener((obs, o, n) -> {
            invoice.setDate(n.format(dtf));
            dirty.set(true);
        });

        dirty.addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                view.statusLabel.setText("");
            }
        });

        // "Speichern"-Button
        view.saveButton.setOnAction(this::save);

        // Enable "Speichern" only if something was changed
        view.saveButton.disableProperty().bind(dirty.not());
    }

    private <T> void setOnCommit(TableColumn<Position, T> col) {
        var handler = col.getOnEditCommit();
        col.setOnEditCommit(event -> {
            handler.handle(event);
            dirty.set(true);
        });
    }

    private void save(ActionEvent event) {
        try {
            model.saveInvoice();
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
