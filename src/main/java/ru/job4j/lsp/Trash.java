package ru.job4j.lsp;

import java.util.ArrayList;
import java.util.List;

public class Trash implements Strategy {
    private final List<Food> trash = new ArrayList<>();


    @Override
    public void add(Food food) {
        if (accept(food)) {
            trash.add(food);
        }
    }

    @Override
    public void delete(Food food) {
        trash.remove(food);
    }

    @Override
    public boolean accept(Food food) {
        return storage(food) > 1.0;
    }

    @Override
    public List<Food> getAll() {
        return List.copyOf(trash);
    }

    @Override
    public void clear() {
        trash.clear();
    }
}
