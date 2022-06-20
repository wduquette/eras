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
public interface Cycle<V extends CycleValue> {
    //-------------------------------------------------------------------------
    // Cycle Queries and Operations

    /**
     * Gets the value of the cycle for the given day.
     * @param day
     * @return The cycle value
     */
    V get(int day);
}
