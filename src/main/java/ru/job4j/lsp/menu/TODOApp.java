package ru.job4j.lsp.menu;

import java.util.Scanner;

public class TODOApp {
    public static void main(String[] args) {
        ActionDelegate actionDelegate = new SimpleActionDelegate();
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", actionDelegate);
        menu.add(Menu.ROOT, "Покормить собаку", actionDelegate);
        menu.add("Сходить в магазин", "Купить продукты", actionDelegate);
        menu.add("Купить продукты", "Купить хлеб", actionDelegate);
        menu.add("Купить продукты", "Купить молоко", actionDelegate);
        init(menu);
    }


    private static void init(Menu menu) {
        Scanner in = new Scanner(System.in);
        SimpleMenuPrinter simpleMenuPrinter = new SimpleMenuPrinter();
        ActionDelegate actionDelegate = new SimpleActionDelegate();
        while (true) {
            simpleMenuPrinter.print(menu);
            String str = in.nextLine();
            if (str.equals("exit")) {
                break;
            } else if (str.equals("add new task")) {
                System.out.println("Text the parentName");
                String parentName = in.nextLine();
                System.out.println("Text the childName");
                String childName = in.nextLine();
                menu.add(parentName, childName, actionDelegate);
            }
            if (menu.select(str).isPresent()) {
                menu.select(str).get().getActionDelegate().delegate();
            }
        }
    }





}
