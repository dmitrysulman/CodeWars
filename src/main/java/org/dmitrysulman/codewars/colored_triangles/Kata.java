package org.dmitrysulman.codewars.colored_triangles;

public class Kata {

    private static char[] letters = {'R', 'G', 'B'};

    public static char triangle(final String row) {
        return 'G';
    }

    private static int modThree(long n) {
        return (int) (n % 3);
    }

    private static int decToTer(int n) {
        int res = 0;
        int ter = 1;
        do {
            res += ter * (n % 3);
            n /= 3;
            ter *= 10;
        } while (n != 0);
        return res;
    }
}