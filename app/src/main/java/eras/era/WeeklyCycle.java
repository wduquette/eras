package eras.era;

import eras.util.Assert;
import eras.util.Functions;

import java.util.List;
import java.util.Optional;

/**
 * A weekly cycle is the definition of the days in a week.
 */
public record WeeklyCycle(List<WeekDay> weekDays) {
    //-------------------------------------------------------------------------
    // Accessors

    /**
     * The duration of the cycle in days.
     * @return The duration
     */
    public int daysPerWeek() {
        return weekDays.size();
    }

    /**
     * Returns the week day given an integer index, modulo the number of days
     * of the week.
     * @param index An index
     * @return The week day
     */
    public WeekDay get(int index) {
        return weekDays.get(Functions.mod(index, weekDays.size()));
    }

    /**
     * Finds the day whose name or abbreviation matches the given name.
     * The comparison is case-insensitive.
     * @param name The given name
     * @return The WeekDay, if found
     */
    public Optional<WeekDay> get(String name) {
        return weekDays.stream()
            .filter(d -> d.name().equalsIgnoreCase(name) ||
                d.abbreviation().equalsIgnoreCase(name))
            .findFirst();
    }

    /**
     * Returns the index, 0 to size-1, of the weekday within the cycle.
     * @param day The day
     * @return the index
     */
    public int indexOf(WeekDay day) {
        var index = weekDays.indexOf(day);
        Assert.arg(index != -1, "Unknown WeekDay: " + day);

        return index;
    }

    /**
     * Returns the index, 0 to size-1, of the weekday within the cycle.
     * @param name The name or abbreviation of the day
     * @return the index
     */
    public Optional<Integer> indexOf(String name) {
        return get(name).map(day -> indexOf(day));
    }
}
