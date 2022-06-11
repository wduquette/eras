package eras.era;

import eras.util.Assert;
import eras.util.Functions;

import java.util.List;
import java.util.Optional;

/**
 * A yearly cycle is the definition of the months in the year.
 */
public record Months(List<Month> months) {
    public static final Months ENGLISH = new Months(List.of(
        new Month("January", "Jan", 31),
        new Month("February", "Feb", 28),
        new Month("March", "Mar", 31),
        new Month("April", "Apr", 30),
        new Month("May", "May", 31),
        new Month("June", "Jun", 30),
        new Month("July", "Jul", 31),
        new Month("August", "Aug", 31),
        new Month("September", "Sep", 30),
        new Month("October", "Oct", 31),
        new Month("November", "Nov", 30),
        new Month("December", "Dec", 31)
    ));

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
     * Returns the month given an integer month 1 to monthsPerYear, modulo the
     * number of months in the year.
     * @param index An index
     * @return The month
     */
    public Month get(int index) {
        return months.get(Functions.mod(index - 1, months.size()));
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
     * Returns the index, 1 to monthsPerYear, of the month within the cycle.
     * @param month The day
     * @return the index
     */
    public int indexOf(Month month) {
        var index = months.indexOf(month);
        Assert.arg(index != -1, "Unknown Month: " + month);

        return index + 1;
    }

    /**
     * Returns the index, 1 to monthsPerYear, of the month within the cycle.
     * @param name The name or abbreviation of the month
     * @return the index
     */
    public Optional<Integer> indexOf(String name) {
        return get(name).map(this::indexOf);
    }
}
