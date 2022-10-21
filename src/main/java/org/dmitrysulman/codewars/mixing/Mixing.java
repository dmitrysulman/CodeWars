package org.dmitrysulman.codewars.mixing;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Mixing {
    public static String mix(String s1, String s2) {
        Map<String, Long> dict1 = prepareString(s1);
        Map<String, Long> dict2 = prepareString(s2);
        List<String[]> result = new ArrayList<>();

        dict1.forEach((key, value1) -> {
            Long value2 = dict2.remove(key);
            if (value2 != null && value2 > value1) {
                result.add(new String[]{key, String.valueOf(value2), "2"});
            } else if (value2 != null && value2.equals(value1)) {
                result.add(new String[]{key, String.valueOf(value2), "="});
            } else {
                result.add(new String[]{key, String.valueOf(value1), "1"});
            }
        });
        dict2.forEach((key, value) -> result.add(new String[]{key, String.valueOf(value), "2"}));

        return result.stream()
                .sorted(
                        Comparator
                                .<String[]>comparingInt(e -> Integer.parseInt(e[1]))
                                .reversed()
                                .thenComparing(e -> e[2] + e[0])
                )
                .map(e -> e[2] +
                        ":" +
                        new String(new char[Integer.parseInt(e[1])]).replace("\0", e[0])
                )
                .collect(Collectors.joining("/"));
    }

    private static Map<String, Long> prepareString(String s1) {
        return Arrays.stream(s1.split(""))
                .filter(s -> s.matches("[a-z]"))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
