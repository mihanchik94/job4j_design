package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "stranger")
@XmlAccessorType(XmlAccessType.FIELD)
public class Stranger {
    @XmlAttribute
    private boolean sex;
    @XmlAttribute
    private int age;
    @XmlAttribute
    private String name;
    private Passport passport;
    @XmlElementWrapper
    @XmlElement(name = "status")
    private String[] statuses;

    public Stranger() {

    }

    public Stranger(boolean sex, int age, String name, Passport passport, String[] statuses) {
        this.sex = sex;
        this.age = age;
        this.name = name;
        this.passport = passport;
        this.statuses = statuses;
    }

    @Override
    public String toString() {
        return "Stranger{"
                + "sex=" + sex
                + ", age=" + age
                + ", name='" + name + '\''
                + ", passport=" + passport
                + ", statuses=" + Arrays.toString(statuses)
                + '}';
    }
}
