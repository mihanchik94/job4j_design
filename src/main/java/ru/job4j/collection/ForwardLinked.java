package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable {
    private Node<T> head;


    public boolean isEmpty() {
        return head == null;
    }

     public void add(T value) {
         Node<T> node = new Node(value, null);
         if (isEmpty()) {
             head = node;
             return;
         }
         Node<T> tail = head;
         while (tail.next != null) {
             tail = tail.next;
         }
         tail.next = node;
     }

     public T deleteFirst() {
         if (isEmpty()) {
             throw new NoSuchElementException();
         }
         Node<T> first = head;
         head = head.next;
         first.next = null;
         return first.value;
     }

     public void addFirst(T value) {
        head = new Node<>(value, head);
    }


    public boolean revert() {
        boolean result = false;
        if (isEmpty()) {
            result = false;
        }
        if (!isEmpty() && head.next != null) {
            Node<T> tail = head;
            Node<T> current = head.next;
            head.next = null;
            while (current != null) {
                Node<T> next = current.next;
                current.next = head;
                head = current;
                current = next;
            }
            result = true;
        }
        return result;
    }

    private static class Node<T> {
        T value;
        Node<T> next;
        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            ForwardLinked.Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
     }
}
