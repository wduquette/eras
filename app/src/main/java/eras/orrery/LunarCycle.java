package eras.orrery;

/**
 * A cycle that models a lunar cycle.  The length will seldom be an integer
 * number of days, and the startDay will often not be zero.
 *
 * @param name       The cycle's name.
 * @param length     The length of the cycle in days
 * @param startValue The cycle's value on day()=0
 */
public record LunarCycle(String name, double length, double startValue)
    implements Cycle<LunarCycleValue>
{
    public LunarCycleValue get(int day) {
        return new LunarCycleValue(day, this);
    }

    /**
     * The appearance of the moon.
     */
    public enum Shape {
        NEW,
        CRESCENT,
        HALF,
        GIBBOUS,
        FULL
    }

    /**
     * The phase of the moon.
     */
    public enum Phase {
        NEW,
        WAXING,
        FULL,
        WANING
    }
}
