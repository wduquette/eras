package eras.util;

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

    /**
     * Given a list {a1, a2, ...} and a function f, return the list
     * {f(a1), f(a2), ...}.
     * @param list The list
     * @param func the function
     * @param <Domain> The element type for the input list
     * @param <Range> The element type for the output list.
     * @return
     */
    public static <Domain, Range> List<Range> map(
        List<Domain> list,
        Function<Domain, Range> func)
    {
        return list.stream().map(func).toList();
    }

    /**
     * Given a method name and the method's arguments, returns a string that
     * describes the call: "name(arg, arg, ...)".  The arguments are stringified
     * by the {@code stringify} method.
     * @param name The name
     * @param args The arguments
     * @return The string.
     */
    public static String signature(String name, Object... args) {
        return name
            + "("
            + String.join(", ", map(List.of(args), Functions::stringify))
            + ")";
    }

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
