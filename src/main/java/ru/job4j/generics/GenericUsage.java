package ru.job4j.generics;

import java.io.OutputStream;
import java.util.*;


public class GenericUsage {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("first");
        list.add("second");
        list.add("third");
        String i = (String) list.get(1);
        for (String s: list) {
            System.out.println("Текущий элемент " + s);
        }

        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        new GenericUsage().printRsl(numbers);

        List<Person> per = List.of(new Person("name", 21, new Date(913716000000L)));
        new GenericUsage().printInfo(per);

        List<Programmer> pro = List.of(new Programmer("name123", 23, new Date(913716000000L)));
        new GenericUsage().printInfo(pro);

        List<? super Integer> lst = new ArrayList<>();
        new GenericUsage().addAll(lst);

    }

    public void printRsl(Collection<?> col) {
        System.out.println("Реализация метода printRsl:");
        for (Iterator<?> it = col.iterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println("Текущий элемент: " + next);
        }
    }

    public void printInfo(Collection<? extends Person> col) {
        System.out.println("Реализация метода printInfo:");
        for (Iterator<? extends Person> it = col.iterator(); it.hasNext();) {
            Person next = it.next();
            System.out.println(next);
        }
    }

    public void addAll(List<? super Integer> list) {
        System.out.println("Реализация метода addAll:");
        for (int i = 0; i <= 5; i++) {
            list.add(i);
        }
        for (Object o : list) {
            System.out.println("Текущий элемент: " + o);
        }
    }



}
