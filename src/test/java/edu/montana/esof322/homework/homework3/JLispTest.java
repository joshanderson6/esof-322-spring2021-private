package edu.montana.esof322.homework.homework3;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class JLispTest {

    /*====================================================================
    // Homework 3
    //
    // Included in this directory is a simple lisp implementation.  This
    // lisp implementation supports basic math, akin to what our reverse
    // polish notation calculator did.
    //
    // The intention with this lisp is to support simple, binary addition
    // in the form:
    //
    //  (+ 1 2)
    //
    //  or
    //
    //  (+ 1 (+ 2 3))
    //
    //
    // The `+` operator can take two and only two arguments, and must be
    // enclosed in parenthesis to be valid.
    //
    // This implementation is buggy.  Your assignment is to create four (4)
    // tests showing *different* bugs within the simple language.
    //
    //====================================================================*/

    @Test
    // Testing for input with more than 2 arguments
    public void manyArgumentsTest() {
        JLisp lisp = new JLisp();
        try {
            Integer integer = lisp.eval("( + 1 1 1)");
            fail("item left in stack");
        }
        catch (IllegalArgumentException e) {
            // pass
        }
    }

    @Test
    // Testing for input without parentheses
    public void noParenthesesTest() {
        JLisp lisp = new JLisp();
        try {
            lisp.eval("+ 1 1");
            fail("format of input was wrong (needs parentheses)");
        }
        catch (IllegalArgumentException e) {
            // pass
        }
    }

    @Test
    // Testing for input with RPN format
    public void wrongOrderTest() {
        JLisp lisp = new JLisp();
        try {
            lisp.eval("(1 1 +)");
            fail("order of values is wrong");
        }
        catch (IllegalArgumentException e) {
            // pass
        }
    }

    @Test
    // Testing null input
    public void nullTest() {
        JLisp lisp = new JLisp();
        try {
            lisp.eval(null);
            fail("null value should return illegal argument exception");
        }
        catch (IllegalArgumentException e) {
            // pass
        }
    }

    @Test
    // This is a sample test to help you get started
    public void exampleTest() {
        assertEquals(1, 2);
        fail("test didn't pass");
        assertEquals("Foo", "Bar");
    }

}
