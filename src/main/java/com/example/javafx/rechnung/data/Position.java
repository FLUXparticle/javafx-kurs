package com.example.javafx.rechnung.data;

import jakarta.xml.bind.annotation.*;

/**
 * class für <position>
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Position {

    @XmlAttribute(name = "text")
    private String text;

    @XmlAttribute(name = "price")
    private Double price;

    @XmlAttribute(name = "taxRate")
    private Double taxRate = 19.0;

    public Position() {
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Position{");
        sb.append("text='").append(text).append('\'');
        sb.append(", price=").append(price);
        sb.append(", taxRate=").append(taxRate);
        sb.append('}');
        return sb.toString();
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

}
