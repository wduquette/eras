package eras.orrery;

/**
 * A cycle that models integer cycles, e.g., days of the week.  The starting
 * day determines the currentCycle and currentDay as of day=0.
 *
 * @param name     The cycle's name.
 * @param length   The length of the cycle in days
 * @param startDay The starting day
 */
public record IntegerCycle(String name, int length, int startDay)
    implements Cycle<IntegerCycleValue>
{
    public IntegerCycleValue get(int day) {
        return new IntegerCycleValue(day, this);
    }
}
