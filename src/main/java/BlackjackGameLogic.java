import java.util.ArrayList;
import java.util.Collections;

public class BlackjackGameLogic {
    public String whoWon(ArrayList <Card> playerHand1, ArrayList<Card> dealerHand) {
        int player = handTotal(playerHand1);
        int dealer = handTotal(dealerHand);
        if (player > dealer) {
            return "player";
        }
        if (player < dealer) {
            return "dealer";
        }
        return "push";
    }
    public int handTotal(ArrayList<Card> hand) {
        int total = 0;
        for (Card card : hand) {
            total += card.getValue();
        }
        return total;
    }
    public boolean evaluateBankerDraw(ArrayList<Card> hand) {
        int total = 0;
        for (Card card : hand) {
            total += card.getValue();
        }
        return total <= 16;
    }
}
