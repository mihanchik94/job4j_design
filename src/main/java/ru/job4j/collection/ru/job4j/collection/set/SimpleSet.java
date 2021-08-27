package ru.job4j.collection.ru.job4j.collection.set;

/*
1. Реализовать коллекцию SimpleSet. Коллекция не должна хранить дубликаты.
Для хранения использовать SimpleArray из прошлого задания.

2. Для сравнения объектов использовать метод:
https://docs.oracle.com/en/java/javase/16/docs/api/java.base/java/util/Objects.html#equals(java.lang.Object,java.lang.Object)

 */


import ru.job4j.collection.SimpleArray;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Set<T> {

    private SimpleArray<T> set = new SimpleArray<>();

    @Override
    public boolean add(T value) {
        if (contains(value)) {
            return false;
        }
        set.add(value);
        return true;
    }

    @Override
    public boolean contains(T value) {
        for (T t : set) {
            if (Objects.equals(t, value)) {
            }
            return true;
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}
