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

    /** The number of days since the epoch. */
    int day();

    /**
     * Returns the cycle updated to the new day.
     * @param newDay The new day
     * @return The updated cycle.
     */
    Cycle setDay(int newDay);

    /** The current cycle number */
    int currentCycle();

    /** The current day within the cycle. */
    int currentDay();

    /** The current day including any fractional part. */
    double realDay();

    /** The current day as a fraction of the cycle. */
    double currentFraction();
}
