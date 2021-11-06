package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor visitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("C:\\Users\\Michael\\Desktop\\test"), visitor);
        printResult(visitor);
    }

    public static void printResult(DuplicatesVisitor visitor) {
        visitor.getFiles().entrySet().iterator().next().getValue().forEach(System.out::println);
    }
}
