
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

    @Test
    public void testBankerHits() {
        BlackjackGame game = new BlackjackGame();
        game.beginGame();
        int initialSize = game.getBankerCards().size();
        game.bankerHit();
        assertEquals(initialSize + 1, game.getBankerCards().size());
    }

    @Test
    public void testUserBust() {
        //app start
        BlackjackGame game = new BlackjackGame();
        //money set
        game.setUserMoney(100);
        //game start
        game.beginGame();
        while (game.getUserCardTotal() < 21) {
            game.playerHit();
        }
        game.playerHit();
        //checks if winner string is dealer
        assertEquals("dealer", game.winner());
    }

    @Test
    public void testBankerBust() {
        //app start
        BlackjackGame game = new BlackjackGame();
        //money set
        game.setUserMoney(100);
        //game start
        game.beginGame();
        while (game.getBankerCards() < 21) {
            game.bankerHit;
        }
        game.bankerHit();
        //checks if winner string is player
        assertEquals("player", game.winner());
    }

    @Test
    public void testBankerWin() {
        BlackjackGame game = new BlackjackGame();
        game.setUserMoney(1000);
        game.beginGame();
        while (game.getBankerCardTotal() < 17) {
            game.bankerHit();
        }
        assertEquals("dealer", game.winner());
    }

    @Test
    public void testUserwin() {
        BlackjackGame game = new BlackjackGame();
        game.setUserMoney(1000);
        game.beginGame();
        while (game.getUserCardTotal < 17) {
            game.userHit();
        }
        assertEquals("player", game.winner());
    }

    @Test
    public void testRaiseBet() {

        Game game = new Game();
        game.setUserMoney(100); // Set initial user money
        double initialBet = 10;
        game.setBet(initialBet);
        game.raiseB.setOnAction(event -> game.t2.setText("30")); //raises bet by 30
        //sees if current bet has been raised successful
        assertEquals(initialBet + 30, game.getBet());
    }

    @Test
    public void testRaiseInvalid() {
        // Arrange
        Game game = new Game();
        game.setUserMoney(1000);
        double initialBet = 100;
        game.setBet(initialBet);

        game.raiseB.setOnAction(event -> game.t2.setText("spam")); // invalid input
        //checks if equal to inital bet and not added invalid input to bet pot
        assertEquals(initialBet, game.getBet());
    }

    @Test
    public void testSetandGetBet() {

        Game game = new Game();
        game.setBet(50);
        //tests setBet by utilizeing getBet to retrieve bet
        assertEquals(50, game.getBet());
    }

    @Test
    public void testRaiseExceedsUserMoney() {
        // Arrange
        Game game = new Game();
        game.setUserMoney(1000);
        double initialBet = 100;
        game.setBet(initialBet);

        game.raiseB.setOnAction(event -> game.t2.setText("10000")); // invalid input
        //checks if equal to inital bet and not added invalid input to bet pot
        assertEquals(initialBet, game.getBet());
    }

    @Test
    public void testStandBDisable() {
        // Arrange
        Game game = new Game();
        game.hitB.setDisable(false);
        //clicks StandB
        game.standB.fire(); // Simulate clicking the stand button
        //sees if it is disabled after click
        assertTrue(game.hitB.isDisabled());
    }

    @Test
    public void testStandandRaiseBDisable() {
        // Arrange
        Game game = new Game();
        game.raiseB.setDisable(false);
        game.standB.setDisable(false);
        game.standB.fire();
        assertTrue(game.standB.isDisabled());
        assertTrue(game.raiseB.isDisabled());
    }



}
