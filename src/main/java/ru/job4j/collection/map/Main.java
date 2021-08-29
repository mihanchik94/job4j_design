package ru.job4j.collection.map;

/*
В классе User не переопределять методы equals() и hasCode();
Создать два объекта User, которые имеют одинаковые поля.
Создать карту Map<User, Object>
Добавить две пары. В качестве ключей использовать объекты User из пункта 2 а в качестве значения new Object() .
Вывести карту на печать. Описать полученный результат словами.

Ответить на вопросы:
5.1. Объекты попали в один бакет или в разные?
5.2. Вызывался ли в этом случае equals() у объектов User или нет? Объяснить почему

6. Переопределить в классе User метод hashCode(), метод equals() не переопределять.
6.1. Объекты попали в один бакет или в разные?
6.2. Вызывался ли в этом случае equals() у объектов User или нет? Объяснить почему
 */


import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        User usr1 = new User("Igor", 3, new GregorianCalendar(1985, Calendar.MAY, 5));
        User usr2 = new User("Igor", 3, new GregorianCalendar(1985, Calendar.MAY, 5));

        Map<User, Object> map = new HashMap();

        map.put(usr1, new Object());
        map.put(usr2, new Object());


        System.out.println(usr1.hashCode());
        System.out.println(usr2.hashCode());

        map.forEach((key, value) -> System.out.println("Key : " + key + "  Value : " + value));


    }

}
