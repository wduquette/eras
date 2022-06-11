# Eras Vision
#top

## Vision

Eras is a calendar processor for authors others.  The user can define a standard era for their world plus any number of subordinate eras of varying complexity.

The intent is to support:

- A standard era, which is the "official" reckoning of years and dates.
- Eras of various kinds that are tied to the base era
    - Julian vs. Gregorian
    - Regnal eras
    - Differing month names (e.g., French vs. English)
    - Differing weeks/week names
    - Differing new year's days
- Timelines with point events, dependent events, spans, etc., as in Aeon, which can be displayed using any desired era.


## The Standard Era

The standard era includes:

- A name, e.g., AD
- A division of the year into months with numbered days.
- A week
- A reference date
    - year, month, day of month, day of week:
    - This month/day of month will be the new year's day
- The name of its predecessor era, e.g., BC

Given this, Eras can produce calendars for any year.

## Subordinate Eras

A subordinate era has:

- A name
- A division of the year into months with numbered days.
- A week
- A reference date
- Optionally, the name of its predecessor era

The subordinate era  may:

- Use the same month definitions and days of the week as the base era, but need not. 
- Have a predecessor era (e.g., AD/BC) or it may not (e.g, King George's year 1, 2, 3...)
- Have begin and/or end dates
    - King George's era only makes sense during his reign

Given this, Eras can produce a calendar showing multiple eras in one layout.

## Bells and Whistles

In some calendars, years are combined into Great Years, Cycles, etc., which are tracked.  Could support that.
 

*Created on 2022-06-10 at 11:12*