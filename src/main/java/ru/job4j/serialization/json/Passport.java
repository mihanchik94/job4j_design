package ru.job4j.serialization.json;

public class Passport {
    private final String number;

    public Passport(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Passport{"
                + "number='" + number
                + '\''
                + '}';
    }
}
