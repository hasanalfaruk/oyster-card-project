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
        double minimumFare = MAX_FARE; 

        // Calculate all possible fares based on the zone combinations
        for (int startZone : startZones) {
            for (int endZone : endZones) {
                double fare = calculateFareForZoneCombination(startZone, endZone);
                minimumFare = Math.min(minimumFare, fare); // Keep the minimum fare
            }
        }
        // Return the minimum possible fare for stations spanning multiple zones
        return minimumFare;
    }

    private double calculateFareForZoneCombination(int startZone, int endZone) {

        boolean includesZone1 = (startZone == 1 || endZone == 1);
        int zoneDifference = Math.abs(startZone - endZone);

        // £2.50 fare for travel within Zone 1
        if (startZone == endZone && includesZone1) {
            return ZONE_1_ONLY_FARE;  
        } 
        // £2.00 fare for travel in the same zone outside Zone 1
        else if (zoneDifference == 0) {
            return ONE_ZONE_OUTSIDE_ZONE_1_FARE; 
        } 
        // £2.00 fare for crossing 1 zone outside Zone 1
        else if (!includesZone1 && zoneDifference == 0) {
            return ONE_ZONE_OUTSIDE_ZONE_1_FARE;  
        } 
        // £3.00 fare for travlleing two zones including Zone 1
        else if (includesZone1 && zoneDifference == 1) {
            return TWO_ZONES_INCLUDING_ZONE_1_FARE;  
        } 
        // £2.25 fare for travelling two zones excluding Zone 1
        else if (!includesZone1 && zoneDifference == 1) {
            return TWO_ZONES_EXCLUDING_ZONE_1_FARE;  
        } 
        else if (zoneDifference == 3) {
            return THREE_ZONES_FARE;  
        }

        return MAX_FARE;
    }

}
