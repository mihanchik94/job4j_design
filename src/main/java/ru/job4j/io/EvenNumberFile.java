package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream inputStream = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = inputStream.read()) != -1) {
                text.append((char) read);
            }
            String[] lines = text.toString().split(System.lineSeparator());
            for (String num : lines) {
                int number = Integer.parseInt(num);
                String result = number % 2 == 0 ? "this is even number: " : "this is odd number: ";
                System.out.println(result + num);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
