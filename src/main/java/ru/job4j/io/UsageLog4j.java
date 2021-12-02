package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Igor";
        byte age = 37;
        short height = 190;
        int carPrice = 8500000;
        long estimatePrice = 112000000;
        float weight = 95.4f;
        double passportNumber = 440.125;
        char classLetter = 'A';
        boolean marriageStatus = false;

        LOG.info("User info name: {}, age: {}, height: {} cm, price of car: {}, price of estimate: {}, weight: {} kg, num of passport: {}, let.of class: {}, marriage status: {}",
                name, age, height, carPrice, estimatePrice, weight, passportNumber, classLetter, marriageStatus);
    }
}
