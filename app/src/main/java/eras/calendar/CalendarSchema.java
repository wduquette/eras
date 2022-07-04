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

    /**
     * Gets the number of elements in the year.
     * @return The size
     */
    public int size() {
        return elements().size();
    }

    /**
     * Gets the year element at the index, 1 to N
     * @param index The index
     * @return The element
     */
    public YearElement element(int index) {
        return elements.get(index - 1);
    }
}
