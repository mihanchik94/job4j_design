package ru.job4j.collection;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class LinkedArrayTest {

    @Test
    public void whenAddAndGet() {
        LinkedArray<Object> linkedArray = new LinkedArray<>();
        linkedArray.add(1);
        linkedArray.add(2);
        assertThat(linkedArray.get(0), is(1));
        assertThat(linkedArray.get(1), is(2));
    }



    @Test
    public void whenIterator() {
        LinkedArray<Object> linkedArray = new LinkedArray<>();
        linkedArray.add(1);
        linkedArray.add(2);
        linkedArray.add(3);
        linkedArray.add(4);
        Iterator it = linkedArray.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
    }
}