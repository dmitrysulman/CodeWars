package org.dmitrysulman.codewars.mixing;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Mixing {
    public static String mix(String s1, String s2) {
        Map<String, Long> dict1 = prepareString(s1);
        Map<String, Long> dict2 = prepareString(s2);
        List<String> result = new ArrayList<>();

        dict1.forEach((key, value1) -> {
            Long value2 = dict2.remove(key);
            if (value2 != null && value2 > value1) {
                result.add(key + ":" + value2 + ":" + "2");
            } else if (value2 != null && value2.equals(value1)) {
                result.add(key + ":" + value2 + ":" + "=");
            } else {
                result.add(key + ":" + value1 + ":" + "1");
            }
        });
        dict2.forEach((key, value) -> result.add(key + ":" + value + ":" + 2));

        return result.stream()
                .sorted(
                        Comparator.comparing(
                                e -> e.split(":"),
                                Comparator
                                        .comparing(e -> Integer.parseInt(((String[]) e)[1]))
                                        .reversed()
                                        .thenComparing(e -> ((String[]) e)[2] + ((String[]) e)[0])
                        )
                )
                .map(entry -> {
                    String[] elements = entry.split(":");
                    StringBuilder res = new StringBuilder();
                    res.append(elements[2])
                            .append(":")
                            .append(new String(new char[Integer.parseInt(elements[1])]).replace("\0", elements[0]));
                    return res.toString();
                })
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
