package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final Person person = new Person(false, 30, new Contact("35-35"), new String[] {"Worker, Married"});

        /*Преобразуем объект person в json-строку*/
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(person));

        /*Модифицируем json-строку*/
        final String personJson =
                "{"
                + "\"sex\":false,"
                + "\"age\":28,"
                + "\"contact\":"
                + "{"
                + "\"phone\":\"+86157458965\""
                + "},"
                + "\"statuses\":"
                + "[\"Student\", \"Free\"]"
                + "}";
        final Person personMod = gson.fromJson(personJson, Person.class);
        System.out.println(personMod);
    }
}
