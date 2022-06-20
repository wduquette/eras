package eras.orrery;

/**
 * A cycle that models integer cycles, e.g., days of the week.  The starting
 * day determines the currentCycle and currentDay as of day=0.
 *
 * @param day   Days since the epoch
 * @param cycle The integer cycle
 */
public record IntegerCycleValue(int day, IntegerCycle cycle)
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

    @Override
    public String toString() {
        return Integer.toString(dayOfCycle());
    }
}
