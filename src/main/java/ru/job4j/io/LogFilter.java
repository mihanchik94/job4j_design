package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> result = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            bufferedReader.lines().filter(str -> str.contains(" 404 ")).forEach(result::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter printWriter = new PrintWriter(new BufferedOutputStream(new FileOutputStream(file)))) {
            log.forEach(printWriter :: println);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        System.out.println(log);
        save(log, "404.txt");
    }

}
