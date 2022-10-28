package org.dmitrysulman.codewars.battlefield;

import java.util.HashMap;
import java.util.Map;

public class BattleField {

    public static boolean fieldValidator(int[][] field) {
        Map<Integer, Integer> ships = new HashMap<>();
        for (int i = 1; i <= 4; i++) {
            ships.put(i, 5 - i);
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (field[i][j] == 1) {
                    if (!checkShip(i, j, field, ships)) {
                        return false;
                    }
                }
            }
        }
        long count = ships.values().stream()
                .filter(value -> value != 0)
                .count();
        return count == 0;
    }

    private static boolean checkShip(int startH, int startV, int[][] field, Map<Integer, Integer> ships) {
        int length = 1;
        if (startV < 9 && field[startH][startV + 1] == 1) {
            // horizontal
            while (startV + length < 10 && field[startH][startV + length] == 1) {
                length++;
                if (length == 5) {
                    return false;
                }
            }
            if (checkShipsCount(length, ships)) return false;
            return setShipHorizontal(startH, startV, length, field, ships);
        } else {
            //vertical or single
            while (startH + length < 10 && field[startH + length][startV] == 1) {
                length++;
                if (length == 5) {
                    return false;
                }
            }
            if (checkShipsCount(length, ships)) return false;
            return setShipVertical(startH, startV, length, field, ships);
        }
    }

    private static boolean setShipHorizontal(int startH, int startV, int length, int[][] field, Map<Integer, Integer> ships) {
        for (int i = startH - 1; i <= startH + 1; i++) {
            if (i < 0 || i > 9) {
                continue;
            }
            for (int j = startV - 1; j <= startV + length; j++) {
                if (j < 0 || j > 9 || (i == startH && j >= startV && j < startV + length)) {
                    continue;
                }
                if (!checkSquareAndSet(field, i, j)) {
                    return false;
                }
            }
        }
        for (int i = startV; i < startV + length; i++) {
            field[startH][i] = 2;
        }
        ships.compute(length, (key, value) -> value - 1);
        return true;
    }

    private static boolean setShipVertical(int startH, int startV, int length, int[][] field, Map<Integer, Integer> ships) {
        for (int i = startH - 1; i <= startH + length; i++) {
            if (i < 0 || i > 9) {
                continue;
            }
            for (int j = startV - 1; j <= startV + 1; j++) {
                if (j < 0 || j > 9 || (j == startV && i >= startH && i < startH + length)) {
                    continue;
                }
                if (!checkSquareAndSet(field, i, j)) {
                    return false;
                }
            }
        }
        for (int i = startH; i < startH + length; i++) {
            field[i][startV] = 2;
        }
        ships.compute(length, (key, value) -> value - 1);
        return true;
    }

    private static boolean checkSquareAndSet(int[][] field, int i, int j) {
        if (field[i][j] == 1) {
            return false;
        } else {
            field[i][j] = 2;
        }
        return true;
    }

    private static boolean checkShipsCount(int length, Map<Integer, Integer> ships) {
        return ships.get(length) == 0;
    }
}
