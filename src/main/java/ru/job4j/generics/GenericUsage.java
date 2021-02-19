package ru.job4j.generics;

import java.util.*;

/**
 * Generics в данном случае это запись, заключенная в скобки <>, т.е. <String>.
 * Это означает, что в коллекцию можно будет добавлять только элементы, которые являются экземплярами класса String.
 * При попытке добавить экземпляр другого класса - мы получим ошибку компиляции
 */


public class GenericUsage {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("first");
        list.add("second");
        list.add("third");
        //list.add(new Person("Igor", 12, new Date(12/02/2012))); - ошибка компиляции, однако в случае, если мы захотим вывести количество элементов коллекции, код будет работать (list.size()).
        String i = (String) list.get(1); // команда .get выводит элементы класса Object, поэтому для избежания ошибки компиляции, необходимо произвести приведение (down casting) к лассу String
        for (String s: list) {
            System.out.println("Текущий элемент " + s);
        }

        //пример реализации метода printRsl():
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        new GenericUsage().printRsl(numbers);

        //пример реализации метода printInfo():
        List<Person> per = List.of(new Person("name", 21, new Date(913716000000L)));
        new GenericUsage().printInfo(per);

        List<Programmer> pro = List.of(new Programmer("name123", 23, new Date(913716000000L)));
        new GenericUsage().printInfo(pro);

    }

    /**
     *В версии 1.5 были добавлены средства обобщенного программирования.
     *Добавим универсальный метод, который будет выводить в консоль наш список.
     *Так как Collection<Object> не является полностью родительской коллекцией всех остальных коллекций, (т.е. имеет ограничения - например, в методе main() для типа данных в списке Integer мы получим ошибку компиляции),
     *Вместо public void printRsl(Collection<Object> col) {...
     *Мы используем так называемый WildCard: public void printRsl(Collection<?> col) {...
     *В этом случае ограничений в использовании не будет.
     */

    public void printRsl(Collection<?> col) {
        System.out.println("Реализация метода printRsl:");
        for (Iterator<?> it = col.iterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println("Текущий элемент: " + next);
        }
    }

    /**
     *Рассмотрим второе стредство обобщенного программирования, которое называется Bounded Wildcard:
     *Мы создали класс Programmer, который наследуется от Person.
     *При создании метода, который позволит вывести в консоль все элементы коллекции,
     *которая может содержать объекты Person или объекты класса Programmer,
     *в случае, если мы укажем явный тип Person (public void printInfo(Collection<Person> col) {...)
     *и попытаемся добавить в коллекцию объект класса Programmer, мы получим ошибку в связи с несовместимостью типов.
     *Для того чтобы справиться с проблемой используется так называемое "Ограничение сверху" или Bounded Wildcard.
     *В этом случае вместо <Person> записывается конструкция <? extends Person> (public void printInfo(Collection<? extends Person> col) {..)
     *При таком случае ошибки компиляции не будет.
     */

    public void printInfo(Collection<? extends Person> col) {
        System.out.println("Реализация метода printInfo:");
        for (Iterator<? extends Person> it = col.iterator(); it.hasNext();) {
            Person next = it.next();
            System.out.println(next);
        }
    }



}
