package com.example.javafx.config.model;

import com.example.javafx.config.data.*;
import com.example.javafx.rechnung.model.*;
import jakarta.xml.bind.*;

import java.io.*;

public class ConfigModel {

    private final XmlModel<Configuration> xmlModel;

    public ConfigModel() throws JAXBException {
        xmlModel = new XmlModel<>(new File("files/configuration.xml"), Configuration.class);
    }

    public void loadConfiguration() throws JAXBException {
        xmlModel.load();
    }

    public void saveConfiguration() throws JAXBException {
        xmlModel.save();
    }

    public Configuration getConfiguration() {
        return xmlModel.getData();
    }

}
