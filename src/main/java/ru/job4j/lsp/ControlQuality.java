package ru.job4j.lsp;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {
    private final List<Strategy> strategies;

    public ControlQuality(List<Strategy> strategies) {
        this.strategies = strategies;
    }

    public List<Strategy> getStrategies() {
        return strategies;
    }

    public void add(Food food) {
        boolean result = false;
        for (Strategy strategy : strategies) {
            if (strategy.accept(food)) {
                strategy.add(food);
                result = true;
            }
        }
        if (result) {
            System.out.println(food.getName() + " moved successfully");
        } else {
            throw new IllegalArgumentException("date is wrong");
        }
    }

    public void resort() {
        List<Food> resortingContainer = new ArrayList<>();
        for (Strategy strategy : strategies) {
            resortingContainer.addAll(strategy.getAll());
            strategy.clear();
        }
        for (Food food : resortingContainer) {
            add(food);
        }
    }
}
