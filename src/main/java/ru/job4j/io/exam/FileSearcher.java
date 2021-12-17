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
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileSearcher {
    private static final Pattern PATH_FOLDER = Pattern.compile("([A-Z|a-z]:\\\\[^*|\"\\n]*)");
    private static final Pattern FILE_NAME = Pattern.compile("\\w+");
    private static final Pattern FILE_TYPE = Pattern.compile("[a-z]");
    private static List<Path> result = new ArrayList<>();
    private static Predicate<Path> predicate;


    private static Predicate<Path> getPredicate(ArgsName args) {
        if (Objects.equals(args.get("t"), "name")) {
            predicate = path -> path.toFile().getName().equals(args.get("n"));
        }
        if (Objects.equals(args.get("t"), "mask")) {
            predicate = foundRegex(getMask(args));
        }
        return predicate;
    }

    private static String getMask(ArgsName args) {
        String mask = args.get("n");
        if (mask.contains("*.")) {
            mask = mask.replace("*", "^.*") + "$";
        } else {
            if (mask.contains("?.")) {
                mask = mask.replace("?", ".*\\") + "$";
            } else {
                throw new IllegalArgumentException("Mask is wrong. As an example to find all files which start on \"res\" you need to use \"res?.txt\""
                        + " And if you want to find all files of \"txt\" format you need to use \"*.txt\"");
            }
        }
        return mask;
    }

    private static Predicate<Path> foundRegex(String file) {
        return foundRegex -> {
            Pattern pattern = Pattern.compile(file);
            Matcher matcher = pattern.matcher(foundRegex.toFile().getName());
            return matcher.find();
        };
    }

    public static void handle(ArgsName args) throws IOException {
        validate(args);
        Path startFolder = Paths.get(args.get("d"));
        result = Search.search(startFolder, getPredicate(args));
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
    }

    public static void main(String[] args) throws IOException {
        handle(ArgsName.of(args));
    }
}
