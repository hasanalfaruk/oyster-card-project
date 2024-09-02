import static org.junit.jupiter.api.Assertions.*;
import com.github.hasanalfaruk.oystercard.Station;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class StationTest {

    // Test station creation and correct zones 
    @Test
    public void testStationCreation() {
        Station station = new Station("Holborn", Set.of(1));
        assertEquals("Holborn", station.getName());
        assertTrue(station.isInZone(1)); 
        assertFalse(station.isInZone(2));
    }
}
