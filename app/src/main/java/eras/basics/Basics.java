package eras.basics;

public class Basics {
    private Basics() {} // Not instantiable

    public static int lengthOfYearElementInDays(YearElement element, int year) {
        return switch (element) {
            case YearElement.Month m ->
                m.days() + m.leapDays().forYear(year);
            case YearElement.Epagomenal m ->
                m.days() + m.leapDays().forYear(year);
        };
    }

    public static int lengthOfYearInDays(YearlyCycle cycle, int year) {
        return cycle.elements().stream()
            .mapToInt(e -> lengthOfYearElementInDays(e, year))
            .sum();
    }
}
