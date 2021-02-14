package ru.job4j.it;


import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Необходимо создать итератор, который возвращает только четные числа.
 * next() - возвращают только четные числа
 * hasNext() - возвращает true, только если в массиве есть четные перед указателем.
 */

public class EvenIt implements Iterator<Integer> {
    private final int[] data;
    private int point = 0;

    public EvenIt(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while (point < data.length) {
            if (data[point] % 2 == 0) {
                return true;
            }
            point++;
        }
        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[point++];
    }
}
