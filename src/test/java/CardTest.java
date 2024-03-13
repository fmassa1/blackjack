import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CardTest {
    @Test
    public void testGetSuit() {
        Card card = new Card("Hearts", 10);
        assertEquals("Hearts", card.getSuit());
    }

    @Test
    public void testGetValue() {
        Card card = new Card("Diamonds", 7);
        assertEquals(7, card.getValue());
    }

    @Test
    public void testCardInitialization() {
        Card card = new Card("Spades", 13);
        assertEquals("Spades", card.getSuit());
        assertEquals(13, card.getValue());
    }
}
