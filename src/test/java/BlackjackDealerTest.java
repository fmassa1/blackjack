
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class BlackjackDealerTest {

    @Test
    public void checkSize() {
        BlackjackDealer dealer = new BlackjackDealer();
        assertEquals(dealer.deckSize(), 52);
    }
    @Test
    public void sizeAfterDrawOne() {
        BlackjackDealer dealer = new BlackjackDealer();
        dealer.drawOne();
        assertEquals(dealer.deckSize(), 51);
    }
    @Test
    public void sizeAfterDealHand() {
        BlackjackDealer dealer = new BlackjackDealer();
        dealer.dealHand();
        assertEquals(dealer.deckSize(), 50);
    }

    @Test
    public void testDrawingAllCards() {
        BlackjackDealer dealer = new BlackjackDealer();
        for (int i = 0; i < 52; i++) {
            assertNotNull(dealer.drawOne());
        }
        assertNull(dealer.drawOne());
    }

    @Test
    public void testShuffleDeck() {
        BlackjackDealer dealer1 = new BlackjackDealer();
        BlackjackDealer dealer2 = new BlackjackDealer();
        dealer2.shuffleDeck();
        assertNotEquals(dealer1.drawOne().toString(), dealer2.drawOne().toString());
    }

}
