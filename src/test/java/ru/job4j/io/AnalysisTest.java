package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AnalysisTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void unavailable() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter writer = new PrintWriter(source)) {
            writer.println("200 10:56:01");
            writer.println("200 10:57:01");
            writer.println("200 10:58:01");
            writer.println("300 10:59:01");
            writer.println("400 11:00:01");
            writer.println("500 11:01:01");
            writer.println("300 11:02:01");
        }
        Analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            reader.lines().forEach(result::append);
        }
        assertThat(result.toString(), is("Сервер не работал с 11:00:01 по 11:02:01"));
    }
}