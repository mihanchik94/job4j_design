package ru.job4j.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * R.1.01 - Добавили в данную ревизию дополнительный метод поиска индекса элемента по id
 */





public class MemStore<T extends Base> implements Store<T> {
    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        if (findIndById(id) == -1) {
            return false;
        }
        this.mem.set(this.mem.indexOf(findById(id)), model);
        return true;
        }


    @Override
    public boolean delete(String id) {
        if (findIndById(id) == -1) {
            return false;
        }
        return this.mem.remove(findById(id));
    }

    @Override
    public T findById(String id) {
        if (findIndById(id) == -1) {
            return null;
        }
        for (T item : mem) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        throw new NullPointerException("This id doesn't exist");
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
