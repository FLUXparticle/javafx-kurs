package com.example.javafx.rechnung.model;

import com.example.javafx.rechnung.data.*;
import jakarta.xml.bind.*;

import java.io.*;
import java.util.*;

public class AddressesModel {

    private final XmlModel<Addresses> xmlModel;

    public AddressesModel() throws JAXBException {
        xmlModel = new XmlModel<>(new File("files/addresses.xml"), Addresses.class);
    }

    public void loadAddresses() throws JAXBException {
        xmlModel.load();
    }

    public void saveAddresses() throws JAXBException {
        xmlModel.save();
    }

    public List<Address> getAddresses() {
        return xmlModel.getData().getAddresses();
    }

    public static void main(String[] args) throws JAXBException {
        AddressesModel model = new AddressesModel();

        model.loadAddresses();

        for (Address address : model.getAddresses()) {
            System.out.println(address);
        }
    }

}
