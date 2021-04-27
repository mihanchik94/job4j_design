package ru.job4j.iterator;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ListUtilsTest {
    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(Arrays.asList(1, 2, 3), is(input));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(Arrays.asList(0, 1, 2, 3), is(input));
    }

    @Test
    public void whenAddAfter() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 2, 3));
        ListUtils.addAfter(input, 0, 1);
        assertThat(Arrays.asList(0, 1, 2, 3), is(input));
    }

    @Test
    public void whenRemoveIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 6, 1, 5, 2, 4, 3));
        ListUtils.removeIf(input, el -> el > 3);
        assertThat(Arrays.asList(0, 1, 2, 3), is(input));
    }

    @Test
    public void whenReplaceIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 5, 3));
        ListUtils.replaceIf(input, el -> el == 5, 2);
        assertThat(Arrays.asList(0, 1, 2, 3), is(input));
    }


}