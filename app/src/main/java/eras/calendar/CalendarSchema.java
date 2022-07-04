package eras.calendar;

import java.util.List;

/**
 * This class defines a yearly calendar in terms of its elements: months and
 * epagomenal days. Both months and epagomena can include leap days according
 * to some {@link LeapRule}, so that length of the year in days can depend
 * on the numerical year.
 *
 * @param elements The elements.
 */
public record CalendarSchema(List<YearElement> elements) {
    /**
     * Convenience method for creating schemas.
     * @param args The elements
     * @return The schema.
     */
    public static CalendarSchema of(YearElement... args) {
        return new CalendarSchema(List.of(args));
    }
}
