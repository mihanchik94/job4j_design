package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


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
    }
}
