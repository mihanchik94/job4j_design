package ru.job4j.lsp;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Strategy {
    private final List<Food> shop = new ArrayList<>();


    @Override
    public void add(Food food) {
        if (accept(food)) {
            if (storage(food) >= 0.75) {
                food.setPrice(food.getPrice() * food.getDiscount());
            }
            shop.add(food);
        }
    }

    @Override
    public void delete(Food food) {
        shop.remove(food);
    }

    @Override
    public boolean accept(Food food) {
        return storage(food) > 0.25 && storage(food) <= 1;
    }

    @Override
    public List<Food> getAll() {
        return List.copyOf(shop);
    }

    @Override
    public void clear() {
        shop.clear();
    }
}
