package eras.orrery;

import java.util.List;

/**
 * A cycle that models days of the week.  The starting
 * day determines the cycleCount and dayOfCycle as of day=0.
 *
 * @param dayNames The names of the days in the week
 * @param startDay The starting day
 */
public record WeeklyCycle(List<String> dayNames, int startDay)
    implements Cycle
{
    //-------------------------------------------------------------------------
    // Cycle API

    @Override
    public int cycleCount(int day) {
        return CycleFunctions.cycleCount(day, length());
    }

    @Override
    public int dayOfCycle(int day) {
        return CycleFunctions.dayOfCycle(day, length(), startDay);
    }

    @Override
    public double realValue(int day) {
        return dayOfCycle(day);
    }

    @Override
    public double fraction(int day) {
        return realValue(day)/length();
    }

    //-------------------------------------------------------------------------
    // WeeklyCycle API

    /**
     * Returns the length of the cycle in days.
     * @return The length
     */
    public int length() {
        return dayNames.size();
    }

    /**
     * Gets the weekday name for the dayOfCycle().
     * @param day Days since the epoch
     * @return The name
     */
    public String dayName(int day) {
        return dayNames.get(dayOfCycle(day));
    }

    /**
     * Gets the day-of-week as an integer 1 to the number of days in the week.
     * @param day Days since the epoch
     * @return The number
     */
    public int dayOfWeek(int day) {
        return dayOfCycle(day) + 1;
    }

    /**
     * Returns the dayName() string.
     * @param day Days since the epoch
     * @return The day name string.
     */
    @Override
    public String toString(int day) {
        return dayName(day);
    }
}
