package ru.job4j.io;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static boolean validate(String[] args, Path start, int countArgs) {
        if (args.length != countArgs) {
            throw new IllegalArgumentException("Specify the source folder and the desired extension!");
        }
        if (!start.toFile().isDirectory()) {
            throw new IllegalArgumentException("Directory is not exist or is not a directory!");
        }
        return false;
    }


    public static void main(String[] args) throws IOException, URISyntaxException {
        Path start = Paths.get(args[0]);
        validate(args, start, 2);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }


    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
