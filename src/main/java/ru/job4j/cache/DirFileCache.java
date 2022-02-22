package ru.job4j.cache;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DirFileCache extends AbstractCache<String, String> {
    private final String cacheDir;

    public DirFileCache(String cacheDir) {
        this.cacheDir = cacheDir;
    }


    @Override
    protected String load(String key) {
        String text = null;
        Path path = Path.of(cacheDir + File.separator + key);
        try {
            text = Files.readString(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }
}
