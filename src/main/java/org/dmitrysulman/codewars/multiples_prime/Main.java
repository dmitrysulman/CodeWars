package org.dmitrysulman.codewars.multiples_prime;

public class Main {
    public static void main(String[] args) {
        SolutionNaive solutionNaive = new SolutionNaive();
        Solution solution = new Solution();
        System.out.println(solutionNaive.findThem("100000001", new int[]{2,3,5,7,11,13,17,19,23,29,31,37}));
        System.out.println(solution.findThem("100000001", new int[]{2,3,5,7,11,13,17,19,23,29,31,37}));
    }
}