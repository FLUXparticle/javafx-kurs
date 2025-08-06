package com.example.javafx.rechnung.data;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "address")
public class Address {

    @XmlAttribute(name = "id")
    private String id;

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "street")
    private String street;

    @XmlElement(name = "city")
    private String city;

    @XmlElement(name = "vatid")
    private String vatId;

    // Default constructor required by JAXB
    public Address() {}

    public Address(String id, String name, String street, String city, String vatId) {
        this.id = id;
        this.name = name;
        this.street = street;
        this.city = city;
        this.vatId = vatId;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getVatId() { return vatId; }
    public void setVatId(String vatId) { this.vatId = vatId; }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append(name);
        sb.append(", ");
        sb.append(street);
        sb.append(", ");
        sb.append(city);
        if (vatId != null) {
            sb.append(" (");
            sb.append(vatId);
            sb.append(")");
        }
        return sb.toString();
    }

}
