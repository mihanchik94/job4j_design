package ru.job4j.it;

import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * В Stream API есть метод flatMap. Он позволяет получить из элемента потока другой поток.
 * Нужно создать подобное поведение.
 * Класс FlatMap принимает объект вложенных итераторов.
 * В классе нужно реализовать два метода: next и hasNext.
 * Метод next должен последовательно вернуть числа из вложенных итераторов.
 */


public class FlatMap<T> implements Iterator<T> {
    private final Iterator<Iterator<T>> data;
    private Iterator<T> cursor = Collections.emptyIterator();

    public FlatMap(Iterator<Iterator<T>> data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while (!cursor.hasNext() && data.hasNext()) {
            cursor = data.next();
        }
        return cursor.hasNext();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return cursor.next();
    }
}
