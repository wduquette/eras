# Orrery
#term 

In this system, an `Orrery` is a model of one or related [[Cycle|cycles]], e.g.,

- The daily cycle
    - This is the basic unit; one cycle has the value 1.0
- The week
    - Some number of days, 1 to N.  This is a simple integer cycle.
- The lunar cycle
    - Some number of days, e.g., 27.322.   This a simple floating point cycle
- The solar year
    - Some number of days, e.g., 365.2422.  This is a simple floating point cycle.
- The calendar year
    - A complex integer cycle, whose length does not precisely match the solar year.  
    - The calendar year might vary in length year by year so is to try not to be too far off from the solar year

Given a starting point for each cycle, one can roll them all forward or backward to any given day related to some epoch day.  One can then read off:

- Days since the epoch
- The day of the week
- Weeks since the epoch
- The phase of the moon
- Lunar months since the epoch
- Solar years since the epoch
- Calendar years since the epoch
- The date

Further, this gives a basis for complex rules like the calculating of the date of Easter or of the first Sunday of Advent.

## Background

Any human calendar derives from the earth's rotation, the solar year, the lunar month, or some imperfect reconciliation of the these.  It is reasonable to think that most fictional calendars would similarly be based on natural cycles of some kind.

An orrery, properly speaking, is a mechanical model of the Solar System, and shows the revolution of the planets around the sun, of the moon around the earth, and possibly the rotation of the earth as well.  My `Orrery` is somewhat analogous.

## Acknowledgments

This notion was suggested to me by Jonathan Castello.

*Created on 2022-06-13 at 13:45*