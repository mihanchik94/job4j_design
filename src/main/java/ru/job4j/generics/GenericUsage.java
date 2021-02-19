package ru.job4j.generics;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    }
}
