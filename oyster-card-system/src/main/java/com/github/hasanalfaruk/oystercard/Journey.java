package com.github.hasanalfaruk.oystercard;

public class Journey {
    
    // "Tube" or "Bus"
    private final String type; 
    
    private final Station startStation;
    private final Station endStation;

    public Journey(String type, Station startStation, Station endStation) {
        this.type = type;
        this.startStation = startStation;
        this.endStation = endStation;
    }

    public String getType() {
        return type;
    }

    public Station getStartStation() {
        return startStation;
    }

    public Station getEndStation() {
        return endStation;
    }

    public boolean isBusJourney() {
        return "Bus".equalsIgnoreCase(type);
    }
}
