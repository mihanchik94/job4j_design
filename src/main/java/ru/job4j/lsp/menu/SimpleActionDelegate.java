package ru.job4j.lsp.menu;

public class SimpleActionDelegate implements ActionDelegate {
    @Override
    public void delegate() {
        System.out.println("Do this task!");
    }
}
