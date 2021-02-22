package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleArrayTest {

    @Test
    public void whenAddAndGet() {
        SimpleArray<Integer> array = new SimpleArray<Integer>(2);
        array.add(4);
        array.add(7);
        Integer rsl = array.get(1);
        assertThat(rsl, is(7));
    }

    @Test
    public void whenGetNull() {
        SimpleArray<Integer> array = new SimpleArray<Integer>(1);
        Object rsl = array.get(0);
        assertNull(rsl);
    }


    @Test
    public void whenSet() {
        SimpleArray<Integer> array = new SimpleArray<Integer>(2);
        array.add(4);
        array.add(7);
        array.set(0, 6);
        Integer rsl = array.get(0);
        assertThat(rsl, is(6));
    }

    @Test
    public void whenRemove() {
        SimpleArray<Integer> array = new SimpleArray<Integer>(2);
        array.add(4);
        array.add(3);
        array.remove(0);
        Integer rsl = array.get(0);
        assertThat(rsl, is(3));
    }
}