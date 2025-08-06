package com.example.javafx.rechnung.data;

import jakarta.xml.bind.annotation.*;

import java.util.*;

/**
 * class für <course>
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Course {

    @XmlAttribute(name = "type")
    private String type;

    @XmlAttribute(name = "code")
    private String code;

    @XmlElement(name = "line")
    private List<String> lines;

    public Course() {
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Course{");
        sb.append("type='").append(type).append('\'');
        sb.append(", code='").append(code).append('\'');
        sb.append(", lines=").append(lines);
        sb.append('}');
        return sb.toString();
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public List<String> getLines() {
        return lines;
    }
    public void setLines(List<String> lines) {
        this.lines = lines;
    }

}
