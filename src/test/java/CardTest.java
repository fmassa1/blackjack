// Ricky Massa and Zakareah Hafeez
// 03/12/2024
// CardTest.java
// Testing for Card.java, Tests initalizing Card
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CardTest {
    @Test
    public void testGetSuit() {
        Card card = new Card("Hearts", 10);
        assertEquals("Hearts", card.getSuit());
    }
    @Test
    public void testGetSuitMultiCard() {
        Card card = new Card("Hearts", 10);
        Card card2 = new Card("Clubs", 2);
        assertEquals("Hearts", card.getSuit());
        assertEquals("Clubs", card2.getSuit());
    }

    @Test
    public void testGetValue() {
        Card card = new Card("Diamonds", 7);
        assertEquals(7, card.getValue());
    }
    @Test
    public void testGetValueMultiCards() {
        Card card = new Card("Diamonds", 7);
        Card card2 = new Card("Diamonds", 10);
        assertEquals(7, card.getValue());
        assertEquals(10, card2.getValue());
    }

    @Test
    public void testCardInitialization() {
        Card card = new Card("Spades", 13);
        assertEquals("Spades", card.getSuit());
        assertEquals(13, card.getValue());
    }
}
