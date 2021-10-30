package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Analysis {
    public static void unavailable(String source, String target) {
        List<String[]> message = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            message = reader.lines().map(line -> line.split(" ")).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            boolean serverDown = false;
            for (String[] info : message) {
                if (!serverDown && (info[0].startsWith("500") || info[0].startsWith("400"))) {
                    serverDown = true;
                    writer.print("Сервер не работал с " + info[1]);
            }
                if (serverDown && (info[0].startsWith("200") || info[0].startsWith("300"))) {
                    serverDown = false;
                    writer.println(" по " + info[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Analysis.unavailable("./server/server.csv", "./server/unavailable.csv");

    }
}
