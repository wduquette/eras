package eras.cycles;

import java.util.List;

public sealed interface Cycle {
    /**
     * A cycle describing a moon's orbit around the planet.  The length is the
     * length of the orbit in days, and will usually not be an integer number
     * of days.  The startValue is the position of the moon in the cycle (in days)
     * as of the epoch day.
     *
     * @param name       The cycle's name.
     * @param length     The length of the cycle in days
     * @param startValue The cycle's value on the epoch day.
     */
    record Lunar(String name, double length, double startValue)
        implements Cycle { }

    /**
     * A cycle that models the days of the week.  The starting
     * day determines the cycleCount and dayOfCycle as of the epoch day.
     *
     * @param name     The cycle's name.
     * @param dayNames The names of the days in the week
     * @param startDay The starting day
     */
    record Weekly(String name, List<String> dayNames, int startDay)
        implements Cycle
    {
        /**
         * Gets the length of the cycle in days.
         * @return The length.
         */
        public int length() { return dayNames.size(); }
    }
}
