package ru.job4j.dip;


import ru.job4j.serialization.json.A;

import java.util.ArrayList;

/**
 * В данном классе представлены следующие нарушения принципа DPI:
 * 1. В Методе calculate: Зависимость от консольного вывода. В качестве решения можно использовать абстракцию для логирования.
 *
 * 2. В классе присутствует зависимость от реализации хранилища.
 *
 * 3. Даже если предположить, что мы откидываем пункт 2, и по какой-то причине нам нужно использовать хранилище таким образом, то
 * хранилище, с которым предполагается работать, не представляет выбора реализации интерфейса List.
 */

public class MistakesOfDPISecond {
    public static int calculate(int a, int b) {
        if (b == 0) {
            System.out.println("Error! Division by zero");
        }
        return a / b;
    }

    public static class Milk {

    }

    ArrayList<Milk> food = new ArrayList<>();
}
