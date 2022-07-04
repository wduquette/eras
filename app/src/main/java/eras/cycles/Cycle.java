package eras.cycles;

import java.util.List;

public sealed interface Cycle {
    /**
     * A cycle describing a moon's orbit around the planet.  The period is the
     * length of the orbit in decimal days. The startPhase is the position of
     * the moon in the cycle decimal days as of the epoch day.
     *
     * @param name       The cycle's name.
     * @param period     The period of the cycle in days
     * @param startPhase The cycle's value on the epoch day.
     */
    record Lunar(String name, double period, double startPhase)
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
        implements Cycle { }
}
