package ru.job4j.lsp.parking;

public interface Parking {
    boolean parkTheCar(Car car);
    boolean parkTheTruck(Car car);
    int checkFreeParkingPlaces();
    int getBusyPlaces();

}
