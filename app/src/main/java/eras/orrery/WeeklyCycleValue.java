package eras.orrery;

/**
 * The value of a weekly cycle.
 *
 * @param day   Days since the epoch
 * @param cycle The weekly cycle
 */
public record WeeklyCycleValue(int day, WeeklyCycle cycle)
    implements CycleValue
{
    @Override
    public int cycleCount() {
        return CycleFunctions.cycleCount(day, cycle.length());
    }

    @Override
    public int dayOfCycle() {
        return CycleFunctions.dayOfCycle(day, cycle.length(), cycle.startDay());
    }

    @Override
    public double realValue() {
        return dayOfCycle();
    }

    @Override
    public double fraction() {
        return realValue()/cycle.length();
    }

    /**
     * Returns the length of the cycle in days.
     * @return The length
     */
    public int length() {
        return cycle.dayNames().size();
    }

    /**
     * Gets the weekday name for the dayOfCycle().
     * @return The name
     */
    public String dayName() {
        return cycle.dayNames().get(dayOfCycle());
    }

    /**
     * Gets the day-of-week as an integer 1 to the number of days in the week.
     * @return The number
     */
    public int dayOfWeek() {
        return dayOfCycle() + 1;
    }

    /**
     * Returns the dayName() string.
     * @return The day name string.
     */
    @Override
    public String toString() {
        return dayName();
    }
}
