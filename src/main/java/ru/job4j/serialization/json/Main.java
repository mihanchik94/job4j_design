package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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

        /* JSONObject из json-строки строки */
        JSONObject jsonContact = new JSONObject("{\"phone\":\"+7(924)111-111-11-11\"}");

        /* JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("Student");
        list.add("Free");
        JSONArray jsonStatuses = new JSONArray(list);

        /* JSONObject напрямую методом put */
        final Person jsonPerson = new Person(false, 30, new Contact("11-111"), new String[] {"Worker, Married"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sex", jsonPerson.isSex());
        jsonObject.put("age", jsonPerson.getAge());
        jsonObject.put("contact", jsonContact);
        jsonObject.put("statuses", jsonStatuses);

        /* Выведем результат в консоль */
        System.out.println(jsonObject.toString());

        /* Преобразуем объект person в json-строку */
        System.out.println(new JSONObject(jsonPerson).toString());
    }
}
