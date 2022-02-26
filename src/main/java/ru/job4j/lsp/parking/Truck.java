package ru.job4j.lsp.parking;

public class Truck extends Car {
    private String brand;
    private String model;
    private String number;
    private int size;

    public Truck(String brand, String model, String number, int size) {
        super(brand, model, number, size);
    }


    @Override
    void validate(int size) {
        if (size <= 1) {
            throw new IllegalArgumentException("Truck size is wrong");
        }
    }
}
