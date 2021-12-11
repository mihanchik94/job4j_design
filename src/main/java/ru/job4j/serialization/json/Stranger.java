package ru.job4j.serialization.json;

import java.util.Arrays;

public class Stranger {
    private final boolean sex;
    private final int age;
    private final String name;
    private final Passport passport;
    private final String[] statuses;

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
