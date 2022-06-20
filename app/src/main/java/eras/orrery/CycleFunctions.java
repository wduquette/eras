package eras.orrery;

import eras.util.Functions;

/**
 * A collection of functions for use by different kinds of cycles.
 */
class CycleFunctions {
    private CycleFunctions() {} // Not instantiable

    /**
     * Computes the number of cycles since the epoch day for an integer cycle.
     * The input day may be positive or negative.  The cycle's real value as
     * of the epoch day is irrelevant.
     * @param day The day in days since the epoch day
     * @param length The cycle length in integer days
     */
    public static int cycleCount(int day, int length) {
        if (day >= 0) {
            return day / length;
        } else {
            return -1 + ((day + 1) / length);
        }
    }

    /**
     * Computes the day-of-cycle for an integer cycle, taking its value on the
     * epoch day into account.
     * @param day The day in days since the epoch day
     * @param length The cycle length in integer days
     * @param startDay The day-of-cycle on the epoch day.
     */
    public static int dayOfCycle(int day, int length, int startDay) {
        return Functions.mod((day + startDay), length);
    }

    /**
     * Performs the currentCycle calculation for a real cycle.
     * @param day The day in days since the epoch day
     * @param length The cycle length in real days
     */
    public static int cycleCount(int day, double length) {
        return (int)Math.floor(day / length);
    }

    /**
     * Performs the realDay calculation for a real cycle.
     * @param day The day in days since the epoch day
     * @param length The cycle length in real days
     * @param startValue The real value on the epoch day
     */
    public static double realValue(int day, double length, double startValue) {
        return Functions.fmod((day + startValue), length);
    }

    /**
     * Dumps a table of values from day=start to day=end for a given integer
     * cycle: day, dayOfCycle, cycleCount, fraction.
     * @param cycle The cycle
     * @param start The start day
     * @param end The end day
     */
    public static void dumpIntegerCycle(Cycle cycle, int start, int end) {
        for (int i = -28; i <= 28; i++) {
            CycleValue value = cycle.get(i);
            System.out.printf("%03d: %d %02d %4.2f\n",
                value.day(),
                value.dayOfCycle(),
                value.cycleCount(),
                value.fraction());
        }
    }

    /**
     * Dumps a table of values from day=start to day=end for a given real
     * cycle: day, realValue, fraction, cycleCount, dayOfCycle.
     * @param cycle The cycle
     * @param start The start day
     * @param end The end day
     */
    public static void dumpRealCycle(Cycle cycle, int start, int end) {
        for (int i = start; i <= end; i++) {
            CycleValue value = cycle.get(i);
            System.out.printf("%03d: %6.2f %4.2f %02d %02d\n",
                value.day(),
                value.realValue(),
                value.fraction(),
                value.cycleCount(),
                value.dayOfCycle());
        }
    }
}
