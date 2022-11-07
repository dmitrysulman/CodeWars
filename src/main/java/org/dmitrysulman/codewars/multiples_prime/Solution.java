package org.dmitrysulman.codewars.multiples_prime;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public String findThem(String numberLimit, int[] primes) {
        int n = primes.length;
        BigInteger number = new BigInteger(numberLimit);
        BigInteger result = BigInteger.valueOf(0L);
        for (int prime : primes) {
            BigInteger progressionSum = getProgressionSum(number, BigInteger.valueOf(prime));
            result = result.add(progressionSum);
        }

        for (int i = 2; i <= n; i++) {
            List<int[]> combinations = generateCombinations(n, i);
            for (int[] combination : combinations) {
                BigInteger multiply = multiply(combination, primes);
                BigInteger progressionSum = getProgressionSum(number, multiply);
                if (i % 2 == 0) {
                    result = result.subtract(progressionSum);
                } else {
                    result = result.add(progressionSum);
                }
            }
        }
        return result.toString();
    }

    private BigInteger getProgressionSum(BigInteger n, BigInteger num) {
        BigInteger bigInteger = new BigDecimal(n).divide(new BigDecimal(num), RoundingMode.CEILING)
                .toBigInteger()
                .subtract(BigInteger.valueOf(1L));
        return bigInteger.multiply(bigInteger.add(BigInteger.valueOf(1L))).multiply(num).divide(BigInteger.valueOf(2L));
    }

    private BigInteger multiply (int[] combination, int[] numbers) {
        BigInteger res = BigInteger.ONE;
        for (int index : combination) {
            res = res.multiply(BigInteger.valueOf(numbers[index]));
        }
        return res;
    }

    public List<int[]> generateCombinations(int n, int r) {
        List<int[]> combinations = new ArrayList<>();
        int[] combination = new int[r];

        // initialize with lowest lexicographic combination
        for (int i = 0; i < r; i++) {
            combination[i] = i;
        }

        while (combination[r - 1] < n) {
            combinations.add(combination.clone());

            // generate next combination in lexicographic order
            int t = r - 1;
            while (t != 0 && combination[t] == n - r + t) {
                t--;
            }
            combination[t]++;
            for (int i = t + 1; i < r; i++) {
                combination[i] = combination[i - 1] + 1;
            }
        }

        return combinations;
    }
}
