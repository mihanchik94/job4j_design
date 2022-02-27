package ru.job4j.lsp.menu;

import java.util.List;

public interface MenuItem {
    String getName();
    List<MenuItem> getChildren();
    ActionDelegate getActionDelegate();
}
