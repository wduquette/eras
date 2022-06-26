/**
 * This package is yet another attempt at the cycles code; this time in a
 * purely functional style.  There will be data records and functions acting on
 * them.  The functions will be defined as static members of the Cycles class.
 *
 * <h1>Concepts</h1>
 *
 * <ul>
 * <li>A "Cycle" is a record value containing the parameters that define a
 * specific cycle with respect to an epoch day: a weekly cycle, a lunar cycle,
 * etc.</li>
 * <li>A "Cycle Value" represents the value of the cycle on a specific day
 * before or since the epoch day.
 * It will reference the Cycle definition, the
 * day (as a number of days since the epoch) and anything other information
 * required to work with the value efficiently.</li>
 * </ul>
 *
 * For simple natural cycles, the cycle value will usually contain the cycle
 * reference and the day.
 * For calendars, where the length may vary according to a function, the
 * cycle value may be more complicated.
 */
package eras.cycles;
