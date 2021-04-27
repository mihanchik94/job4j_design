package ru.job4j.iterator;

import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.function.Predicate;

/**ListIterator
 * Iterator обладает поведением fail-fast. Что это значит? Это значит, что если мы по ходу итерирования
 * меняем коллекцию, то получаем исключение ConcurrentModificationException.
 * В качестве альтернативы "обычному" итератору, есть ListIterator. Он обладает fail-safe поведением,
 * это значит, что мы можем менять коллекцию по ходу итерирования, но только с помощью самого итератора.
 * Чтобы как-то манипулировать списком во время итерирования, нужно использовать методы ListIterator.
 * 1. Реализовать недостающие методы в классе ListUtils:
 *
 * - addAfter() вставляет после индекса;
 *
 * - addBefore() вставляет до индекса;
 *
 * - removeIf() удаляет все элементы, которые удовлетворяют предикату.
 * Запрещено использовать метод List.removeIf;
 *
 * - replaceIf() заменяет все элементы, которые удовлетворяют предикату;
 *
 * - removeAll() удаляет из списка те элементы, которые есть в elements.
 * Запрещено использовать метод List.removeAll().
 *
 * 2. Написать тесты на каждый из методов.
 */

public class ListUtils {
    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> it = list.listIterator();
        while (it.hasNext()) {
            if (it.nextIndex() == index) {
                it.add(value);
                break;
            }
            it.next();
        }
    }

    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> it = list.listIterator();
        while (it.hasNext()) {
            if (it.nextIndex() == index) {
                it.next();
                it.add(value);
                break;
            }
            it.next();
        }
    }

    public static <T> void removeIf(List<T> list, Predicate<T> filter) {
        ListIterator<T> it = list.listIterator();
        while (it.hasNext()) {
            if (filter.test(it.next())) {
                it.remove();
            }
        }
    }

    public static <T> void replaceIf(List<T> list, Predicate<T> filter, T value) {
        ListIterator<T> it = list.listIterator();
        while (it.hasNext()) {
            if (filter.test(it.next())) {
                it.set(value);
            }
        }
    }

    public static <T> void removeAll(List<T> list, List<T> elements) {
        ListIterator<T> it = list.listIterator();
        while (it.hasNext()) {
            T delElement = it.next();
            if (elements.contains(delElement)) {
                it.remove();
            }
        }
    }
}
