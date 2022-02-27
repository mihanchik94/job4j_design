package ru.job4j.lsp;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Strategy {
    private final List<Food> wareHouse = new ArrayList<>();

    @Override
    public void add(Food food) {
        if (accept(food)) {
            wareHouse.add(food);
        }
    }

    @Override
    public void delete(Food food) {
        wareHouse.remove(food);
    }

    @Override
    public boolean accept(Food food) {
        return storage(food) <= 0.25;
    }

    @Override
    public List<Food> getAll() {
        return List.copyOf(wareHouse);
    }

    @Override
    public void clear() {
        wareHouse.clear();
    }
}
