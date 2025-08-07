package com.example.javafx.config.data;

import jakarta.xml.bind.annotation.*;

import java.util.*;

@XmlRootElement(name = "configuration")
@XmlAccessorType(XmlAccessType.FIELD)
public class Configuration {

    @XmlElement(name = "api")
    private List<Api> apis;

    public List<Api> getApis() {
        return apis;
    }
    public void setApis(List<Api> apis) {
        this.apis = apis;
    }

}
