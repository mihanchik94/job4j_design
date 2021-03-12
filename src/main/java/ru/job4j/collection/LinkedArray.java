package ru.job4j.collection;


import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Необходимо создать динамический контейнер с методами:
 * 1) add(E value); (добавляет в конец)
 * 2) E get(int index);
 * 3) реализовать интерфейс Iterable<E>.
 * Внутри контейнер должен базироваться на связанном списке(Node<E> node). Использовать стандартные коллекции JDK (ArrayList, LinkedList и т.д.) запрещено.
 * Контейнер должен быть динамическим, т.е. увеличиваться по мере добавления элементов.
 * Итератор должен реализовывать fail-fast поведение, т.е. если с момента создания итератора коллекция подверглась структурному изменению,
 * итератор должен кидать ConcurrentModificationException.
 * Это достигается через введение счетчика изменений - modCount. Каждая операция, которая структурно модифицирует коллекцию должна инкрементировать этот счетчик.
 * В свою очередь итератор запоминает значение этого счетчика на момент своего создания (expectedModCount), а затем на каждой итерации сравнивает сохраненное значение,
 * с текущим значением поля modCount, если они отличаются, то генерируется исключение.
 * Примечание:
 * В методах, где используется индекс нужно делать валидацию. Индекс должен находиться в рамках добавленных элементов.
 * Например, добавили 3 элемента. Каким может быть индекс? [0, 2]. Для проверки индекса используйте метод Objects.checkIndex.
 */

public class LinkedArray<E> implements Iterable<E> {
    private Node<E> first;
    private Node<E> last;
    private int size;
    private int modCount;

    public void add(E value) {
        Node<E> elm = new Node<>(last, value, null);
        if (first == null) {
            first = elm;
        } else {
            last.next = elm;
        }
        last = elm;
        size++;
        modCount++;
    }

    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> current = first;
        int i = 0;
        while (i < index) {
            current = current.next;
            i++;
        }
        return current.value;
    }

    private static class Node<E> {
        private Node<E> prev;
        private E value;
        private Node<E> next;

        public Node(Node<E> prev, E value, Node<E> next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int expectedModCount = modCount;
            private Node<E> temp = first;
            int count = 0;

            @Override
            public boolean hasNext() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                return count < size;
            }

            @Override
            public E next() {

                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                E result = temp.value;
                temp = temp.next;
                return result;
            }
        };
    }
}

