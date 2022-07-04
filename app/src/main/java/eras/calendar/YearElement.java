package eras.calendar;

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
     * @param leapRule The rule for adding leap days to this month.
     */
    record Month(
        String name,
        int days,
        LeapRule leapRule
    ) implements YearElement {
        /**
         * Creates a month that's a constant number of days.
         * @param name The month's name
         * @param days The month's length in days.
         * @return The month
         */
        public static Month of(String name, int days) {
            return new Month(name, days, LeapRule.NONE);
        }

        /**
         * Creates a month that includes an explicit leap rule.
         * @param name The month's name
         * @param days The month's normal length in days.
         * @param leapRule The rule for adding leap days to this month.
         * @return The month
         */
        public static Month of(String name, int days, LeapRule leapRule) {
            return new Month(name, days, leapRule);
        }
    }

    /**
     * A period of zero or more epagomenal days (days outside of any month).
     *
     * @param name     The name of the epagomenal
     * @param days     The number of normal days
     * @param leapRule The rule for the number of leap days
     * @param nonWeek  Whether the epagomenal days are days of the week or not
     */
    record Epagomenal(
        String name,
        int days,
        LeapRule leapRule,
        boolean nonWeek
    ) implements YearElement {
        /**
         * Returns an epagomenal period of the given length.
         * @param name The name of the period
         * @param days The length in days
         * @return The epagomenal
         */
        public static Epagomenal of(String name, int days) {
            return new Epagomenal(name, days, LeapRule.NONE, false);
        }

        /**
         * Returns an epagomenal period of the given length, plus an explicit
         * leap rule
         * @param name The name of the period
         * @param days The normal length in days
         * @param leapRule The rule for adding leap days
         * @return The epagomenal
         */
        public static Epagomenal of(String name, int days, LeapRule leapRule) {
            return new Epagomenal(name, days, leapRule, false);
        }

        /**
         * Returns a non-week-day epagomenal period of the given length, plus
         * an explicit leap rule
         * @param name The name of the period
         * @param days The normal length in days
         * @param leapRule The rule for adding leap days
         * @return The epagomenal
         */
        public static Epagomenal nonWeekOf(
            String name,
            int days,
            LeapRule leapRule)
        {
            return new Epagomenal(name, days, leapRule, false);
        }
    }
}
