package ru.job4j.io;

import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class MultipleResultFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("multipleResult.txt")) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter number");
            int number = scanner.nextInt();
            for (int i = 1; i < number; i++) {
                for (int j = 1; j < number; j++) {
                    out.write(String.format("%4d", i * j).getBytes(StandardCharsets.UTF_8));
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
