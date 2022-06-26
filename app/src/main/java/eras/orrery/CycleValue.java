package eras.orrery;

/**
 * A CycleValue captures the state of a cycle on a specific day since the
 * epoch.
 */
public interface CycleValue {
    /**
     * The number of days since the epoch.
     * @return The number
     */
    int day();

    /**
     * Gets the underlying cycle
     * @returns The cycle
     */
    <C extends Cycle> C cycle();

    /**
     * For the current day the number of cycles completed since the epoch.
     * @return The number
     */
    int cycleCount();

    /**
     * The day of the cycle.  For integer cycles, this will be in
     * the range [0,length-1]; for real cycles with non-integer length it
     * will be less meaningful.
     * @return the day-of-cycle.
     */
    int dayOfCycle();

    /**
     * The real value of the cycle for the given day. For integer cycles
     * this will be the same as the dayOfCycle(); for natural cycles of
     * non-integer length it will be something else.
     * @return the real value.
     */
    double realValue();

    /**
     * The real value of the cycle for the given day, expressed as a fraction
     * in the range [0.0,1.0).
     * @return the real value.
     */
    double fraction();

    /**
     * The cycle's value as a string.  The appropriate string
     * representation will vary depending on the kind of cycle.
     * @return The string representation
     */
    String toString();
}
