package com.example.javafx.rechnung;

import com.example.javafx.rechnung.data.*;
import com.example.javafx.rechnung.model.*;
import jakarta.xml.bind.*;
import javafx.beans.property.*;
import javafx.collections.*;
import javafx.event.*;
import javafx.scene.control.*;

import java.io.*;
import java.time.format.*;

public class InvoiceController {

    private final InvoiceModel model;
    final InvoiceView view;
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

        // Kurs-Beschreibungsliste
        lines = FXCollections.observableList(invoice.getCourse().getLines());

        // Änderungen in der Liste beobachten
        lines.addListener((ListChangeListener<String>) change -> dirty.set(true));

        // Edit-Commit-Handler anpassen
        setOnCommit(view.colText);
        setOnCommit(view.colPrice);

        // Status-Leiste zurücksetzen
        dirty.addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                view.statusLabel.setText("");
            }
        });

        // TODO Kopf-Daten initialisieren

        // TODO Zeilen hinzufügen/löschen konfigurieren

        // TODO Positionen-Tabelle befüllen

        // TODO Buttons für Positionen hinzufügen/löschen konfigurieren

        // TODO Änderungen an Kopf-Feldern tracken

        // TODO "Speichern"-Button

        // TODO "Speichern" nur aktivieren, wenn etwas geändert wurde
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

}
