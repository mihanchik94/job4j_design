package ru.job4j.map;

import com.sun.jdi.Value;
import org.junit.Test;

import java.security.Key;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void whenPutAndTrue() {
        Map<String, Integer> simpleMap = new SimpleMap<>();
        assertTrue(simpleMap.put("War and Peace", 1300));
    }

    @Test
    public void whenPutAndFalse() {
        Map<String, Integer> simpleMap = new SimpleMap<>();
        simpleMap.put("War and Peace", 1300);
        assertFalse(simpleMap.put("War and Peace", 1300));
    }


    @Test
    public void whenGetNull() {
        Map<String, Integer> simpleMap = new SimpleMap<>();
        assertNull(simpleMap.get("fgdg"));
    }

    @Test
    public void whenGetNotNull() {
        Map<String, Integer> simpleMap = new SimpleMap<>();
        simpleMap.put("War and Peace", 1300);
        assertThat(simpleMap.get("War and Peace"), is(1300));
    }


    @Test
    public void whenRemoveFromEmpty() {
        Map<String, Integer> simpleMap = new SimpleMap<>();
        assertFalse(simpleMap.remove("War and Peace"));
    }

    @Test
    public void whenRemoveFromNotEmpty() {
        Map<String, Integer> simpleMap = new SimpleMap<>();
        simpleMap.put("War and Peace", 1300);
        assertTrue(simpleMap.remove("War and Peace"));
    }

    @Test
    public void puttingWithExpand() {
        Map<Integer, Integer> simpleMap = new SimpleMap<>();
        IntStream.range(1, 20).forEach(x -> simpleMap.put(x, x * 2));
        assertThat(simpleMap.get(19), is(38));
    }


    @Test
    public void checkIterator() {
        Map<String, Integer> simpleMap = new SimpleMap<>();
        simpleMap.put("War and Peace", 1300);
        simpleMap.put("Shantaram", 856);
        Iterator<String> sIt = simpleMap.iterator();
        assertTrue(sIt.hasNext());
        assertThat(sIt.next(), is("War and Peace"));
        assertTrue(sIt.hasNext());
        assertThat(sIt.next(), is("Shantaram"));
        assertFalse(sIt.hasNext());
    }


    @Test
    public void checkIteratorWhenRemove() {
        Map<String, Integer> simpleMap = new SimpleMap<>();
        simpleMap.put("War and Peace", 1300);
        simpleMap.put("Shantaram", 856);
        simpleMap.remove("War and Peace");
        Iterator<String> sIt = simpleMap.iterator();
        assertEquals("Shantaram", sIt.next());
        assertFalse(sIt.hasNext());
    }

    @Test (expected = NoSuchElementException.class)
    public void whenEmptyIteratorThenException() {
        Map<String, Integer> simpleMap = new SimpleMap<>();
        Iterator<String> sIt = simpleMap.iterator();
        sIt.next();
    }

    @Test (expected = ConcurrentModificationException.class)
    public void whenPutAndIteratorThenException() {
        Map<String, Integer> simpleMap = new SimpleMap<>();
        Iterator<String> sIt = simpleMap.iterator();
        simpleMap.put("Shantaram", 856);
        sIt.next();
    }


}