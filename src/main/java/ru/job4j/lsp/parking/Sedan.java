package ru.job4j.lsp.parking;

public class Sedan extends Car {
    private String brand;
    private String model;
    private String number;
    private final static int SIZE = 1;

    public Sedan(String brand, String model, String number) {
        super(brand, model, number, SIZE);
    }

    @Override
    void validate(int size) {
        if (size != 1) {
            throw new IllegalArgumentException("Passenger car size is wrong");
        }
    }
}
