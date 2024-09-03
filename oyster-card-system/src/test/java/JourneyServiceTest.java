import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.github.hasanalfaruk.oystercard.FareCalculator;
import com.github.hasanalfaruk.oystercard.JourneyService;
import com.github.hasanalfaruk.oystercard.OysterCard;
import java.util.Set;
import com.github.hasanalfaruk.oystercard.Station;

public class JourneyServiceTest {

    private OysterCard card;
    private FareCalculator fareCalculator;
    private JourneyService journeyService;
    private Station holborn;
    private Station earlsCourt;
    private Station hammersmith;

    @BeforeEach
    public void setUp() {
        card = new OysterCard(30.0);
        fareCalculator = new FareCalculator();
        journeyService = new JourneyService(card, fareCalculator);
        holborn = new Station("Holborn", Set.of(1));
        earlsCourt = new Station("Earl's Court", Set.of(1, 2));
        hammersmith = new Station("Hammersmith", Set.of(2));
    }

    // Test entering a station with sufficient balance
    @Test
    public void testEnterStationWithSufficientBalance() {
        journeyService.enterStation(holborn);
        assertEquals(26.80, card.getBalance());  // Check balance after max fare deduction
    }

    // Test entering a station with insufficient balance
    @Test
    public void testEnterStationWithInsufficientBalance() {
        OysterCard lowBalanceCard = new OysterCard(2.0);
        JourneyService lowBalanceService = new JourneyService(lowBalanceCard, fareCalculator);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> lowBalanceService.enterStation(holborn));
        assertEquals("Insufficient balance for entry.", exception.getMessage());
    }

    // Test exiting a station after a valid entry
    @Test
    public void testExitStationAfterEntry() {
        journeyService.enterStation(holborn);
        journeyService.exitStation(earlsCourt);
        assertEquals(27.50, card.getBalance());  // Check balance after the correct fare is applied.
    }
    
    // Test exiting a station without a prior entry (Deduct Max Fare)
    @Test
    public void testExitStationWithoutEntry() {
        journeyService.exitStation(holborn);
        assertEquals(26.80, card.getBalance());  // Check balance after applying the maximum fare as penalty
    }   

    // Test taking a bus journey
    @Test
    public void testBusJourney() {
        journeyService.takeBusJourney();
        assertEquals(28.20, card.getBalance());  // Check balance after deducting bus fare
    }

    // Test multiple journeys
    @Test
    public void testMultipleJourneys() {
        // Tube: Holborn to Earl’s Court
        journeyService.enterStation(holborn);
        journeyService.exitStation(earlsCourt);
        assertEquals(27.50, card.getBalance());

        // Bus: 328 from Earl’s Court to Chelsea
        journeyService.takeBusJourney();
        assertEquals(25.70, card.getBalance());

        // Tube: Earl’s Court to Hammersmith
        journeyService.enterStation(earlsCourt);
        journeyService.exitStation(hammersmith);
        assertEquals(23.70, card.getBalance());
    }
    
}
