package ru.job4j.serialization.json;

import java.util.Arrays;

public class Stranger {
    private boolean sex;
    private int age;
    private String name;
    private Passport passport;
    private String[] statuses;

    public Stranger(boolean sex, int age, String name, Passport passport, String[] statuses) {
        this.sex = sex;
        this.age = age;
        this.name = name;
        this.passport = passport;
        this.statuses = statuses;
    }

    public boolean isSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public Passport getPassport() {
        return passport;
    }

    public String[] getStatuses() {
        return statuses;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public void setStatuses(String[] statuses) {
        this.statuses = statuses;
    }

    @Override
    public String toString() {
        return "Stranger{"
                + "sex=" + sex
                + ", age=" + age
                + ", name='" + name + '\''
                + ", passport=" + passport
                + ", statuses=" + Arrays.toString(statuses)
                + '}';
    }
}
