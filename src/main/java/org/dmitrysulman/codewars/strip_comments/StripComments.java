package org.dmitrysulman.codewars.strip_comments;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StripComments {
    public static String stripComments(String text, String[] commentSymbols) {
        String pattern = Arrays.asList(commentSymbols).toString().replace(", ", "").concat("+.*");
        String[] lines = text.split("\\n");
        return Arrays.stream(lines)
                .map(line -> line.replaceAll(pattern, ""))
                .map(String::stripTrailing)
                .collect(Collectors.joining("\n"));
    }
}
