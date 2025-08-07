package com.example.javafx.config.data;

import jakarta.xml.bind.annotation.*;

import java.util.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Api {

    @XmlAttribute(name = "name")
    private String name;

    @XmlElement(name = "endpoint")
    private List<Endpoint> endpoints;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public List<Endpoint> getEndpoints() {
        return endpoints;
    }
    public void setEndpoints(List<Endpoint> endpoints) {
        this.endpoints = endpoints;
    }

}
