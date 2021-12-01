package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CSVReader {
    private static Path path;
    private static String delimiter;
    private static String target;
    private static String filter;
    private static final StringBuilder BUILDER = new StringBuilder();

    public CSVReader(ArgsName args) {
        path = Paths.get(args.get("path"));
        delimiter = args.get("delimiter");
        target = args.get("out");
        filter = args.get("filter");
    }

    public void validate(ArgsName args) {
        if (args.getValues().size() == 0) {
            throw new IllegalArgumentException("Args length is null!");
        }
        if (args.getValues().size() != 4) {
            throw new IllegalArgumentException("Specify the source folder and the desired extension!");
        }
        if (!path.toFile().exists()) {
            throw new IllegalArgumentException("File doesn't exist");
        }
        if (!path.toFile().isFile()) {
            throw new IllegalArgumentException("File is not file");
        }
    }

    public static void handle(ArgsName args) {
        CSVReader csvReader = new CSVReader(args);
        csvReader.validate(args);
        List<Integer> numPos = new ArrayList<>();
        try (Scanner sc = new Scanner(new BufferedReader(new FileReader(path.toFile())))) {
            while (sc.hasNextLine()) {
                String[] strings = sc.nextLine().split(delimiter);
                for (int index = 0; index < strings.length; index++) {
                    if (filter.contains(strings[index])) {
                        numPos.add(index);
                    }
                }
                for (int position : numPos) {
                    BUILDER.append(strings[position]).append(";");
                }
                BUILDER.deleteCharAt(BUILDER.length() - 1);
                BUILDER.append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void result(ArgsName args) {
        handle(args);
        if ("stdout".equals(target)) {
            System.out.println(BUILDER);
        } else {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(target))) {
                writer.write(BUILDER.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
