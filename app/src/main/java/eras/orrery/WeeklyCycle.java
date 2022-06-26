package eras.orrery;

import java.util.List;

/**
 * A cycle that models days of the week.  The starting
 * day determines the cycleCount and dayOfCycle as of day=0.
 *
 * @param name     The cycle's name.
 * @param dayNames The names of the days in the week
 * @param startDay The starting day
 */
public record WeeklyCycle(String name, List<String> dayNames, int startDay)
    implements Cycle<WeeklyCycleValue>
{
    public WeeklyCycleValue get(int day) {
        return new WeeklyCycleValue(day, this);
    }

    /**
     * Gets the length of the cycle in days.
     * @return The length.
     */
    public int length() {
        return dayNames.size();
    }
}
