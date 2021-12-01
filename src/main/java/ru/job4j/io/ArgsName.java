package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }


    public Map<String, String> getValues() {
        return values;
    }

    private void parse(String[] args) {
        if (!validateArray(args)) {
            for (String arg : args) {
                if (validateValue(arg)) {
                    String[] res = arg.replaceAll("-", "").split("=");
                    values.put(res[0], res[1]);
                    }
                }
            }
        }

    public static boolean validateArray(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments are not found");
        }
        return false;
    }

    public static boolean validateValue(String arg) {
        if (!arg.startsWith("-") || !arg.contains("=") || arg.startsWith("=") || arg.endsWith("=") || arg.startsWith("-=")) {
            throw new IllegalArgumentException("Arguments are not found");
        }
        return true;
    }



    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
