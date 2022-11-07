package org.dmitrysulman.codewars.multiples_prime;

import java.math.BigInteger;

public class SolutionNaive {
    public String findThem(String numberLimit, int[] primes) {
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
}
