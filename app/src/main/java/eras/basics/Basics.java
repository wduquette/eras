package eras.basics;

public class Basics {
    private Basics() {} // Not instantiable

    public static int lengthOfYearElementInDays(YearElement element, int year) {
        return switch (element) {
            case YearElement.Month m ->
                m.daysInMonth();
            case YearElement.MonthWithLeapDay m ->
                m.daysInMonth() + m.rule().leapDays(year);
        };
    }

    public static int lengthOfYearInDays(YearlyCycle cycle, int year) {
        return cycle.elements().stream()
            .mapToInt(e -> lengthOfYearElementInDays(e, year))
            .sum();
    }
}
