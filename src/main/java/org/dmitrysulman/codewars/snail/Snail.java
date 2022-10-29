package org.dmitrysulman.codewars.snail;

import java.util.ArrayList;
import java.util.List;

public class Snail {
    public static int[] snail(int[][] array) {
        int n = array.length;
        List<Integer> result = new ArrayList<>();
        for (int i : array[0]) {
            result.add(i);
        }
        int i = 1;
        int x = 0;
        int y = n - 1;
        int direction;
        n--;
        while (n > 0) {
            direction = i % 4;
            switch (direction) {
                case 0 -> {
                    goRight(array, n, x, y, result);
                    y += n;
                }
                case 1 -> {
                    goDown(array, n, x, y, result);
                    x += n;
                }
                case 2 -> {
                    goLeft(array, n, x, y, result);
                    y -= n;
                }
                case 3 -> {
                    goUp(array, n, x, y, result);
                    x -= n;
                }
            }
            i++;
            if (i % 2 == 1) {
                n--;
            }
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    private static void goRight(int[][] array, int n, int x, int y, List<Integer> result) {
        for (int i = y + 1; i <= y + n; i++) {
            result.add(array[x][i]);
        }
    }

    private static void goDown(int[][] array, int n, int x, int y, List<Integer> result) {
        for (int i = x + 1; i <= x + n; i++) {
            result.add(array[i][y]);
        }
    }

    private static void goLeft(int[][] array, int n, int x, int y, List<Integer> result) {
        for (int i = y - 1; i >= y - n; i--) {
            result.add(array[x][i]);
        }
    }

    private static void goUp(int[][] array, int n, int x, int y, List<Integer> result) {
        for (int i = x - 1; i >= x - n; i--) {
            result.add(array[i][y]);
        }
    }
}
