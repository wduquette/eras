package eras.orrery;

/**
 * A cycle is a rotating cycle of days: a solar year, a lunar month, a yearly
 * calendar, the days of the week.  On any day before or after some epoch day,
 * the cycle can tell you:
 *
 * <ul>
 * <li>The <i>day</i>: the number of days since the epoch.</li>
 * <li>The cycle count: how many complete cycles have elapsed since the epoch.</li>
 * <li>The <i>day within the current cycle</i></li>
 * <li>The length of the cycle in days.</li>
 * </ul>
 */
public interface Cycle {
    //-------------------------------------------------------------------------
    // Cycle Queries and Operations

    /**
     * For the current day the number of cycles completed since the epoch.
     * @day Days since the epoch
     * @return The number
     */
    int cycleCount(int day);

    /**
     * The current day in the cycle.  For integer cycles, this will be in
     * the range [0,length-1]; for real cycles with non-integer length it
     * will be less meaningful.
     * @param day Days since the epoch
     * @return the day-of-cycle.
     */
    int dayOfCycle(int day);

    /**
     * The real value of the cycle given the day.  For integer cycles
     * this will be the same as the dayOfCycle(); for natural cycles of
     * non-integer length it will be something else.
     * @return the real value.
     */
    double realValue(int day);

    /**
     * The real value of the cycle expressed as a fraction [0.0,1.0)
     * @param day Days since the epoch
     * @return the fraction
     */
    double fraction(int day);

    /**
     * The value of the cycle as a string.  The appropriate string
     * representation will vary depending on the kind of cycle.
     * @param day Days since the epoch
     * @return The string representation
     */
    String toString(int day);
}
