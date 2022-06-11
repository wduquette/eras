package eras.util;

import eras.checker.Checker;

import java.util.List;
import java.util.function.Function;

public class Functions {
    private Functions() {} // Uninstantiable

    /**
     * Given an object, turns it into a string.  This is simply toString(),
     * except for actual Strings, which are quoted.
     * @param object The object
     * @return The string
     */
    public static String stringify(Object object) {
        if (object instanceof String) {
            return "\"" + object.toString().replace("\"", "\\\"") + "\"";
        } else {
            return object.toString();
        }
    }

    public static <T,U> List<U> map(List<T> list, Function<T,U> func) {
        return list.stream().map(func).toList();
    }

    public static String signature(String name, Object... args) {
        return name
            + "("
            + String.join(", ", map(List.of(args), Functions::stringify))
            + ")";
    }

    // Returns (a mod b), always returning a non-negative number.
    // Works around Java's default behavior.

    /**
     * Given two integers a and b, returns (a mod b) such that the result is
     * always in the range [0, b), even for negative a.
     * @param a an integer
     * @param b an integer
     * @return the modulus
     */
    public static int mod(int a, int b) {
        return (a % b + b) % b;
    }
}
