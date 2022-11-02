package org.dmitrysulman.codewars.string_incrementer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KataTest {
    private static void doTest(String str, String expected) {
        assertEquals(expected, Kata.incrementString(str), "input: <"+str+">");
    }

    @Test
    public void exampleTests() {
        doTest("foobar000", "foobar001");
        doTest("foo", "foo1");
        doTest("foobar001", "foobar002");
        doTest("foobar99", "foobar100");
        doTest("foobar099", "foobar100");
        doTest("", "1");
    }
}