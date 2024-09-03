import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Set;
import com.github.hasanalfaruk.oystercard.FareCalculator;
import com.github.hasanalfaruk.oystercard.Journey;
import com.github.hasanalfaruk.oystercard.Station;

public class FareCalculatorTest {

    private final FareCalculator fareCalculator = new FareCalculator();

    // Test calculating fare for a bus journey
    @Test
    public void testCalculateBusFare() {
        Journey busJourney = new Journey("Bus", null, null);
        assertEquals(1.80, fareCalculator.calculateFare(busJourney));
    }

    // Test calculating fare for a Zone 1 only journey
    @Test
    public void testCalculateZone1OnlyFare() {
        Station holborn = new Station("Holborn", Set.of(1));
        Station earlsCourt = new Station("Earl's Court", Set.of(1));
        Journey journey = new Journey("Tube", holborn, earlsCourt);

        assertEquals(2.50, fareCalculator.calculateFare(journey));
    }

    // Test calculating fare for two zones including Zone 1
    @Test
    public void testCalculateTwoZonesIncludingZone1Fare() {
        Station holborn = new Station("Holborn", Set.of(1));
        Station hammersmith = new Station("Hammersmith", Set.of(2));
        Journey journey = new Journey("Tube", holborn, hammersmith);

        assertEquals(3.00, fareCalculator.calculateFare(journey));
    }

    // Test calculating fare for two zones excluding Zone 1
    @Test
    public void testCalculateTwoZonesExcludingZone1Fare() {
        Station hammersmith = new Station("Hammersmith", Set.of(2));
        Station wimbledon = new Station("Wimbledon", Set.of(3));
        Journey journey = new Journey("Tube", hammersmith, wimbledon);

        assertEquals(2.25, fareCalculator.calculateFare(journey));
    }

    // Test calculating fare for three zones 
    @Test
    public void testCalculateThreeZonesFare() {
        Station holborn = new Station("Holborn", Set.of(1));
        Station wimbledon = new Station("Wimbledon", Set.of(3));
        Journey journey = new Journey("Tube", holborn, wimbledon);

        assertEquals(3.20, fareCalculator.calculateFare(journey));
    }
    
}
