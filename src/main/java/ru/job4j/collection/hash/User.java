package ru.job4j.collection.hash;

/*
Переопределение Hashcode:
В самом начале исключаются поля, которые не будут присутствовать в equals.
Далее выбирается "базовое число", которое будет основой вычисления hashCode (обычно берется число 31).

Далее присваеваем переменной result значение базы и вычисляем для каждого поля в объекте свой hashCode.
Для разных типов данных он вычисляется по определенным правилам:

1. boolean: (f ? 1 : 0)
2. byte, short, int, char: (int) f
3. long: (int) (f^(f>>>32))
4. float: Float.floatToIntBits(f)
5. double: Double.doubleToIntBits(f), после этого аналогично п.3 (long)
6. null: 0
7. Массив: обрабатываем каждый элемент массива как поле объекта
8. Если ссылка на другой объект: рекурсивно вызываем hashCode()

После каждого обработанного поля объединяем его hashCode с текущим значением:
result = result * 17 + c; //c - это hashCode обработанного поля.

Ниже практический пример
 */

import java.util.Objects;

public class User {
    private String name;
    private byte age;
    private int salary;
    private long number;

    public User(String name, byte age, int salary, long number) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.number = number;
    }

    @Override
    public int hashCode() {
        int result = 31;
        result = result * 17 + name.hashCode();
        result = result * 17 + (int) age;
        result = result * 17 + salary;
        int num = (int) (number ^ (number >>> 32));
        result = result * 17 + num;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        User user = (User) obj;
        return Objects.equals(name, user.name)
                && age == user.age
                && salary == user.salary
                && number == user.number;
    }
}
