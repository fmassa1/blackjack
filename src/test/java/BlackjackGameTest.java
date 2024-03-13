import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
public class BlackjackGameTest {

    private BlackjackGame game = new BlackjackGame();
    private ArrayList <Card> player;
    private ArrayList <Card> banker;

    private Card H1 = new Card("Hearts", 1);
    private Card H5 = new Card("Hearts", 5);
    private Card HK = new Card("Hearts", 13);
    private Card H10 = new Card("Hearts", 10);



    @Test
    public void blackJackCheckerWithBlackJack() {
        player = new ArrayList<>();
        player.add(new Card("Hearts", 1));
        player.add(new Card("Hearts", 13));
        assertTrue(game.blackJackChecker(player));
    }
    @Test
    public void blackJackCheckerWithBlackJack2() {
        player = new ArrayList<>();
        player.add(new Card("Hearts", 1));
        player.add(new Card("Hearts", 10));
        assertTrue(game.blackJackChecker(player));
    }
    @Test
    public void blackJackCheckerWithOutBlackJack() {
        player = new ArrayList<>();
        player.add(new Card("Hearts", 1));
        player.add(new Card("Hearts", 9));
        assertFalse(game.blackJackChecker(player));
    }
    @Test
    public void blackJackCheckerWithOutBlackJack2() {
        player = new ArrayList<>();
        player.add(new Card("Hearts", 10));
        player.add(new Card("Hearts", 11));
        assertFalse(game.blackJackChecker(player));
    }
    @Test
    public void blackJackCheckerTooManyCards() {
        player = new ArrayList<>();
        player.add(new Card("Hearts", 1));
        player.add(new Card("Hearts", 9));
        player.add(new Card("Hearts", 3));
        assertFalse(game.blackJackChecker(player));
    }
    @Test
    public void gameStartTest() {
        game.beginGame();
        assertEquals(game.getBankerCards().size(), 2);
        assertEquals(game.getUserCards().size(), 2);
    }

    @Test
    public void evalWinningsPlayer(){
        game.setBet(1.0);
        game.setPlayerHand(H10, H10);
        game.setBankerHand(H5, H5);
        assertEquals(game.evaluateWinnings(), 2.0);
    }
    @Test
    public void evalWinningsBanker(){
        game.setBet(1.0);
        game.setPlayerHand(H5, H5);
        game.setBankerHand(H10, H10);
        assertEquals(game.evaluateWinnings(), 0.0);
    }
    @Test
    public void evalWinningsTie(){
        game.setBet(1.0);
        game.setPlayerHand(H10, H10);
        game.setBankerHand(H10, H10);
        assertEquals(game.evaluateWinnings(), 1.0);
    }
    @Test
    public void evalWinningsBankerBlackjack(){
        game.setBet(1.0);
        game.setPlayerHand(H10, H10);
        game.setBankerHand(H1, H10);
        assertEquals(game.evaluateWinnings(), 0.0);
    }
    @Test
    public void evalWinningsUserBlackjack(){
        game.setBet(1.0);
        game.setPlayerHand(H1, H10);
        game.setBankerHand(H10, H10);
        assertEquals(game.evaluateWinnings(), 3.0);
    }
}
