package eras.basics;

public sealed interface YearElement {
    /**
     * A standard month of a given length.
     * @param name The name of the month
     * @param daysInMonth The length of the month in days.
     */
    record Month(
        String name,
        int daysInMonth
    ) implements YearElement {}

    /**
     * A month which sometimes has an added leap day
     * @param name The name of the month
     * @param daysInMonth The number of days without the leap day
     * @param rule The rule for adding leap days, given the year
     */
    record MonthWithLeapDay(
        String name,
        int daysInMonth,
        LeapRule rule
    ) implements YearElement {}
}
