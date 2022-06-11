package eras.util;

public class Assert {

    /** Asserts that the condition is met.
     *
     * @param flag The condition flag
     * @param description The description of the failure
     * @throws IllegalArgumentException if the flag is false
     */
    public static void arg(boolean flag, String description)
        throws IllegalArgumentException
    {
        if (!flag) {
            throw new IllegalArgumentException(description);
        }
    }
}
