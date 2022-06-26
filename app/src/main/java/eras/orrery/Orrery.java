package eras.orrery;

import java.util.*;

/**
 * An Orrery is an ordered collection of named cycles all tied to the same
 * epoch day.
 */
public class Orrery {
    //-------------------------------------------------------------------------
    // Instance Variables

    // The collection of cycles
    private final List<Cycle<? extends CycleValue>> cycles;

    //-------------------------------------------------------------------------
    // Constructor

    /**
     * Public creates a new Orrery.
     * @param builder The builder
     */
    private Orrery(Builder builder) {
        this.cycles = builder.cycles;
    }

    //-------------------------------------------------------------------------
    // Public Methods

    /**
     * Gets a read-only list of the cycles.
     * @return The cycles
     */
    public List<Cycle> getCycles() {
        return Collections.unmodifiableList(cycles);
    }

    /**
     * Gets a cycle given its name.
     * @param name The name
     * @return The cycle, or null if not found
     */
    public Optional<Cycle<?>> getCycle(String name) {
        return cycles.stream()
            .filter(c -> c.name().equals(name))
            .findFirst();
    }

    public LunarCycle asLunar(String name) {
        Cycle<?> cycle = getCycle(name)
            .orElseThrow(() -> new IllegalStateException(
                "Not found: \"" + name + "\""));

        if (cycle instanceof LunarCycle) {
            return (LunarCycle)cycle;
        } else {
            throw new IllegalStateException(
                "Not a LunarCycle: \"" + name + "\"");
        }
    }

    public WeeklyCycle asWeekly(String name) {
        Cycle<?> cycle = getCycle(name)
            .orElseThrow(() -> new IllegalStateException(
                "Not found: \"" + name + "\""));

        if (cycle instanceof WeeklyCycle) {
            return (WeeklyCycle)cycle;
        } else {
            throw new IllegalStateException(
                "Not a WeeklyCycle: \"" + name + "\"");
        }
    }


    //-------------------------------------------------------------------------
    // Builder

    public static class Builder {
        private final List<Cycle<?>> cycles = new ArrayList<>();

        public Builder add(Cycle cycle) {
            cycles.add(cycle);
            return this;
        }

        public Orrery build() {
            return new Orrery(this);
        }
    }
}
