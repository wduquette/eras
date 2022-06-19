package eras.orrery;

import eras.util.Functions;

/**
 * A collection of functions for use by different kinds of cycles.
 */
class CycleFunctions {
    private CycleFunctions() {} // Not instantiable

    /**
     * Performs the currentCycle calculation for an integer cycle.
     * @param day The day in days since the epoch day
     * @param length The cycle length in integer days
     */
    public static int currentCycle(int day, int length) {
        if (day >= 0) {
            return day / length;
        } else {
            return -1 + ((day + 1) / length);
        }
    }

    /**
     * Performs the currentDay calculation for an integer cycle.
     * @param day The day in days since the epoch day
     * @param length The cycle length in integer days
     * @param startDay The startDay relative to the epoch day
     */
    public static int currentDay(int day, int length, int startDay) {
        return Functions.mod((day + startDay), length);
    }

    /**
     * Performs the currentCycle calculation for a real cycle.
     * @param day The day in days since the epoch day
     * @param length The cycle length in real days
     */
    public static int currentCycle(int day, double length) {
        return (int)Math.floor(day / length);
    }

    /**
     * Performs the realDay calculation for a real cycle.
     * @param day The day in days since the epoch day
     * @param length The cycle length in real days
     * @param startDay The startDay relative to the epoch day
     */
    public static double realDay(int day, double length, double startDay) {
        return Functions.fmod((day + startDay), length);
    }
}
