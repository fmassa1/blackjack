
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
    public void testHandDeal() {
        BlackjackDealer dealer = new BlackjackDealer();
        ArrayList<Card> hand = dealer.dealHand();
        assertEquals(2, hand.size());
    }

    @Test
    public void testDeckSizeAfterMultiDraw() {
        BlackjackDealer dealer = new BlackjackDealer();
        dealer.drawOne();
        dealer.dealHand();
        assertEquals(49, dealer.deckSize());
    }

    @Test
    public void testGetSuit() {
        Card card = new Card("Hearts", 1);
        assertEquals("Hearts", card.getSuit());
    }

    @Test
    public void testBankerHits() {
        BlackjackGame game = new BlackjackGame();
        game.beginGame();
        int initialSize = game.getBankerCards().size();
        game.bankerHit();
        assertEquals(initialSize + 1, game.getBankerCards().size());
    }


}
