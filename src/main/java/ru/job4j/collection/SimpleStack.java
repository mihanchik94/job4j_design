package ru.job4j.collection;

public class SimpleStack<T> {
    private ForwardLinked<T> linked = new ForwardLinked<T>();
    private int size;

    public int getSize() {
        return size;
    }

    public T pop() {
        size--;
        return linked.deleteFirst();
    }

    public void push(T value) {
        linked.addFirst(value);
        size++;
    }

}
