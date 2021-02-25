package ru.job4j.list;

import java.util.List;

public class ListPrinter {
    public ListPrinter(List<String> list) {
        for (String s : list) {
            System.out.println("Текущий элемент: " + s);
        }
    }
}
