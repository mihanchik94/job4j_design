package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable {
    private Node<T> head;

     public void add(T value) {
         Node<T> node = new Node(value, null);
         if (head == null) {
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
         Node<T> first = head;
         if (head == null) {
             throw new NoSuchElementException();
         }
         head = head.next;
         return first.value;
     }

     public T deleteLast() {
         if (head == null) {
             throw new NoSuchElementException();
         }
         Node<T> prev = null;
         Node<T> current = head;
         while (current.next != null) {
             prev = current;
             current = current.next;
         }
         if (prev == null) {
             head = null;
         } else {
             prev.next = null;
         }
         return current.value;
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
