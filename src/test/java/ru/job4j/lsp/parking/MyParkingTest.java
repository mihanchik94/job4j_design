package ru.job4j.lsp.parking;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class MyParkingTest {
    @Test
    public void whenParkTheCar() {
        MyParking parking = new MyParking(2, 4);
        parking.parkTheCar(new Sedan("Audi", "A6", "E004KH"));
        assertThat(parking.getCarPlaces(), is(1));
    }

    @Test
    public void whenParkTheTruck() {
        MyParking parking = new MyParking(2, 4);
        parking.parkTheCar(new Truck("Mercedes-Benz", "Actros", "P252EA", 4));
        assertThat(parking.getTruckPlaces(), is(3));
    }

    @Test
    public void whenParkTheTruckOnCarPlaces() {
        MyParking parking = new MyParking(4, 0);
        parking.parkTheCar(new Truck("Chevrolet", "Express", "H311TK", 3));
        assertThat(parking.getCarPlaces(), is(1));
    }

    @Test
    public void whenNoPlacesToParkTheTruck() {
        MyParking parking = new MyParking(3, 0);
        assertFalse(parking.parkTheCar(new Truck("Mercedes-Benz", "Actros", "P252EA", 4)));
    }

    @Test
    public void whenNoPlacesToParkThePassengerCar() {
        MyParking parking = new MyParking(0, 6);
        assertFalse(parking.parkTheCar(new Sedan("Audi", "A6", "E004KH")));
    }

    @Test
    public void whenCheckFreeParkingPlaces() {
        MyParking parking = new MyParking(2, 4);
        parking.parkTheCar(new Truck("Mercedes-Benz", "Actros", "P252EA", 4));
        parking.parkTheCar(new Sedan("Audi", "A6", "E004KH"));
        assertThat(parking.checkFreeParkingPlaces(), is(4));
    }

    @Test
    public void whenGetBusyParkingPlaces() {
        MyParking parking = new MyParking(2, 4);
        parking.parkTheCar(new Truck("Mercedes-Benz", "Actros", "P252EA", 4));
        parking.parkTheCar(new Sedan("Audi", "A6", "E004KH"));
        assertThat(parking.getBusyPlaces(), is(2));
    }
}
