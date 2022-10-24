package org.dmitrysulman.codewars.topwords;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TopWords {
    public static List<String> top3(String s) {
        return Arrays.stream(s.toLowerCase().split("[^a-z']+"))
                .filter(w -> !w.isEmpty())
                .filter(w -> !w.matches("^'+$"))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .limit(3)
                .map(Map.Entry::getKey)
                .toList();
    }
}
