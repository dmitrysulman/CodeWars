package org.dmitrysulman.codewars.ip_integer;

public class Main {
    public static void main(String[] args) {
        System.out.println(longToIP(2154959208L));
    }

    public static String longToIP(long ip) {
        long o1 = (ip & 0b11111111000000000000000000000000) >> 24;
        long o2 = (ip & 0b00000000111111110000000000000000) >> 16;
        long o3 = (ip & 0b00000000000000001111111100000000) >> 8;
        long o4 = ip & 0b00000000000000000000000011111111;
        return o1 + "." + o2 + "." + o3 + "." + o4;
    }
}
