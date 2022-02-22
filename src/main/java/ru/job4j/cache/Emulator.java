package ru.job4j.cache;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Emulator {
    private DirFileCache cache;

    public void showMenu() {
        System.out.println("Select an action: ");
        System.out.println("1. Show cached directory");
        System.out.println("2. Load file content into cache");
        System.out.println("3. Get file content from cache");
        System.out.println("4. Exit");
    }

    public void init(Scanner scanner) throws IOException {
        boolean isWork = true;
        while (isWork) {
            showMenu();
            int action = scanner.nextInt();
            switch (action) {
                case 1: specifyDirCache();
                break;
                case 2: loadCache();
                break;
                case 3: getContent();
                break;
                case 4: isWork = false;
                break;
                default:
                    System.out.println("Select other action");
                    System.out.println();
            }
        }
    }

    public void specifyDirCache() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Text the directory you want to cache: ");
        String path = scanner.nextLine();
        File file = new File(path);
        if (file.isDirectory()) {
            cache = new DirFileCache(path);
        } else {
            throw new IllegalArgumentException("Directory is not exist");
        }

    }

    public void loadCache() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Text the file name to load the content to the cache ");
        String path = scanner.nextLine();
        cache.get(path);
    }

    public void getContent() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Text the file name to get the content from the cache ");
        String path = scanner.nextLine();
        System.out.println(cache.get(path));
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Emulator emulator = new Emulator();
        emulator.init(scanner);
    }

}
