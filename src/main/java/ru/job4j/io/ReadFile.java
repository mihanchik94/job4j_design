package ru.job4j.io;

import java.io.BufferedReader;

import java.io.FileReader;

public class ReadFile {
    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"))) {
            bufferedReader.lines().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
