package com.example.javafx.rechnung;

import jakarta.xml.bind.*;
import javafx.scene.*;
import javafx.stage.*;

import java.io.*;

public class RechnungController {

    private final RechnungView view;

    public RechnungController() {
        view = new RechnungView();

        view.adressenMenuItem.setOnAction(event -> {
            try {
                var controller = new AddressesController();
                var root = controller.getRoot();
                var stage = new Stage();
                stage.setTitle("Adressen");
                stage.setScene(new Scene(root, 600, 400));
                stage.show();
            } catch (JAXBException e) {
                view.statusLabel.setText("Fehler beim Laden der Adressen: " + e.getMessage());
            }
        });

        view.rechnungMenuItem.setOnAction(event -> {
            try {
                var controller = new InvoiceController(new File("files/rechnung.xml"));
                var root = controller.getRoot();
                var stage = new Stage();
                stage.setTitle("Rechnung bearbeiten");
                stage.setScene(new Scene(root, 700, 800));
                stage.show();
            } catch (JAXBException e) {
                view.statusLabel.setText("Fehler beim Laden der Rechnung: " + e.getMessage());
            }
        });
    }

    public Parent getRoot() {
        return view;
    }

}
