package ru.job4j.condition;

public class SqMax {
    public static int max(int first, int second) {
        return first > second ? first : second;
    }


    public static int max(int first, int second, int third, int fourth) {
        return max(max(first, second),
                max(third, fourth));
    }


    public static void main(String[] args) {
        System.out.println(max(4, 1, 7, 2));
    }
}
