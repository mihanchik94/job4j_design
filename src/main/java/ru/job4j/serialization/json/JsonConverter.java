package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class JsonConverter {
    public static void main(String[] args) {
        final Stranger stranger = new Stranger(true, 19, "Igor", new Passport("550-771"),
                new String[] {"Student", "Foreigner"});

        Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(stranger));

        final String strangerJson =
                "{"
                + "\"sex\":false,"
                + "\"age\":19,"
                + "\"name\":Igor,"
                + "\"passport\":"
                + "{"
                + "\"number\":\"550-771\""
                + "},"
                + "\"statuses\":"
                + "[\"Student\", \"Foreigner\"]"
                + "}";

        System.out.println(gson.fromJson(strangerJson, Stranger.class));

        JSONObject jsonPassport = new JSONObject("{\"number\":\"515-440\"}");
        List<String> list = new ArrayList<>();
        list.add("Student");
        list.add("Foreigner");
        JSONArray jsonStatuses = new JSONArray(list);

        final Stranger jsonStranger = new Stranger(true, 19, "Igor", new Passport("550-771"),
                new String[] {"Student", "Foreigner"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sex", jsonStranger.isSex());
        jsonObject.put("age", jsonStranger.getAge());
        jsonObject.put("name", jsonStranger.getName());
        jsonObject.put("passport", jsonPassport);
        jsonObject.put("statuses", jsonStatuses);

        System.out.println(jsonObject.toString());

        System.out.println(new JSONObject(jsonStranger).toString());
    }
}
