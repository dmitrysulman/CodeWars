package org.dmitrysulman.codewars.multiples_prime;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.*;

public class Solution {
    public String findThemNaive(String numberLimit, int[] primes) {
        BigInteger n = new BigInteger(numberLimit);
        BigInteger i = BigInteger.TWO;
        BigInteger result = BigInteger.ZERO;
        while (i.compareTo(n) < 0) {
            for (int prime : primes) {
                if (i.mod(BigInteger.valueOf(prime)).equals(BigInteger.ZERO)) {
                    result = result.add(i);
                    break;
                }
            }
            i = i.add(BigInteger.ONE);
        }
        return result.toString();
    }

    public String findThem(String numberLimit, int[] primes) {
        BigInteger n = new BigInteger(numberLimit);
        Map<Integer, Integer> corrections = new HashMap<>();
//        List<Integer> corrections = new ArrayList<>();
        BigInteger result = BigInteger.valueOf(0L);
        for (int i = 0; i < primes.length; i++) {
//            List<Integer> tempCorrections = new ArrayList<>();
            Map<Integer, Integer> tempCorrections = new HashMap<>();
            BigInteger progressionSum = getProgressionSum(n, primes[i]);
            final int y = primes[i];
            System.out.println("i: " + i + ", primes[i]: " + primes[i] + ", psum before: " + progressionSum);
//            corrections.forEach(e -> tempCorrections.add(e * y));
//            corrections.addAll(tempCorrections);
            corrections.forEach((k, v) -> tempCorrections.put(k * y, v + 1));
            corrections.putAll(tempCorrections);
            BigInteger subtract = BigInteger.ZERO;
            for (int j = 0; j < i; j++) {
//                System.out.println(primes[i] * primes[j]);
                corrections.put(primes[i] * primes[j], 2);
                //subtract = subtract.add(getProgressionSum(n, primes[i] * primes[j]));
                progressionSum = progressionSum.subtract(getProgressionSum(n, primes[i] * primes[j]));
//                corrections.add(primes[i] * primes[j]);
                System.out.println(primes[i] + ": " + primes[j] + ", " + corrections);
            }
            System.out.println("i: " + i + ", primes[i]: " + primes[i] + ", psum after: " + progressionSum);
//            Iterator<Map.Entry<Integer, Integer>> iterator = corrections.entrySet().iterator();
//            while (iterator.hasNext()) {
//                Map.Entry<Integer, Integer> correction = iterator.next();
//                if (correction.getValue() > 2 && correction.getKey() % primes[i] == 0) {
//                    System.out.println("Inside if, prime: " + primes[i] + ", value: " + correction.getValue() + ", key: " + correction.getKey());
//                    subtract = subtract.subtract(getProgressionSum(n, correction.getKey()));
////                    iterator.remove();
//                }
//            }
            //progressionSum = progressionSum.subtract(subtract);
            result = result.add(progressionSum);
//            if (i >= 2) {
//                int add = 1;
//                for (int j = 0; j <= i; j++) {

//                    add *= primes[j];
//                }
//                result = result.add(getProgressionSum(n, add).multiply(BigInteger.valueOf(i - 1)));
//            }
        }
        System.out.println(corrections);

        BigInteger add = BigInteger.ZERO;

//        System.out.println("Subtract before: " + subtract);
        for (Map.Entry<Integer,Integer> correction: corrections.entrySet()) {
//                if (correction.getValue() > 2 && correction.getKey() % primes[i] == 0) {
            if (correction.getValue() == 3) {
//                System.out.println("Inside , value: " + correction.getValue() + ", key: " + correction.getKey());
                add = add.add(getProgressionSum(n, correction.getKey()));
            }
        }
        System.out.println("Add after: " + add);
        result = result.add(add);
        for (Map.Entry<Integer, Integer> correction : corrections.entrySet()) {
            int num = correction.getValue() - 1;
            if (correction.getKey() != 210) {
//                System.out.println("hi! " + (num * (num - 1) / 2));
//                result = result.add(getProgressionSum(n, correction.getKey()).multiply(BigInteger.valueOf((long) num * (num - 1) / 2)));
            }
            if (correction.getValue() > 2 && correction.getValue() < primes.length) {
                //result = result.add(getProgressionSum(n, correction.getKey()));
            }
//            result = result.add(BigInteger.valueOf(correction.getKey()).multiply(BigInteger.valueOf((long) num * (num - 1) / 2)));
        }
//        long finalMinus = Arrays.stream(primes).reduce(1L, (a, b) -> (long) a * (long) b);
//        long finalMinus = 1L;
        int finalMinus = 1;
        for (int prime : primes) {
            finalMinus *= prime;
        }
        System.out.println(finalMinus);
        System.out.println(getProgressionSum(n, finalMinus));
        System.out.println(getProgressionSum(n, finalMinus).multiply(BigInteger.valueOf(4)));
//        result = result.subtract(getProgressionSum(n, finalMinus).multiply(BigInteger.valueOf(4)));
//        result = result.subtract(BigInteger.valueOf(finalMinus));

//        for (int i = 0; i < primes.length - 1; i++) {
//            for (int j = i + 1; j < primes.length; j++) {
//                result = result.subtract(getProgressionSum(n, primes[i] * primes[j]));
//            }
//            if (i >= 2) {
//                int add = 1;
//                for (int j = 0; j <= i; j++) {
//                    add *= primes[j];
//                }
//                result = result.add(getProgressionSum(n, add).multiply(BigInteger.valueOf(i - 1)));
//            }
//        }
//        System.out.println();
        return result.toString();
    }

    private BigInteger getProgressionSum(BigInteger n, int prime) {
        BigInteger bigInteger = new BigDecimal(n).divide(new BigDecimal(prime), RoundingMode.CEILING)
                .toBigInteger()
                .subtract(BigInteger.valueOf(1L));
        return bigInteger.multiply(bigInteger.add(BigInteger.valueOf(1L))).multiply(BigInteger.valueOf(prime)).divide(BigInteger.valueOf(2L));
    }
}
