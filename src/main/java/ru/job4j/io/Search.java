package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static boolean validate(String[] args, int countArgs) {
        Path path = Path.of(args[0]);
        if (args.length != countArgs) {
            throw new IllegalArgumentException("Specify the source folder and the desired extension!");
        }
        if (!Files.exists(path) || !Files.isDirectory(path)) {
            throw new IllegalArgumentException("Directory is not exist or is not a directory!");
        }
        return false;
    }


    public static void main(String[] args) throws IOException {
        if (!Search.validate(args, 2)) {
            Path start = Paths.get(args[0]);
            search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
