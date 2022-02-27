package ru.job4j.lsp;

import java.time.LocalDate;
import java.util.List;

public interface Strategy {
    void add(Food food);
    void delete(Food food);
    boolean accept(Food food);
    List<Food> getAll();
    void clear();


    default double storage(Food food) {
        return (double) (LocalDate.now().toEpochDay() - food.getCreateDate().toEpochDay())
                / (double) (food.getExpiryDate().toEpochDay() - food.getCreateDate().toEpochDay());
    }
}
