package eras.orrery;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * An Orrery is an ordered collection of named cycles all tied to the same
 * epoch day.
 */
public class Orrery {
    //-------------------------------------------------------------------------
    // Instance Variables

    // The collection of cycles
    private final Map<String,Cycle> cycles;

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
     * Gets the names of the cycles.
     * @return The names
     */
    public List<String> getCycleNames() {
        return new ArrayList<>(cycles.keySet());
    }

    /**
     * Gets a cycle given its name.
     * @param name The name
     * @return The cycle, or null if not found
     */
    public Cycle getCycle(String name) {
        return cycles.get(name);
    }

    /**
     * Sets the desired day.
     * @param newDay The new day
     */
    public void setDay(int newDay) {
        cycles.values().forEach(c -> c.setDay(newDay));
    }

    //-------------------------------------------------------------------------
    // Builder

    public static class Builder {
        private final Map<String,Cycle> cycles = new LinkedHashMap<>();

        public Builder add(String name, Cycle cycle) {
            cycles.put(name, cycle);
            return this;
        }

        public Orrery build() {
            return new Orrery(this);
        }
    }


}
