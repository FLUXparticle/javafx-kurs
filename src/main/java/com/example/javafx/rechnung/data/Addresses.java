package com.example.javafx.rechnung.data;

import jakarta.xml.bind.annotation.*;

import java.util.*;

@XmlRootElement(name = "addresses")
@XmlAccessorType(XmlAccessType.FIELD)
public class Addresses {

    @XmlElement(name = "address")
    private List<Address> addresses = new ArrayList<>();

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

}
