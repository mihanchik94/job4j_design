package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.Buffer;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AbuseTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();


    @Test
    public void drop() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter writer = new PrintWriter(source)) {
            writer.println("hello foolish dude");
            writer.println("java job4j php");
        }
        Abuse.drop(source.getAbsolutePath(), target.getAbsolutePath(), List.of("foolish", "php"));
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            reader.lines().forEach(rsl::append);
        }
        assertThat(rsl.toString(), is("hello dude java job4j "));
    }
}