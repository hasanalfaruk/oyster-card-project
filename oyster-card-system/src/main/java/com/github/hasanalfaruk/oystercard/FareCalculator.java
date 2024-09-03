package com.github.hasanalfaruk.oystercard;

import java.util.Set;

public class FareCalculator {
    
    private static final double ZONE_1_ONLY_FARE = 2.50;
    private static final double ONE_ZONE_OUTSIDE_ZONE_1_FARE = 2.00;
    private static final double TWO_ZONES_INCLUDING_ZONE_1_FARE = 3.00;
    private static final double TWO_ZONES_EXCLUDING_ZONE_1_FARE = 2.25;
    private static final double THREE_ZONES_FARE = 3.20;
    private static final double BUS_FARE = 1.80;
    private static final double MAX_FARE = 3.20;

    public static double getMaxFare() {
        return MAX_FARE;
    }

    public double calculateFare(Journey journey) {
        if (journey.isBusJourney()) {
            return BUS_FARE;
        }

        Set<Integer> startZones = journey.getStartStation().getZones();
        Set<Integer> endZones = journey.getEndStation().getZones();

        if (startZones.contains(1) && endZones.contains(1) && startZones.size() == 1 && endZones.size() == 1) {
            return ZONE_1_ONLY_FARE;
        }

        if (startZones.contains(1) || endZones.contains(1)) {
            if (startZones.size() == 1 && endZones.size() == 1) {
                return TWO_ZONES_INCLUDING_ZONE_1_FARE;
            }
            return ONE_ZONE_OUTSIDE_ZONE_1_FARE;
        }

        int zoneDifference = Math.abs(startZones.iterator().next() - endZones.iterator().next());
        if (zoneDifference == 1) {
            return TWO_ZONES_EXCLUDING_ZONE_1_FARE;
        } else if (zoneDifference == 2) {
            return THREE_ZONES_FARE;
        }

        return MAX_FARE;
    }

}
