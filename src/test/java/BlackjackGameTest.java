import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
public class BlackjackGameTest {

    private BlackjackGame game = new BlackjackGame();
    private ArrayList <Card> player;
    private ArrayList <Card> banker;

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
}
