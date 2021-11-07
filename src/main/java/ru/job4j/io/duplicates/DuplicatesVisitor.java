package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

import static java.nio.file.FileVisitResult.CONTINUE;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    Map<FileProperty, List<Path>> files = new HashMap();
    List<Path> duplicates = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProp = new FileProperty(attrs.size(), file.getFileName().toString());
        if (files.containsKey(fileProp)) {
            duplicates.add(file);
            files.put(fileProp, duplicates);
        } else {
            List<Path> paths = files.get(fileProp);
            files.put(fileProp, paths);
        }
        return CONTINUE;
    }

    public List<Path> getFiles() {
        return duplicates;
    }
}
