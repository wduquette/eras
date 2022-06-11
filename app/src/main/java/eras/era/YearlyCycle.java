package eras.era;

import eras.util.Assert;
import eras.util.Functions;

import java.util.List;
import java.util.Optional;

/**
 * A yearly cycle is the definition of the months in the year.
 */
public record YearlyCycle(List<Month> months) {
    //-------------------------------------------------------------------------
    // Accessors

    /**
     * The duration of the year in days.
     * @return The duration
     */
    public int monthsPerYear() {
        return months.size();
    }

    /**
     * The duration of the year in days.
     * @return The duration
     */
    public int daysPerYear() {
        return months.stream().mapToInt(Month::length).sum();
    }

    /**
     * Returns the month given an integer month, modulo the number of months
     * in the year.
     * @param index An index
     * @return The week day
     */
    public Month get(int index) {
        return months.get(Functions.mod(index, months.size()));
    }

    /**
     * Finds the month whose name or abbreviation matches the given name.
     * The comparison is case-insensitive.
     * @param name The given name
     * @return The Month, if found
     */
    public Optional<Month> get(String name) {
        return months.stream()
            .filter(m -> m.name().equalsIgnoreCase(name) ||
                m.abbreviation().equalsIgnoreCase(name))
            .findFirst();
    }

    /**
     * Returns the index, 0 to lengthInMonths-1, of the month within the cycle.
     * @param month The day
     * @return the index
     */
    public int indexOf(Month month) {
        var index = months.indexOf(month);
        Assert.arg(index != -1, "Unknown Month: " + month);

        return index;
    }

    /**
     * Returns the index, 0 to lengthInMonths-1, of the month within the cycle.
     * @param name The name or abbreviation of the day
     * @return the index
     */
    public Optional<Integer> indexOf(String name) {
        return get(name).map(this::indexOf);
    }
}
