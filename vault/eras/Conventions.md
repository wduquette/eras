# Conventions

This page describes the design conventions I'm currently using on the project (as of time of writing).

- I'm trying to take a functional approach, by which I mean: 
    - Work bottom up
    - Define the needed data
    - Then, define functions that do the computations I need, possibly in terms of lower-level functions.
- Code Layout
    - Define a package for related data types, e.g., `eras.calendar`.
    - Define the data types as pure data types
        - No instance methods, beyond the automatic record getters.
        - Static factory methods are OK.
    - Define a single class to contain the package's functions
        - E.g., `eras.calendar.Calendars`.
        - Plural, because that won't conflict with a data type.
    
*Created on 2022-07-04 at 09:37*