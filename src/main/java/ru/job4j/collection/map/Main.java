package ru.job4j.collection.map;

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