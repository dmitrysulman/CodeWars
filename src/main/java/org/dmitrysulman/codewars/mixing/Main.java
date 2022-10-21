package org.dmitrysulman.codewars.mixing;

public class Main {
    public static void main(String[] args) {
        String s1 = "my&friend&Paul has heavy hats! &";
        String s2 = "my friend John has many many friends &";
        String s3 = "A ss SSS d & !!   dddd q wewe W RPRR OO";
        System.out.println(Mixing.mix(s1, s2));
    }
}
