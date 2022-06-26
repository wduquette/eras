package eras.cycles;

public sealed interface CycleValue {

    /**
     * The value of a LunarCycle.
     *
     * @param cycle The underlying cycle.
     * @param day   Days since the epoch
     */
    record LunarValue(Cycle.Lunar cycle, int day)
         implements CycleValue {}


    /**
     * The value of a weekly cycle.
     *
     * @param cycle The weekly cycle
     * @param day   Days since the epoch
     */
    record WeeklyValue(Cycle.Weekly cycle, int day)
         implements CycleValue {}
}
