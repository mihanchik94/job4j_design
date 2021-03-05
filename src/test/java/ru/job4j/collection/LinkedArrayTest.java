package ru.job4j.collection;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class LinkedArrayTest {

    @Test
    public void whenAddAndGet() {
        LinkedArray linkedArray = new LinkedArray<>();
        linkedArray.add(1);
        linkedArray.add(2);
        linkedArray.add(3);
        assertThat(linkedArray.get(0), is(1));
        assertThat(linkedArray.get(1), is(2));
        assertThat(linkedArray.get(2), is(3));
    }



    @Test
    public void iterator() {
    }
}