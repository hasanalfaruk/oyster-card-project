package com.github.hasanalfaruk.oystercard;

import java.util.ArrayList;
import java.util.List;

public class JourneyService {

    private final OysterCard oysterCard;
    private final FareCalculator fareCalculator;
    private double currentJourneyFare;
    private final List<String> journeyDetails;
    private Station startStation;

    public JourneyService(OysterCard oysterCard, FareCalculator fareCalculator) {
        this.oysterCard = oysterCard;
        this.fareCalculator = fareCalculator;
        this.journeyDetails = new ArrayList<>();
    }

    public void enterStation(Station station) {
        if (oysterCard.getBalance() < FareCalculator.getMaxFare()) {
            throw new IllegalArgumentException("Insufficient balance for entry.");
        }
        oysterCard.deduct(FareCalculator.getMaxFare());
        currentJourneyFare = FareCalculator.getMaxFare();  
        startStation = station;
    }

    // Exiting without entry
    public void exitStation(Station station) {
        if (startStation == null) {
            oysterCard.deduct(FareCalculator.getMaxFare()); 
            journeyDetails.add("Penalty Fare for exiting without entry: £3.20");
        } 
        else {
            double fare = fareCalculator.calculateFare(new Journey("Tube", startStation, station));
            oysterCard.add(currentJourneyFare - fare);
            currentJourneyFare = 0;

            String journeyDetail = String.format("Tube: %s to %s Fare: £%.2f", startStation.getName(), station.getName(), fare);
            journeyDetails.add(journeyDetail);
            startStation = null;

        }
    }

    public void takeBusJourney() {
        double busFare = fareCalculator.calculateFare(new Journey("Bus", null, null));
        oysterCard.deduct(busFare);

        journeyDetails.add(String.format("Bus: Earl's Court to Chelsea Fare: £%.2f", busFare));
    }
    
    public void printJourneyDetails() {
        for (String detail : journeyDetails) {
            System.out.println(detail);
        }
        System.out.println("Remaining Balance: £" + oysterCard.getBalance());
    }

}
