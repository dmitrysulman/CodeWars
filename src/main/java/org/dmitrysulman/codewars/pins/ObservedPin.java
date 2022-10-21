package org.dmitrysulman.codewars.pins;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObservedPin {
    private static final Map<Character, List<Character>> adjacentDigits = new HashMap<>();

    static {
        adjacentDigits.put('0', List.of('0', '8'));
        adjacentDigits.put('1', List.of('1', '2', '4'));
        adjacentDigits.put('2', List.of('2', '1', '3', '5'));
        adjacentDigits.put('3', List.of('3', '2', '6'));
        adjacentDigits.put('4', List.of('4', '1', '5', '7'));
        adjacentDigits.put('5', List.of('5', '2', '4', '6', '8'));
        adjacentDigits.put('6', List.of('6', '3', '5', '9'));
        adjacentDigits.put('7', List.of('7', '4', '8'));
        adjacentDigits.put('8', List.of('8', '5', '7', '9', '0'));
        adjacentDigits.put('9', List.of('9', '6', '8'));
    }

    public static List<String> getPINs(String observed) {
        List<String> result = new ArrayList<>();
        findPINs(observed, "", 0, result);
        return result;
    }

    private static void findPINs(String observed, String current, int index, List<String> result) {
        if (index == observed.length()) {
            result.add(current);
            return;
        }
        Character c = observed.charAt(index);
        List<Character> currentAdjacentDigits = adjacentDigits.get(c);
        for (Character currentAdjacentDigit : currentAdjacentDigits) {
            findPINs(observed, current + currentAdjacentDigit, index + 1, result);
        }
    }
}
