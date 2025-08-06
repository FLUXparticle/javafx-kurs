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

    public Position() {
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Position{");
        sb.append("text='").append(text).append('\'');
        sb.append(", price=").append(price);
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

}
