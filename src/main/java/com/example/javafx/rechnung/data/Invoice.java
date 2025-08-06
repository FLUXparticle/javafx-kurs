package com.example.javafx.rechnung.data;

import jakarta.xml.bind.annotation.*;

import java.util.*;

/**
 * JAXB-mapped class für das gegebene XML-Schema:
 * <invoice>
 *     <city>...</city>
 *     <date>...</date>
 *     <number>...</number>
 *     <company>...</company>
 *     <course type="..." code="...">
 *         <line>...</line>
 *         ...
 *     </course>
 *     <positions>
 *         <position text="..." price="..."/>
 *         ...
 *     </positions>
 * </invoice>
 */
@XmlRootElement(name = "invoice")
@XmlAccessorType(XmlAccessType.FIELD)
public class Invoice {

    @XmlElement(name = "city")
    private String city;

    @XmlElement(name = "date")
    private String date;

    @XmlElement(name = "number")
    private String number;

    @XmlElement(name = "company")
    private String company;

    @XmlElement(name = "course")
    private Course course;

    @XmlElementWrapper(name = "positions")
    @XmlElement(name = "position")
    private List<Position> positions;

    // Default-Konstruktor für JAXB
    public Invoice() {}

    @Override
    public String toString() {
        var sb = new StringBuilder("Invoice{");
        sb.append("city='").append(city).append('\'');
        sb.append(", date='").append(date).append('\'');
        sb.append(", number='").append(number).append('\'');
        sb.append(", company='").append(company).append('\'');
        sb.append(", course=").append(course);
        sb.append(", positions=").append(positions);
        sb.append('}');
        return sb.toString();
    }

    // Getter & Setter
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }

    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }

    public List<Position> getPositions() { return positions; }
    public void setPositions(List<Position> positions) { this.positions = positions; }

}
