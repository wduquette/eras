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

    /**
     * A runnable that can throw any exception.
     */
    public interface ThrowingRunnable {
        void run() throws Throwable;
    }

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
        ThrowingRunnable runnable,
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
     * This condition checks whether value's class is exactly the given
     * class.
     * @param cls The class
     * @param <V> The value type
     * @return The condition
     */
    public static <V> Condition<V> isa(Class<?> cls) {
        return define(v -> v != null && v.getClass() == cls,
            "isa", cls);
    }

    /**
     * This condition checks whether value's class is assignable to
     * a variable of the given class.
     * @param cls The class
     * @param <V> The value type
     * @return The condition
     */
    public static <V> Condition<V> isAssignableTo(Class<?> cls) {
        return define(v -> v != null && cls.isAssignableFrom(v.getClass()),
            "isAssignableTo", cls);
    }

    /**
     * This condition checks whether the value is null
     * @param <V> The value type
     * @return The condition
     */
    public static <V> Condition<V> isNull() {
        return define(v -> v == null, "isNull");
    }

    /**
     * Checks that the value is not null
     * @param <V> The value type
     * @return The condition
     */
    public static <V> Condition<V> isNotNull() {
        return define(v -> v != null, "isNotNull");
    }

    /**
     * This condition checks that the value equals the expected value.
     * @param value The expected value
     * @param <V> The value type
     * @return The condition
     */
    public static <V> Condition<V> eq(V value) {
        return define(v -> Objects.equals(v, value), "eq", value);
    }

    /**
     * This condition checks that the value is not equal to the expected value.
     * @param value The expected value
     * @param <V> The value type
     * @return The condition
     */
    public static <V> Condition<V> ne(V value) {
        return define(v -> !Objects.equals(v, value), "ne", value);
    }

    //-------------------------------------------------------------------------
    // Boolean Conditions

    /**
     * Checks that the value is true.
     * @return The condition
     */
    public static Condition<Boolean> isTrue() {
        return define(flag -> flag, "isTrue");
    }

    /**
     * Checks that the value is false.
     * @return The condition
     */
    public static Condition<Boolean> isFalse() {
        return define(flag -> !flag, "isFalse");
    }

    //-------------------------------------------------------------------------
    // Client Utilities

    /**
     * Defines a condition given a predicate, the condition's name,
     * and the argument values.
     * @param predicate The predicate
     * @param name The condition's name for display
     * @param args The condition's argument values, for display
     * @param <V> the value type
     * @return The condition
     */
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
