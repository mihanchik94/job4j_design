package ru.job4j.ood.isp;

/**
 * В данном интерфесе можно заметить следующее нарушение принципа ISP:
 * не все пылесосы могут двигаться, строить карты, заряжаться.
 * Имеет смысл вынести каждый метод данного интерфейса в отдельный интерфейс
 */

public interface VacuumCleanerFunctions {
    void clean();
    void buildMap();
    boolean charge();
}
