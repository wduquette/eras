package eras.calendar;

public class CalendarFunctions {
    private CalendarFunctions() {} // Not instantiable

    public static int lengthOfYearElementInDays(YearElement element, int year) {
        return switch (element) {
            case YearElement.Month m ->
                m.days() + m.leapDays().forYear(year);
            case YearElement.Epagomenal m ->
                m.days() + m.leapDays().forYear(year);
        };
    }

    public static int lengthOfYearInDays(Calendar calendar, int year) {
        return calendar.elements().stream()
            .mapToInt(e -> lengthOfYearElementInDays(e, year))
            .sum();
    }
}
