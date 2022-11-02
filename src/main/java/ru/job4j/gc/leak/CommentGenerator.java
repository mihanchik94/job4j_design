package ru.job4j.gc.leak;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CommentGenerator implements Generate {
    public static final String PATH_PHRASES = "src/main/java/ru/job4j/gc/leak/files/phrases.txt";

    public static final String SEPARATOR = System.lineSeparator();
    private static List<Comment> comments = new ArrayList<>();
    public static final Integer COUNT = 50;
    private static List<String> phrases;
    private UserGenerator userGenerator;
    private Random random;

    public CommentGenerator(Random random, UserGenerator userGenerator) {
        this.userGenerator = userGenerator;
        this.random = random;
        read();
    }

    private void read() {
        try {
            phrases = read(PATH_PHRASES);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Comment> getComments() {
        return comments;
    }

    @Override
    public void generate() {
        comments.clear();
        for (int i = 0; i < COUNT; i++) {
            StringBuilder comment = new StringBuilder();
            comment.append(phrases.get(random.nextInt(phrases.size())))
                    .append(SEPARATOR)
                    .append(phrases.get(random.nextInt(phrases.size())))
                    .append(SEPARATOR)
                    .append(phrases.get(random.nextInt(phrases.size())));
            comments.add(new Comment(comment.toString(),
                    userGenerator.randomUser()));
        }
    }
}