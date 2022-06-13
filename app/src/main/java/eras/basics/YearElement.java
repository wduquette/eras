package eras.basics;

/**
 * A year element is a portion of a year, usually a normal month.  In real
 * calendars, one finds these kinds of elements:
 *
 * <ul>
 * <li>Normal months of fixed length.</li>
 * <li>Months to which an intercalary day (a leap day) is sometimes added.</li>
 * <li>Leap months. In lunar calendars, most years are 12 months but sometimes a
 * 13th month is added.</li>
 * <li>Epagomenal days (days belonging to no specific month) as in the
 * Egyptian calendar, which has 12 30-day months and 5 or 6 epagomenal days
 * at the end of the year.</li>
 * </ul>
 *
 * <p>In real calendars, so far as I can tell, the 7-day week cycles
 * continuously, independently of these elements: every day has a day of the
 * week.  In fictional calendars, epagomenal days could easily be distinct from
 * the days of the week.  The normal cycle would continue after the epagomenal
 * day or days.</p>
 */
public sealed interface YearElement {
    /**
     * A standard month of a given length.
     *
     * @param name The name of the month
     * @param days The length of the month in days.
     */
    record Month(
        String name,
        int days,
        LeapDays leapDays
    ) implements YearElement {
        public static Month of(String name, int days) {
            return new Month(name, days, LeapDays.NONE);
        }

        public static Month of(String name, int days, LeapDays leapDays) {
            return new Month(name, days, leapDays);
        }

        public static Month of(String name, LeapDays leapDays) {
            return new Month(name, 0, leapDays);
        }
    }

    /**
     * A period of zero or more epagomenal days
     *
     * @param name     The name
     * @param days     The number of normal days
     * @param leapDays The rule for the number of leap days
     * @param nonWeek  Whether the epagomenal days are days of the week
     */
    record Epagomenal(
        String name,
        int days,
        LeapDays leapDays,
        boolean nonWeek
    ) implements YearElement {
        public static Epagomenal of(String name, int days) {
            return new Epagomenal(name, days, LeapDays.NONE, false);
        }

        public static Epagomenal of(String name, int days, LeapDays leapDays) {
            return new Epagomenal(name, days, leapDays, false);
        }

        public static Epagomenal of(String name, int days, LeapDays leapDays, boolean nonWeek) {
            return new Epagomenal(name, days, leapDays, nonWeek);
        }
    }
}
