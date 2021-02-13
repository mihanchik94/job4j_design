package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**Шаблон итератор описывается интерфейсом - java.util.Iterator.
 *Метод hasNext проверяет, если ли следующий элемент.
 *Многократный вызов этого метода должен быть одинаковым.
 *Метод next возвращает первый элемент ячейки. Второй вызов метода next вернет второй элемент и так далее.
 *Если в итераторе нет элементов и мы вызовем метод next,
 *итератор должен сгенерировать исключение NoSuchElementException.
 *Метод next сдвигает указатель итератора. Указатель - это ссылка на элемент, который нужно вернуть.
 *Шаблон итератор используется в коллекциях, базах данных, чтении файлов.
 *Необходимо сделать итератор для одномерного массива чисел
 */

public class ArrayIt implements Iterator<Integer> {
    private final int[] data;
    private int point = 0;

    public ArrayIt(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return point < data.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[point++];
    }
}
