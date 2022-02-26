package ru.job4j.lsp.parking;

abstract class Car {
    private String brand;
    private String model;
    private String number;
    private int size;

    public Car(String brand, String model, String number, int size) {
        this.brand = brand;
        this.model = model;
        this.number = number;
        validate(size);
        this.size = size;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getNumber() {
        return number;
    }

    public int getSize() {
        return size;
    }

    abstract void validate(int size);
}
