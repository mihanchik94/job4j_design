package ru.job4j.kiss;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;



public class MaxMinTest {
    @Test
    public void whenFindMaxThenThree() {
        List<Integer> list = Arrays.asList(1, 2, 3);
        int result = MaxMin.max(list, Integer::compare);
        assertThat(result, is(3));

    }

    @Test
    public void min() {
        List<Integer> list = Arrays.asList(1, 2, 3);
        int result = MaxMin.min(list, Integer::compare);
        assertThat(result, is(1));
    }
}