import java.util.ArrayList;
import java.util.Collections;

public class BlackjackGameLogic {
    public String whoWon(ArrayList <Card> playerHand1, ArrayList<Card> dealerHand) {
        int player = handTotal(playerHand1);
        int dealer = handTotal(dealerHand);
        if(player == dealer) {
            return "push";
        }
        if (player > dealer || dealer > 21) {
            return "player";
        }
        return "dealer";
    }
    public int handTotal(ArrayList<Card> hand) {
        boolean aceFound = false;
        int numAce = 0;
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
                numAce += 1;
            }
            else {
                total += card.getValue();
            }
        }
        //if ace in hand and goes over 21 ace turns into a 1
        if(aceFound && total > 21){
            while(total > 21 && numAce != 0) {
                total -= 10;
                numAce -= 1;
            }
        }
        return total;
    }
    public boolean evaluateBankerDraw(ArrayList<Card> hand) {
        return handTotal(hand) <= 16;
    }
}
