package org.dmitrysulman.codewars.valid_parentheses;

import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) {
        System.out.println(validParentheses("()"));
        System.out.println(validParentheses(")(()))"));
        System.out.println(validParentheses("("));
        System.out.println(validParentheses("(())((()())())"));
        System.out.println(validParentheses("())"));
        System.out.println(validParentheses("32423(sgsdg)"));
        System.out.println(validParentheses("(dsgdsg))2432"));
        System.out.println(validParentheses("adasdasfa"));
    }

    public static boolean validParentheses(String parens) {
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < parens.length(); i++) {
            char c = parens.charAt(i);
            switch (c) {
                case '(' -> stack.push(c);
                case ')' -> {
                    if (stack.isEmpty()) {
                        return false;
                    }
                    stack.pop();
                }
            }
        }
        return stack.isEmpty();
    }
}
