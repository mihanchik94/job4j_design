package ru.job4j.it;


import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *Необходимо сделать итератор для дыумерного массива чисел
 */

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while (row < data.length) {
            if (column < data[row].length) {
                return true;
            } else {
                column = 0;
                row++;
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        } else {
            int result = data[row][column++];
            if (column == data[row].length) {
                column = 0;
                row++;
            }
            return result;
        }
    }
}
