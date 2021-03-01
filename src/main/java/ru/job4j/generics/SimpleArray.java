package ru.job4j.generics;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * REV.1.02
 *
 *Необходимо сделать универсальную обертку над массивом.
 *методы:
 * add(T model) - добавляет указанный элемент (model) в первую свободную ячейку;
 *
 * set(int index, T model) - заменяет указанным элементом (model) элемент, находящийся по индексу index;
 *
 * remove(int index) - удаляет элемент по указанному индексу, все находящиеся справа элементы при этом необходимо сдвинуть на единицу влево (в середине массива не должно быть пустых ячеек);
 *
 * get(int index) - возвращает элемент, расположенный по указанному индексу;
 *
 * Также, реализуйте интерфейс Iterable<T> - метод iterator() возвращает итератор, предназначенный для обхода данной структуры.
 *
 * Объект должен принимать количество ячеек. Структура не должна быть динамической.
 *
 * Для проверки индекса используйте метод Objects.checkIndex().
 *
 * Обрабатывать исключение не нужно! Т.к. это связано не с нашей ошибкой, а с ошибкой пользователя нашего класса
 *
 * Objects.checkIndex(index, size);
 *
 */

public class SimpleArray<T> implements Iterable<T> {
    private Object[] array;
    private int size = 0;

    public SimpleArray(int size) {
        array = new Object[size];
    }

    public void add(T model) {
        array[size] = model;
        size++;
    }

    public void set(int index, T model) {
        int ind = Objects.checkIndex(index, size);
        array[ind] = model;
    }

    public void remove(int index) {
        int ind = Objects.checkIndex(index, size);
        System.arraycopy(array, ind + 1, array, ind, size - ind - 1);
        array[size - 1] = null;
        size--;
    }

    public T get(int index) {
        return (T) array[index];
    }



    @Override
    public Iterator<T> iterator() {
        Iterator<T> it = new Iterator<T>() {
            private int currentInd = 0;
            @Override
            public boolean hasNext() {
                return currentInd < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                return (T) array[currentInd++];
            }
        };
        return it;
    }
}