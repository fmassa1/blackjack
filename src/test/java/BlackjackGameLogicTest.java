// Ricky Massa and Zakareah Hafeez
// 03/12/2024
// BlackjackGameLogic.java
// Tests BlackJackGameLogic:Who wins game and on what principals. Testing for hand calculations
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
public class BlackjackGameLogicTest {
    private ArrayList <Card> player;
    private ArrayList <Card> banker;
    private BlackjackGameLogic logic = new BlackjackGameLogic();

    @Test
    public void handTotalBasic() {
        player = new ArrayList<>();
        player.add((new Card("Hearts", 10)));
        player.add((new Card("Hearts", 9)));
        assertEquals(logic.handTotal(player), 19);
    }
    @Test
    public void handTotalLotsOfAces() {
        player = new ArrayList<>();
        player.add((new Card("Hearts", 1)));
        player.add((new Card("Hearts", 1)));
        player.add((new Card("Hearts", 1)));
        player.add((new Card("Hearts", 1)));
        player.add((new Card("Hearts", 10)));
        assertEquals(logic.handTotal(player), 14);
    }
    @Test
    public void handTotalBlackJack() {
        player = new ArrayList<>();
        player.add((new Card("Hearts", 1)));
        player.add((new Card("Hearts", 13)));
        assertEquals(logic.handTotal(player), 21);
    }

    @Test
    public void playerWins() {
        player = new ArrayList<>();
        player.add((new Card("Hearts", 10)));
        player.add((new Card("Hearts", 9)));
        banker = new ArrayList<>();
        banker.add((new Card("Hearts", 10)));
        banker.add((new Card("Hearts", 7)));
        assertEquals(logic.whoWon(player, banker), "player");
    }
    @Test
    public void bankerWins() {
        player = new ArrayList<>();
        player.add((new Card("Hearts", 10)));
        player.add((new Card("Hearts", 7)));
        banker = new ArrayList<>();
        banker.add((new Card("Hearts", 10)));
        banker.add((new Card("Hearts", 9)));
        assertEquals(logic.whoWon(player, banker), "dealer");
    }
    @Test
    public void tieGame() {
        player = new ArrayList<>();
        player.add((new Card("Hearts", 10)));
        player.add((new Card("Hearts", 7)));
        banker = new ArrayList<>();
        banker.add((new Card("Hearts", 10)));
        banker.add((new Card("Hearts", 7)));
        assertEquals(logic.whoWon(player, banker), "push");
    }
    @Test
    public void evaluateBankersDrawBig() {
        banker = new ArrayList<>();
        banker.add((new Card("Hearts", 10)));
        banker.add((new Card("Hearts", 9)));
        assertFalse(logic.evaluateBankerDraw(banker));
    }
    @Test
    public void evaluateBankersDrawSmall() {
        banker = new ArrayList<>();
        banker.add((new Card("Hearts", 4)));
        banker.add((new Card("Hearts", 5)));
        assertTrue(logic.evaluateBankerDraw(banker));
    }
}
