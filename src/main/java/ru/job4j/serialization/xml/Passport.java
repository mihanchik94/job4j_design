package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Passport {
    @XmlAttribute
    private String number;

    public Passport(String number) {
        this.number = number;
    }

    public Passport() {

    }

    @Override
    public String toString() {
        return "Passport{"
                + "number='" + number
                + '\''
                + '}';
    }
}
