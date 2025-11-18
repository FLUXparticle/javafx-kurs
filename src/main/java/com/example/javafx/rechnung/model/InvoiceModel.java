package com.example.javafx.rechnung.model;

import com.example.javafx.rechnung.data.*;
import com.example.javafx.xml.model.*;
import jakarta.xml.bind.*;

import java.io.*;

public class InvoiceModel {

    private final XmlModel<Invoice> xmlModel;

    public InvoiceModel(File file) throws JAXBException {
        xmlModel = new XmlModel<>(file, Invoice.class);
    }

    public void loadInvoice() throws JAXBException {
        xmlModel.load();
    }

    public void saveInvoice() throws JAXBException {
        xmlModel.save();
    }

    public Invoice getInvoice() {
        return xmlModel.getData();
    }

    public static void main(String[] args) throws JAXBException {
        InvoiceModel model = new InvoiceModel(new File("files/rechnung.xml"));

        model.loadInvoice();

        Invoice invoice = model.getInvoice();
        System.out.println(invoice);
    }

}
