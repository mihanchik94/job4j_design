package ru.job4j.generic;

import java.util.ArrayList;
import java.util.List;

public class MemStore<T extends Base> implements Store<T> {
    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        if (findById(id) != null) {
            this.mem.set(this.mem.indexOf(findById(id)), model);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        if (findById(id) == null) {
            return false;
        }
        return this.mem.remove(findById(id));
    }

    @Override
    public T findById(String id) {
        for (T item : mem) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }
}
