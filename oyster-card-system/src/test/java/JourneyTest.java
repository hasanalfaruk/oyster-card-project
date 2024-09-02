import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
import com.github.hasanalfaruk.oystercard.Journey;
import com.github.hasanalfaruk.oystercard.Station;

public class JourneyTest {

     // Test Tube journey creation
    @Test
    public void testTubeJourneyCreation() {
        Station start = new Station("Holborn", Set.of(1));
        Station end = new Station("Earl's Court", Set.of(1, 2));
        Journey journey = new Journey("Tube", start, end);

        assertEquals("Tube", journey.getType());
        assertEquals(start, journey.getStartStation());
        assertEquals(end, journey.getEndStation());
        assertFalse(journey.isBusJourney());
    }

    // Test Bus journey creation
    @Test
    public void testBusJourneyCreation() {
        Journey journey = new Journey("Bus", null, null);

        assertEquals("Bus", journey.getType());
        assertNull(journey.getStartStation());
        assertNull(journey.getEndStation());
        assertTrue(journey.isBusJourney());
    }
    
}
