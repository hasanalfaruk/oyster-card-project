package com.github.hasanalfaruk.oystercard;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        Station holborn = new Station("Holborn", Set.of(1));
        Station earlsCourt = new Station("Earl's Court", Set.of(1, 2));
        Station hammersmith = new Station("Hammersmith", Set.of(2));

        OysterCard card = new OysterCard(30.0);
        FareCalculator fareCalculator = new FareCalculator();
        JourneyService journeyService = new JourneyService(card, fareCalculator);

        // Journey 1: Tube: Holborn to Earl’s Court
        journeyService.enterStation(holborn);
        journeyService.exitStation(earlsCourt);

        // Journey 2: 328 bus from Earl’s Court to Chelsea
        journeyService.takeBusJourney();

        // Journey 3: Tube: Earl’s Court to Hammersmith
        journeyService.enterStation(earlsCourt);
        journeyService.exitStation(hammersmith);

        // Final balance:
        journeyService.printJourneyDetails();

    }
}