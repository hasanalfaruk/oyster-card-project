package com.github.hasanalfaruk.oystercard;
import java.util.Set;

public class Station {

    private final String name;
    private final Set<Integer> zones;

    public Station(String name, Set<Integer> zones) {
        this.name = name;
        this.zones = zones;
    }

    public String getName() {
        return name;
    }

    public Set<Integer> getZones() {
        return zones;
    }

    public boolean isInZone(int zone) {
        return zones.contains(zone);
    }
    
}
