package ru.job4j.collection.ru.job4j.collection.set;

import org.junit.Test;

import javax.swing.text.html.HTMLDocument;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleSetTest {

    @Test
    public void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertTrue(set.contains(1));
        assertFalse(set.add(1));
    }

    @Test
    public void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(null));
        assertTrue(set.contains(null));
        assertFalse(set.add(null));
    }

    @Test
    public void whenAddThenIt() {
        Set<Integer> set = new SimpleSet<>();
        set.add(2);
        set.add(3);
        Iterator it = set.iterator();
        assertTrue(it.hasNext());
        assertThat(it.next(), is(2));
    }

    @Test (expected = NoSuchElementException.class)
    public void whenItEmpty() {
        Set<Integer> set = new SimpleSet<>();
        set.iterator().next();
    }

}