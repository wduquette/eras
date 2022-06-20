package eras.orrery;

/**
 * A cycle that models natural cycles: the solar year, the lunar month.  The
 * length will seldom be an integer number of days, and the startDay will
 * often not be zero.
 *
 * @param length   The length of the cycle in days
 * @param startValue The cycle's value on day()=0
 */
public record RealCycle(double length, double startValue)
    implements Cycle<RealCycleValue>
{
    public RealCycleValue get(int day) {
        return new RealCycleValue(day, this);
    }
}
