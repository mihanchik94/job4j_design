package ru.job4j.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public class MyParking implements Parking {
    private int carPlaces;
    private int truckPlaces;
    private final List<Car> parking = new ArrayList<>();


    public MyParking(int carPlaces, int truckPlaces) {
        this.carPlaces = carPlaces;
        this.truckPlaces = truckPlaces;
    }

    public int getCarPlaces() {
        return carPlaces;
    }

    public int getTruckPlaces() {
        return truckPlaces;
    }

    @Override
    public boolean parkTheCar(Car car) {
        boolean result = false;
        if (car.getSize() == 1) {
            result = addCar(car);
        } else {
            result = addTruck(car);

        }
        return result;
    }

    @Override
    public int checkFreeParkingPlaces() {
        return (carPlaces + truckPlaces);
    }

    @Override
    public int getBusyPlaces() {
        return parking.size();
    }

    public boolean addCar(Car car) {
        boolean result = false;
        if (carPlaces >= 1) {
            parking.add(car);
            carPlaces = carPlaces - 1;
            result = true;
        }
        return result;
    }

    public boolean addTruck(Car car) {
        boolean result = false;
        if (truckPlaces >= 1) {
            parking.add(car);
            truckPlaces = truckPlaces - 1;
            result = true;
        } else {
            if (carPlaces >= car.getSize()) {
                parking.add(car);
                carPlaces = carPlaces - car.getSize();
                result = true;
            }
        }
        return result;
    }
}
