package ru.job4j.io.scanner;

import java.io.*;
import java.util.Scanner;

public class ScannerExample3 {
    public static void main(String[] args) throws Exception {
        String data = "A 1B FF 110";
        File file = File.createTempFile("data", null);
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file))) {
            out.write(data.getBytes());
        }
        try (Scanner scanner = new Scanner(file).useRadix(16)) {
            while (scanner.hasNextInt()) {
                System.out.print(scanner.nextInt());
                System.out.print(" ");
            }
        }
    }
}
