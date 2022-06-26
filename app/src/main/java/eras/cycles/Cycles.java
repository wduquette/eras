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
    public static CycleValue compute(Cycle cycle, int day) {
        return switch (cycle) {
            case Lunar def -> new LunarValue(def, day);
            case Weekly def -> new WeeklyValue(def, day);
        };
    }

    /**
     * Given a cycle and day, computes the number of complete cycles since
     * the epoch day.
     * @param cycle The cycle definition
     * @param day days before/since the epoch day
     * @return The cycle count
     */
    public static int cycleCount(Cycle cycle, int day) {
        return cycleCount(compute(cycle, day));
    }

    /**
     * Given a cycle and day, computes the day-of-cycle.
     * the epoch day.
     * @param cycle The cycle definition
     * @param day days before/since the epoch day
     * @return The cycle count
     */
    public static int dayOfCycle(Cycle cycle, int day) {
        return dayOfCycle(compute(cycle, day));
    }

    /**
     * Given a cycle and day, computes the position.
     * @param cycle The cycle definition
     * @param day days before/since the epoch day
     * @return The cycle count
     */
    public static double position(Cycle cycle, int day) {
        return position(compute(cycle, day));
    }

    /**
     * Given a cycle and day, computes the fraction-of-cycle.
     * @param cycle The cycle definition
     * @param day days before/since the epoch day
     * @return The cycle count
     */
    public static double fraction(Cycle cycle, int day) {
        return fraction(compute(cycle, day));
    }

    //-------------------------------------------------------------------------
    // CycleValue functions

    /**
     * Given a cycle value, computes the number of complete cycles since
     * the epoch day.
     * @param value the value
     * @return The cycle count
     */
    public static int cycleCount(CycleValue value) {
        return switch (value) {
            case LunarValue val ->
                realCycleCount(val.day(), val.cycle().length());
            case WeeklyValue val ->
                intCycleCount(val.day(), val.cycle().dayNames().size());
        };
    }

    /**
     * Given a cycle value, computes the day within the cycle.
     * @param value The cycle value
     * @return The day-of-cycle
     */
    public static int dayOfCycle(CycleValue value) {
        return switch (value) {
            case LunarValue val ->
                (int)Math.floor(position(val));
            case WeeklyValue val ->
                dayOfCycle(val.day(), val.cycle().dayNames().size(), val.cycle().startDay());
        };
    }

    /**
     * Given a cycle value, computes the position in days, i.e., the position
     * of the moon in a lunar cycle or the sun in a yearly cycle.
     * @param value The cycle value
     * @return The position
     */
    public static double position(CycleValue value) {
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
    public static double fraction(CycleValue value) {
        return switch (value) {
            case LunarValue val  -> position(val)/val.cycle().length();
            case WeeklyValue val -> position(val)/val.cycle().dayNames().size();
        };
    }

    //-------------------------------------------------------------------------
    // LunarValue functions

    /**
     * Returns the shape of the moon.
     * @param value The cycle value
     * @return The shape
     */
    public static LunarShape lunarShape(CycleValue value) {
        if (value instanceof LunarValue v) {
            return lunarShape(fraction(value));
        } else {
            throw new IllegalArgumentException("Not a LunarValue");
        }
    }

    /**
     * Returns the shape of the moon given the fraction-of-cycle.
     * @param fraction The fraction-of-cycle.
     * @return The shape
     */
    public static LunarShape lunarShape(double fraction) {
        // 0.96 to 0.40    NEW
        //                 CRESCENT
        // 0.21 to 0.29    HALF
        //                 GIBBOUS
        // 0.46 to 0.54    FULL
        //                 GIBBOUS
        // 0.71 to 0.79    HALF
        //                 CRESCENT
        // 0.96 to 0.40    NEW

        if (fraction <= 0.04) {
            return LunarShape.NEW;
        } else if (fraction < 0.21) {
            return LunarShape.CRESCENT;
        } else if (fraction <= 0.29) {
            return LunarShape.HALF;
        } else if (fraction < 0.46) {
            return LunarShape.GIBBOUS;
        } else if (fraction <= 0.54) {
            return LunarShape.FULL;
        } else if (fraction < 0.71) {
            return LunarShape.GIBBOUS;
        } else if (fraction <= 0.79) {
            return LunarShape.HALF;
        } else if (fraction < 0.96) {
            return LunarShape.CRESCENT;
        } else {
            return LunarShape.NEW;
        }
    }

    /**
     * Returns the phase of the moon.
     * @param value The cycle value
     * @return The shape
     */
    public static LunarPhase lunarPhase(CycleValue value) {
        if (value instanceof LunarValue v) {
            return lunarPhase(fraction(value));
        } else {
            throw new IllegalArgumentException("Not a LunarValue");
        }
    }

    /**
     * Returns the phase of the moon given the fraction-of-cycle.
     * @param fraction The fraction of cycle.
     * @return The phase
     */
    public static LunarPhase lunarPhase(double fraction) {
        // 0.96 to 0.04 - NEW
        //                WAXING
        // 0.46 to 0.54 - FULL
        //                WANING

        if (fraction <= 0.04) {
            return LunarPhase.NEW;
        } else if (fraction < 0.46) {
            return LunarPhase.WAXING;
        } else if (fraction <= 0.54) {
            return LunarPhase.FULL;
        } else if (fraction < 0.96) {
            return LunarPhase.WANING;
        } else {
            return LunarPhase.NEW;
        }
    }

    //-------------------------------------------------------------------------
    // WeeklyValue functions

    public static String dayName(CycleValue value) {
        if (value instanceof WeeklyValue v) {
            return v.cycle().dayNames().get(dayOfCycle(value));
        } else {
            throw new IllegalArgumentException("value is not a WeeklyValue");
        }
    }

    public static int dayOfWeek(CycleValue value) {
        if (value instanceof WeeklyValue v) {
            return dayOfCycle(v) + 1;
        } else {
            throw new IllegalArgumentException("value is not a WeeklyValue");
        }
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

    /**
     * Dumps a table of values from day=start to day=end for a given integer
     * cycle: day, dayOfCycle, cycleCount, fraction.
     * @param cycle The cycle
     * @param start The start day
     * @param end The end day
     */
    public static void dumpIntegerCycle(Cycle cycle, int start, int end) {
        for (int i = -28; i <= 28; i++) {
            CycleValue value = compute(cycle, i);
            System.out.printf("%03d: %d %02d %4.2f\n",
                i,
                dayOfCycle(value),
                cycleCount(value),
                fraction(value));
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
            CycleValue value = compute(cycle, i);
            System.out.printf("%03d: %6.2f %4.2f %02d %02d\n",
                i,
                position(value),
                fraction(value),
                cycleCount(value),
                dayOfCycle(value));
        }
    }
}
