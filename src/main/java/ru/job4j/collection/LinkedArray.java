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
    private Node<E> last;''
    private int size;
    private int modCount;

    public void add(E value) {
        Node<E> item = new Node<>(value, size++);
            if (first == null) {
                first = item;
            } else {
                last.next = item;
            }
            last = item;
            modCount++;
        }

        public E get(int index) {
            Objects.checkIndex(index, size);
            Node<E> current = last;
            while (current.index != index) {
                if (current.next == null) {
                    return null;
                }
                current = current.next;
            }
            return current.value;
        }

    public int getModCount() {
        return modCount;
    }

    public int getSize() {
        return size;
    }

    private class Node<E> {
        private E value;
        private Node<E> next;
        private int index;

        public Node(E value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    @Override
    public Iterator<E> iterator() {
        class LinkedArrayIterator<E> implements Iterator<E> {
            private LinkedArray<E> linkedArray;
            private int count = 0;
            private int modCount;

            public LinkedArrayIterator(LinkedArray<E> linkedArray) {
                this.linkedArray = linkedArray;
                this.modCount = linkedArray.getModCount();
            }

            @Override
            public boolean hasNext() {
                if (modCount !=  linkedArray.getModCount()) {
                    throw new ConcurrentModificationException();
                }
                return count < linkedArray.getSize();
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return linkedArray.get(count++);
            }
        }
        return new LinkedArrayIterator<>(this);
    }


}
