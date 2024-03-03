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
        boolean aceFound = false;
        int total = 0;
        for (Card card : hand) {
            //if face card sets value to 10
            if(card.getValue() == 11 || card.getValue() == 12 || card.getValue() == 13) {
                total += 10;
            }
            //if ace defaults to 11
            else if(card.getValue() == 1) {
                total += 11;
                aceFound = true;
            }
            else {
                total += card.getValue();
            }
        }
        //if ace in hand and goes over 21 ace turns into a 1
        if(aceFound && total > 21){total -= 10;}
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
