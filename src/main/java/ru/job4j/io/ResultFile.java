package ru.job4j.io;

import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            out.write("Hello world!".getBytes(StandardCharsets.UTF_8));
            out.write(System.lineSeparator().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
