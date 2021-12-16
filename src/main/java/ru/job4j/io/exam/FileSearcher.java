package ru.job4j.io.exam;

import ru.job4j.io.ArgsName;
import ru.job4j.io.Search;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileSearcher {
    private static final Pattern PATH_FOLDER = Pattern.compile("([A-Z|a-z]:\\\\[^*|\"\\n]*)");
    private static final Pattern FILE_NAME = Pattern.compile("\\w+");
    private static final Pattern FILE_TYPE = Pattern.compile("[a-z]");
    private static final Pattern SEARCH_RESULT = Pattern.compile("[a-zA-Z0-9\\\\.a-z]");
    private static List<Path> result = new ArrayList<>();


    public static void handle(ArgsName args) throws IOException {
        validate(args);
        Path startFolder = Paths.get(args.get("d"));
        if (Objects.equals(args.get("t"), "name")) {
            result = Search.search(startFolder, path -> path.toFile().getName().equals(args.get("n")));
        }
        if (Objects.equals(args.get("t"), "mask")) {
            result = Search.search(startFolder, path -> path.toFile().getName().endsWith(args.get("n").substring(1)));
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(args.get("o")))) {
            for (Path file : result) {
                writer.write(file.toString());
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void validate(ArgsName args) {
        Matcher folderMatcher = PATH_FOLDER.matcher(args.get("d"));
        Path pathFolder = Paths.get(args.get("d"));
        if (!pathFolder.toFile().isDirectory() && !folderMatcher.find()) {
            throw new IllegalArgumentException("Search folder specified incorrectly");
        }
        Matcher fileNameMatcher = FILE_NAME.matcher(args.get("n"));
        if (!fileNameMatcher.find()) {
            throw new IllegalArgumentException("Search parameters are incorrect");
        }
        Matcher searchTypeMatcher = FILE_TYPE.matcher(args.get("t"));
        if (!searchTypeMatcher.find()) {
            throw new IllegalArgumentException("Search type specified incorrectly");
        }
        Matcher searchResultMatcher = SEARCH_RESULT.matcher(args.get("o"));
        Path resultFile = Paths.get(args.get("o"));
        if (!resultFile.toFile().exists() && searchResultMatcher.find()) {
            throw new IllegalArgumentException("The file is specified incorrectly");
        }
    }

    public static void main(String[] args) throws IOException {
        handle(ArgsName.of(args));
    }
}
