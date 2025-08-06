package com.example.javafx.rechnung.model;

import jakarta.xml.bind.*;

import java.io.*;

public class XmlModel<T> {

    private final Unmarshaller unmarshaller;
    private final Marshaller marshaller;

    private final File file;

    private T data;

    public XmlModel(File file, Class<T> tClass) throws JAXBException {
        this.file = file;

        JAXBContext context = JAXBContext.newInstance(tClass);
        unmarshaller = context.createUnmarshaller();
        marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    }

    public void load() throws JAXBException {
        data = (T) unmarshaller.unmarshal(file);
    }

    public void save() throws JAXBException {
        marshaller.marshal(data, file);
    }

    public T getData() {
        return data;
    }

}
