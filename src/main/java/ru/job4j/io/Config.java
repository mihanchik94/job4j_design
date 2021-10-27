package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();


    public Config(String path) {
        this.path = path;
    }


    public String[] splitConfig(String path) {
        String[] result = path.split("=");
        if (result.length != 2 || result[0].length() == 0 || !path.contains("=")) {
            throw new IllegalArgumentException("Config file Error!");
        }
        return result;
    }

    public void load() {
        try (BufferedReader in = new BufferedReader(new FileReader(this.path))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                if (line.contains("#") || line.length() == 0) {
                    continue;
                }
                String[] res = splitConfig(line);
                values.put(res[0], res[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }
}
