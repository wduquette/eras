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
     * The number of days since the epoch.  May be positive or negative.
     * @return The number of days
     */
    int day();

    /**
     * Returns the cycle updated to the new day.
     * @param newDay The new day
     * @return The updated cycle.
     */
    Cycle setDay(int newDay);

    /**
     * For the current day(), the number of cycles completed since the epoch.
     * @return The number
     */
    int cycleCount();

    /**
     * The current day in the cycle.  For integer cycles, this will be in
     * the range [0,length-1]; for real cycles with non-integer length it
     * will be less meaningful.
     * @return the day.
     */
    int dayOfCycle();

    /**
     * The real value of the cycle given the day().  For integer cycles
     * this will be the same as the dayInCycle(); for natural cycles of
     * non-integer length it will be something else.
      * @return the real value.
     */
    double realValue();

    /**
     * The real value of the cycle expressed as a fraction [0.0,1.0)
     * @return the fraction
     */
    double fraction();
}
