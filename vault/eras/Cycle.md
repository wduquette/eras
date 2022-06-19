# Cycle
#term 

A *cycle* is a modular count of days that measures some important cycle that drives or is part of a calendar, e.g.,

- The solar year
- The yearly calendar
- The days of the week

Each cycle has:

- A length *L* in days, possibly with a fractional part.
    - If *L* is integer we have an integer cycle; if real, a real cycle.
- An integer count *t* in days, starting at 0, from some epoch day.
- A value *x* within the current cycle, e.g,
    - The day of the year
    - The day of the week
- A starting value *x0* at *t=0*.

  
A cycle is defined by its *cycle type*, its length *L*, and starting value *x0*.

Given a time *t*, the cycle can compute *x*, the current value, and *p = x/L*.

For most cycle types, *x = (t + x0) mod L* where *mod* is the floating point or integer modulus operator, so that *x* runs linearly in the range from 0 to *L* as *t* counts up or down. For others (such as yearly calendars) the value of *L* might vary cycle by cycle, in which case the computation of *x* might need to be simulated.


*Created on 2022-06-18 at 07:21*