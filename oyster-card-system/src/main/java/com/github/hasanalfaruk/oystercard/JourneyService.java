package com.github.hasanalfaruk.oystercard;

public class JourneyService {

    private final OysterCard oysterCard;
    private final FareCalculator fareCalculator;
    private Station entryStation;

    public JourneyService(OysterCard oysterCard, FareCalculator fareCalculator) {
        this.oysterCard = oysterCard;
        this.fareCalculator = fareCalculator;
    }

    public void enterStation(Station station) {
        if (oysterCard.getBalance() < FareCalculator.getMaxFare()) {
            throw new IllegalArgumentException("Insufficient balance for entry.");
        }
        entryStation = station;
        oysterCard.deduct(FareCalculator.getMaxFare());
    }

    // Exiting without entry
    public void exitStation(Station station) {
        if (entryStation == null) {
            oysterCard.deduct(FareCalculator.getMaxFare()); 
            return;
        }
        Journey journey = new Journey("Tube", entryStation, station);
        double realFare = fareCalculator.calculateFare(journey);
        oysterCard.add(FareCalculator.getMaxFare() - realFare);
        entryStation = null;
    }

    public void takeBusJourney() {
        Journey busJourney = new Journey("Bus", null, null);
        double busFare = fareCalculator.calculateFare(busJourney);
        oysterCard.deduct(busFare);
    }
    
}
