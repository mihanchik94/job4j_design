package ru.job4j.ood.isp;

/**
 * В данном интерфесе можно заметить следующее нарушение принципа ISP:
 * не у всех автомобилей есть функция круиз-контроля
 * В данном интерфейсе необходимо оставить только функции, которые есть у всех автомобилей,
 * а специфические функции вынести отдельными интерфейсами.
 */

public interface CarFunctions {
    boolean startEngine();
    void move();
    void brake();
    boolean refuel();
    void cruiseControl();
}
