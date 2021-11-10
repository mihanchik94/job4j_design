package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

import static java.nio.file.FileVisitResult.CONTINUE;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    Map<FileProperty, List<Path>> files = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProp = new FileProperty(attrs.size(), file.getFileName().toString());
        if (files.containsKey(fileProp)) {
            List<Path> duplicates = new ArrayList<>(files.get(fileProp));
            duplicates.add(file);
            files.put(fileProp, duplicates);
        } else {
            List<Path> paths = new ArrayList<>();
            paths.add(file);
            files.put(fileProp, paths);
        }
        return CONTINUE;
    }


    public List<Path> getFiles() {
        List<Path> duplicates = new ArrayList<>();
        files.values().stream().
                filter(list -> list.size() > 1)
                .forEach(duplicates :: addAll);
        return duplicates;
    }
}
