package eras.cycles;

import eras.cycles.CycleValue.*;
import eras.cycles.Cycle.*;
import eras.orrery.LunarCycle;
import eras.util.Functions;

/**
 * Functions that operate on cycles.
 */
class Cycles {
    private Cycles() {} // Not instantiable

    //-------------------------------------------------------------------------
    // Cycle Functions

    /**
     * Given a cycle definition and a number of days since the epoch,
     * computes the cycle value.
     * @param cycle The cycle definition
     * @param day days before/since the epoch day
     * @return The value
     */
    public CycleValue compute(Cycle cycle, int day) {
        return switch (cycle) {
            case Lunar def -> new LunarValue(def, day);
            case Weekly def -> new WeeklyValue(def, day);
        };
    }

    //-------------------------------------------------------------------------
    // CycleValue functions

    /**
     * Given a cycle value, computes the number of complete cycles since
     * the epoch day.
     * @param value the value
     * @return The cycle count
     */
    public int cycleCount(CycleValue value) {
        return switch (value) {
            case LunarValue val ->
                realCycleCount(val.day(), val.cycle().length());
            case WeeklyValue val ->
                intCycleCount(val.day(), val.cycle().length());
        };
    }

    /**
     * Given a cycle value, computes the day within the cycle.
     * @param value The cycle value
     * @return The day-of-cycle
     */
    public int dayOfCycle(CycleValue value) {
        return switch (value) {
            case LunarValue val ->
                (int)Math.floor(position(val));
            case WeeklyValue val ->
                dayOfCycle(val.day(), val.cycle().length(), val.cycle().startDay());
        };
    }

    /**
     * Given a cycle value, computes the position in days, i.e., the position
     * of the moon in a lunar cycle or the sun in a yearly cycle.
     * @param value The cycle value
     * @return The position
     */
    public double position(CycleValue value) {
        return switch (value) {
            case LunarValue val ->
                position(val.day(), val.cycle().length(), val.cycle().startValue());
            case WeeklyValue val ->
                dayOfCycle(val);
        };
    }

    /**
     * Given a cycle value, computes the position as a fraction of the cycle
     * length, e.g, a value in the range [0.0,1.0).
     * @param value The cycle value
     * @return The position as a fraction.
     */
    public double fraction(CycleValue value) {
        return switch (value) {
            case LunarValue val  -> position(val)/val.cycle().length();
            case WeeklyValue val -> position(val)/val.cycle().length();
        };
    }

    //-------------------------------------------------------------------------
    // LunarValue functions

    /**
     * Returns the shape of the moon.
     * @return The shape
     */
    public LunarCycle.Shape shape(LunarValue value) {
        double frac = fraction(value);

        // 0.96 to 0.40    NEW
        //                 CRESCENT
        // 0.21 to 0.29    HALF
        //                 GIBBOUS
        // 0.46 to 0.54    FULL
        //                 GIBBOUS
        // 0.71 to 0.79    HALF
        //                 CRESCENT
        // 0.96 to 0.40    NEW

        if (frac <= 0.04) {
            return LunarCycle.Shape.NEW;
        } else if (frac < 0.21) {
            return LunarCycle.Shape.CRESCENT;
        } else if (frac <= 0.29) {
            return LunarCycle.Shape.HALF;
        } else if (frac < 0.46) {
            return LunarCycle.Shape.GIBBOUS;
        } else if (frac <= 0.54) {
            return LunarCycle.Shape.FULL;
        } else if (frac < 0.71) {
            return LunarCycle.Shape.GIBBOUS;
        } else if (frac <= 0.79) {
            return LunarCycle.Shape.HALF;
        } else if (frac < 0.96) {
            return LunarCycle.Shape.CRESCENT;
        } else {
            return LunarCycle.Shape.NEW;
        }
    }

    /**
     * Returns the phase of the moon.
     * @return The phase
     */
    public LunarCycle.Phase phase(LunarValue value) {
        double frac = fraction(value);

        // 0.96 to 0.04 - NEW
        //                WAXING
        // 0.46 to 0.54 - FULL
        //                WANING

        if (frac <= 0.04) {
            return LunarCycle.Phase.NEW;
        } else if (frac < 0.46) {
            return LunarCycle.Phase.WAXING;
        } else if (frac <= 0.54) {
            return LunarCycle.Phase.FULL;
        } else if (frac < 0.96) {
            return LunarCycle.Phase.WANING;
        } else {
            return LunarCycle.Phase.NEW;
        }
    }

    //-------------------------------------------------------------------------
    // WeeklyValue functions

    public String dayName(WeeklyValue value) {
        return value.cycle().dayNames().get(dayOfCycle(value));
    }

    public int dayOfWeek(WeeklyValue value) {
        return dayOfCycle(value) + 1;
    }

    //-------------------------------------------------------------------------
    // Basic computations

    /**
     * Computes the number of cycles since the epoch day for an integer cycle.
     * The input day may be positive or negative.
     * @param day The day in days since the epoch day
     * @param length The cycle length in integer days
     */
    public static int intCycleCount(int day, int length) {
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
    public static int realCycleCount(int day, double length) {
        return (int)Math.floor(day / length);
    }

    /**
     * Performs the realDay calculation for a real cycle.
     * @param day The day in days since the epoch day
     * @param length The cycle length in real days
     * @param startValue The real value on the epoch day
     */
    public static double position(int day, double length, double startValue) {
        return Functions.fmod((day + startValue), length);
    }

//    /**
//     * Dumps a table of values from day=start to day=end for a given integer
//     * cycle: day, dayOfCycle, cycleCount, fraction.
//     * @param cycle The cycle
//     * @param start The start day
//     * @param end The end day
//     */
//    public static void dumpIntegerCycle(Cycle cycle, int start, int end) {
//        for (int i = -28; i <= 28; i++) {
//            CycleValue value = cycle.get(i);
//            System.out.printf("%03d: %d %02d %4.2f\n",
//                value.day(),
//                value.dayOfCycle(),
//                value.cycleCount(),
//                value.fraction());
//        }
//    }
//
//    /**
//     * Dumps a table of values from day=start to day=end for a given real
//     * cycle: day, realValue, fraction, cycleCount, dayOfCycle.
//     * @param cycle The cycle
//     * @param start The start day
//     * @param end The end day
//     */
//    public static void dumpRealCycle(Cycle cycle, int start, int end) {
//        for (int i = start; i <= end; i++) {
//            CycleValue value = cycle.get(i);
//            System.out.printf("%03d: %6.2f %4.2f %02d %02d\n",
//                value.day(),
//                value.realValue(),
//                value.fraction(),
//                value.cycleCount(),
//                value.dayOfCycle());
//        }
//    }
}
