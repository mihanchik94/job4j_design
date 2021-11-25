package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ConsoleChat {
    private final String path;
    private final String botAnswers;
    private boolean pause;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> logs = new ArrayList<>();
        List<String> answers = readPhrases();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = reader.readLine();
            logs.add(line);
            while (!OUT.equals(line)) {
                if (!isPause(line)) {
                    String answer = answers.get(new Random().nextInt(answers.size()));
                    System.out.println(answer);
                    logs.add(answer);
                }
                line = reader.readLine();
                logs.add(line);
            }
            saveLog(logs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers))) {
            phrases = reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isPause(String line) {
        if (line.equals(STOP)) {
            pause = true;
        }
    if (line.equals(CONTINUE)) {
        pause = false;
    }
        return pause;
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./src/data/log.txt", "./src/data/botAnswers.txt");
        cc.run();
    }
}
