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

    @Override
    public boolean parkTheCar(Car car) {
        return false;
    }

    @Override
    public boolean parkTheTruck(Car car) {
        return false;
    }

    @Override
    public int checkFreeParkingPlaces() {
        return 0;
    }

    @Override
    public int getBusyPlaces() {
        return 0;
    }
}
