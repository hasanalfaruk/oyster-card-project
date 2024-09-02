import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.github.hasanalfaruk.oystercard.OysterCard;

public class OysterCardTest {
    
    private OysterCard card;

    // Test creating an Oyster card with a balance of 30.0
    @BeforeEach
    public void setUp() {
        card = new OysterCard(30.0);  
    }

    // Test if balance is set correctly
    @Test
    public void testInitialBalance() {
        assertEquals(30.0, card.getBalance());  
    }

    // Test adding balance by 10.0
    @Test
    public void testAddBalance() {
        card.add(10.0);
        assertEquals(40.0, card.getBalance()); 
    }

    // Test deducting balance by 5.0
    @Test
    public void testDeductBalance() {
        card.deduct(5.0);
        assertEquals(25.0, card.getBalance()); 
    }

    // Test insufficient balance exception handling
    @Test
    public void testDeductInsufficientBalance() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> card.deduct(50.0));
        assertEquals("Insufficient balance", exception.getMessage());
    }
}
