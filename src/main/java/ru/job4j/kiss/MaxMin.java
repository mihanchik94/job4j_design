package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

public class MaxMin {
    public static  <T> T max(List<T> value, Comparator<T> comparator) {
        return findValue(value, comparator);
    }

    public static  <T> T min(List<T> value, Comparator<T> comparator) {
        return findValue(value, comparator.reversed());
    }

    public static  <T> T findValue(List<T> value, Comparator<T> comparator) {
        T result = value.get(0);
        for (T element : value) {
          result = comparator.compare(element, result) > 0 ? element : result;
        }
        return result;
    }
}
