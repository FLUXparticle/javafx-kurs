package com.example.javafx.config.data;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Endpoint {

    private String verb;
    private String path;
    private boolean authRequired;

    public String getVerb() {
        return verb;
    }
    public void setVerb(String verb) {
        this.verb = verb;
    }

    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }

    public boolean isAuthRequired() {
        return authRequired;
    }
    public void setAuthRequired(boolean authRequired) {
        this.authRequired = authRequired;
    }

}
