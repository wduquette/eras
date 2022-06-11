package eras.checker;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

import static eras.util.Functions.*;

public class Checker {
    private Checker() {} // Uninstantiable

    //-------------------------------------------------------------------------
    // Data Types

    public interface Condition<Value> {
        /**
         * Tests whether the value meets the condition.
         * @param value The value
         * @return true or false
         */
        boolean test(Value value);


        /**
         * Requires that the value meets the condition.
         * @param value The value
         * @throws CheckFailureException if the condition is not met
         */
        void check(Value value) throws CheckFailureException;

        /**
         * The condition's signature string
         * @return The string
         */
        String signature();
    }

    //-------------------------------------------------------------------------
    // Checkers

    public static void failure(String message) throws AssertionError {
        throw new AssertionError("*** Failed: " + message);
    }

    @SafeVarargs
    public static <V> void require(V value, Condition<V>... conditions)
        throws AssertionError
    {
        try {
            for (Condition<V> condition : conditions) {
                condition.check(value);
            }
        } catch (CheckFailureException ex) {
            println("*** Failed: require(value, " + ex.condition() + ")");
            println("  Value: " + ex.value());
            throw new AssertionError("Required condition failed: "
                + ex.condition() + " for value: " + ex.value());
        }
    }

    @SafeVarargs
    public static void requireThrow(
        Runnable runnable,
        Condition<Throwable>... conditions
    )
        throws AssertionError
    {
        Throwable caught = null;

        try {
            runnable.run();
        } catch(Throwable ex) {
            caught = ex;
        }

        if (caught == null) {
            failure("Should have thrown an exception");
        }

        require(caught, conditions);
    }

    //-------------------------------------------------------------------------
    // Object Conditions

    /**
     * A condition that whether the value is null
     * @param <V> The value type
     * @return The condition
     */
    public static <V> Condition<V> isNull() {
        return define(v -> v == null, "isNull");
    }

    /**
     * A condition that checks for equality
     * @param value The expected value
     * @param <V> The value type
     * @return The condition
     */
    public static <V> Condition<V> eq(V value) {
        return define(v -> Objects.equals(v, value), "eq", value);
    }

    /**
     * A condition that checks for inequality
     * @param value The expected value
     * @param <V> The value type
     * @return The condition
     */
    public static <V> Condition<V> ne(V value) {
        return define(v -> !Objects.equals(v, value), "ne", value);
    }

    //-------------------------------------------------------------------------
    // Client Utilities

    public static <V> Condition<V> define(
        Predicate<V> predicate,
        String name,
        Object... args)
    {
        return new SimpleCondition<>(signature(name, args), predicate);
    }

    //-------------------------------------------------------------------------
    // Private Utilities

    private static void println(String message) {
        System.out.println(message);
    }
}
