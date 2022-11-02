package org.dmitrysulman.codewars.string_incrementer;

import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Kata {
    public static String incrementString(String str) {
        String result;
        Pattern pattern = Pattern.compile("(\\d+)$");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            String stringDigit = matcher.group(1);
            int length = stringDigit.length();
            BigInteger digit = new BigInteger(stringDigit);
            digit = digit.add(BigInteger.ONE);
            stringDigit = String.format("%0" + length + "d", digit);
            result = str.replaceAll("(\\d+)$", "") + stringDigit;
        } else {
            result = str + "1";
        }
        return result;
    }
}
