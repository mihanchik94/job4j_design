package ru.job4j.collection;

/** Очередь на двух стеках
 * В этом задании мы рассмотрим другую организацию данных - очередь.
 * Термин FIFO - first input first output. Первый пришел, первый ушел.
 * Метод poll() - должен возвращать первое значение и удалять его из коллекции.
 * Метод push(T value) - помещает значение в конец.
 *
 * Алгоритм.
 * Данные очереди нужно хранить в ru.job4j.collection.SimpleStack. Для этого задания нужны два стека.
 * Представьте, что у вас стопка с тарелками. Вам нужно достать нижнюю тарелку. Для этого вы перекладываете все тарелки в другую стопку.
 * Стопка - это стек. Для операции извлечения первой тарелки нужны две стопки.
 */


public class SimpleQueue<T> {
    private SimpleStack<T> in = new SimpleStack<>();
    private SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        if (out.getSize() == 0) {
            while (in.getSize() > 0) {
                out.push(in.pop());
            }
        }

        return out.pop();
    }

    public void push(T value) {
        in.push(value);
    }
}
