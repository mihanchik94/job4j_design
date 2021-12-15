package ru.job4j.serialization.json;

public class Passport {
    private String number;

    public Passport(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
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
