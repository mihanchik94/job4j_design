package ru.job4j.dip;

import java.util.ArrayList;
import java.util.List;

/**
 * В данном классе представлены 3 нарушения принципа DPI:
 * 1. В классе присутствует зависимость от реализации хранилища.
 * Правильнее было бы использовать интерфейс, который отвечал бы за хранилище.
 *
 * 2. Тип данных, с которым мы работаем в хранилище, очень ограничен.
 * Лучше было бы сделать абстракнтый класс или интерфейс Bird и работать с ним.
 *
 * 3. В методе getBird(int index) также присутствует зависимость от возвращаемого объекта.
 * Гораздо лучше было бы представить его в виде абстракнтого класса или интерфейса Bird и работать с ним
 */

public class MistakesOfDPI {
    public static class Parrot {

    }

    private List<Parrot> birds = new ArrayList();

    public Parrot getBird(int index) {
      return birds.get(index);
    }
}
