package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Создадим реализацию ArrayList. ArrayList - это массив.
 * Когда элементов становится больше чем ячеек, в массиве ArrayList создает новый массив с большим размером.
 * Внутри контейнер должен базироваться на массиве Object[] container.
 * Использовать стандартные коллекции JDK (ArrayList, LinkedList и т.д.) запрещено.
 * Контейнер должен быть динамическим, т.е. при полном заполнении увеличиваться.
 *
 * Итератор должен реализовывать fail-fast поведение, т.е. если с момента создания итератора в коллекцию добавили новый элемент,
 * итератор должен кидать ConcurrentModificationException.
 * Это достигается через введение счетчика изменений - modCount. Каждая операция, которая структурно модифицирует коллекцию должна увеличивать этот счетчик.
 * В свою очередь итератор запоминает значение этого счетчика на момент своего создания (expectedModCount),
 * а затем на каждой итерации сравнивает сохраненное значение, с текущим значением поля modCount, если они отличаются, то генерируется исключение.
 *
 * 1. В методах, где используется индекс нужно делать валидацию. Индекс должен находиться в рамках добавленных элементов.
 * Например, есть хранилище из 10 элементов. Добавили 3 элемента. Каким может быть индекс? [0, 2]. Для проверки индекса используем метод Objects.checkIndex().
 * Обрабатывать исключение не нужно! Т.к. это связано не с нашей ошибкой, а с ошибкой пользователя нашего класса
 *
 * 2. Количество элементов - это показатель, который говорит, сколько элементов в коллекции на данный момент.
 * Переменную, которая будет отвечать за это число можно применять для размещения элементов (если говорить про динамический массив).
 * Количество изменений - это показатель, который нам говорит, сколько раз коллекция была изменена с момента ее создания.
 * Например, вы создали коллекцию. Добавили в нее элемент, а потом удалили его. Сколько элементов в коллекции? 0, а сколько раз ее изменили? 2 раза.
 * Это показатель всегда растет. При добавлении, удалении и изменении. Его используют для того, чтобы на момент итерирования коллекцию держать в определенном (defined)
 * состоянии, иначе если во время итерирования был вставлен элемент, а мы его обойти не смогли значит наш итератор работает не корректно
 * (на самом деле состояние коллекции не определено точно).
 *
 * 3. Итератор кидает два исключения:
 * 1) NoSuchElementException. Относится к первому показателю. Тут просто. Если итератор "уперся", т.е. нет больше элементов, а клиент вызвал этот метод,
 * то этим исключение мы ему подчеркиваем, что элементов больше нет.
 *
 * 2) ConcurrentModificationException. Относится ко второму показателю. Чтобы кинуть это исключение заводят отдельную переменную в итераторе
 * expectedModCount = modCount и проверяют условие if (expectedModCount != modCount). Если условие выполнено, значит на момент итерирования была изменена коллекция,
 * поэтому вылетает исключение. Это называется fail-fast поведение
 *
 * Вывод. Чтобы реализовать итератор нужны два поля.
 *
 * 4. Не путать null элементы и пустые ячейки контейнера. Список может содержать null элементы.
 */

public class SimpleArray<T> implements Iterable {
    int modCount;
    private int size = 0;
    private int capacity = 10;
    Object[] container = new Object[capacity];


    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) container[index];
    }

    public int getSize() {
        return size;
    }


    public void add(T model) {
        if (container.length == size) {
            resize(container.length + 10);
        }
        container[size++] = model;
    }

    public void resize(int newLength) {
        Object[] newCont = new Object[newLength];
        System.arraycopy(container, 0, newCont, 0, size);
        container = newCont;
    }

    public int getModCount() {
        return modCount;
    }


    @Override
    public Iterator iterator() {
        class SimpleArrayIterator<T> implements Iterator<T> {
            private int count = 0;
            private final int expectedModCount;
            private final SimpleArray<T> simpleArray;

            SimpleArrayIterator(SimpleArray<T> array) {
                this.simpleArray = array;
                this.expectedModCount = array.getModCount();
            }

            private boolean isModified() {
                return simpleArray.getModCount() == expectedModCount;
            }

            @Override
            public boolean hasNext() {
                    if (isModified()) {
                        throw new ConcurrentModificationException();
                    }
                    return count < simpleArray.getSize();
                }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return simpleArray.get(count++);
            }
        }
        return new SimpleArrayIterator(this);
    }
}
