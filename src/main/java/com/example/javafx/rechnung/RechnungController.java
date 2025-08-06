package com.example.javafx.rechnung;

import jakarta.xml.bind.*;
import javafx.stage.*;

import java.io.*;

public class RechnungController {

    final RechnungView view;

    public RechnungController() {
        view = new RechnungView();

        view.adressenMenuItem.setOnAction(event -> {
            try {
                var controller = new AddressesController();
                var stage = new Stage();
                controller.view.buildStage(stage);
            } catch (JAXBException e) {
                view.statusLabel.setText("Fehler beim Laden der Adressen: " + e.getMessage());
            }
        });

        view.rechnungMenuItem.setOnAction(event -> {
            try {
                var controller = new InvoiceController(new File("files/rechnung.xml"));
                var stage = new Stage();
                controller.view.buildStage(stage);
            } catch (JAXBException e) {
                view.statusLabel.setText("Fehler beim Laden der Rechnung: " + e.getMessage());
            }
        });
    }

}
