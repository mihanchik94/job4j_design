package ru.job4j.collection.hash;

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
