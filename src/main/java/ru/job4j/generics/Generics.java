package ru.job4j.generics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Представленный ниже код содержит ошибки компиляции.
 * Необходимо их поправить, закомментировав строки которые их вызывают. При этом:
 *
 * 1-ый метод - работает без ограничений, т.е. в него можно передавать коллекцию, которая хранит любые типы.
 *
 * 2-ой метод - должен иметь ограничение сверху и ограничиваться классом Predator.
 *
 * 3-ий метод - должен иметь ограничение снизу и ограничиваться классом Predator.
 */

public class Generics {
    public static void main(String[] args) {
        Generics gen = new Generics();
        List<Animal> first = new ArrayList<>();
        List<Predator> second = new ArrayList<>();
        List<Tiger> third = new ArrayList<>();

        first.add(new Animal());
        second.add(new Predator());
        third.add(new Tiger());

        gen.printObject(first);
        gen.printObject(second);
        gen.printObject(third);
        System.out.println();

        //gen.printBoundedWildCard(first); - получаем несоответствие типов, так как мы можем выводить только объекты класса Predator и его наследников
        gen.printBoundedWildCard(second);
        gen.printBoundedWildCard(third);
        System.out.println();

        gen.printLowerBoundedWildCard(first);
        gen.printLowerBoundedWildCard(second);
        //gen.printLowerBoundedWildCard(third); - получаем несоответствие типов, так как мы можем выводить только объекты класса Predator и экземпляры объектов его суперкласса
        System.out.println();
    }

    public void printObject(List<?> list) {
        for (Iterator<?> it = list.iterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println("Текущий элемент: " + next);
        }
    }

    public void printBoundedWildCard(List<? extends Predator> list) {
        for (Iterator<? extends Predator> it = list.iterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println("Текущий элемент: " + next);
        }
    }

    public void printLowerBoundedWildCard(List<? super Predator> list) {
        for (Iterator<? super Predator> it = list.iterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println("Текущий элемент: " + next);
        }
    }
}
