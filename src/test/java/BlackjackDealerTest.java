
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
    public void testHandTotal() {

        BlackjackGameLogic logic = new BlackjackGameLogic();
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card("Hearts", 10));
        hand.add(new Card("Diamonds", 5));


        int total = logic.handTotal(hand);

        assertEquals(15, total);
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

}
