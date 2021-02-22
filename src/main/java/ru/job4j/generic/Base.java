package ru.job4j.generic;

/**
 * Необходимо реализовать контейнеры для хранения объектов.
 * Структура контейнеров будет одинаковая. Все ограничения хранимых типов мы должны задать с помощью Generics.
 * Контейнеры должны быть описаны интерфейсом Store.
 * Универсальное хранилище MemStore
 * Реализация для пользователя UserStore. Будем использовать композицию объектов.
 */



public abstract class Base {
    private final String id;

    public Base(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
