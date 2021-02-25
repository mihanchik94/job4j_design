package ru.job4j.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * R.1.03 - Исправил методы replace и delete
 */


public class MemStore<T extends Base> implements Store<T> {
    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        int ind = findIndById(id);
        if (ind != -1) {
            this.mem.set(ind, model);
            return true;
        }
       return false;
        }


    @Override
    public boolean delete(String id) {
        int ind = findIndById(id);
        if (ind != -1) {
            mem.remove(ind);
            return true;
        }
        return false;
    }

    @Override
    public T findById(String id) {
        if (findIndById(id) != -1) {
            for (T item : mem) {
                if (item.getId().equals(id)) {
                    return item;
                }
        }
        }
        return null;
    }


    @Override
    public int findIndById(String id) {
        for (int i = 0; i < mem.size(); i++) {
            if (mem.get(i).equals(id)) {
                return i;
            }
        }
        return -1;
    }
}
