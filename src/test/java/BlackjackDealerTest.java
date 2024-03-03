
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
}
