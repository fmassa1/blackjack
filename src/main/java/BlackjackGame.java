import java.util.ArrayList;
import java.util.Collections;
public class BlackjackGame {
    private ArrayList<Card> playerHand;
    private ArrayList<Card> bankerHand;
    private BlackjackDealer theDealer;
    private BlackjackGameLogic gameLogic;
    private double currentBet;
    private double totalWinnings;

    public double evaluateWinnings() {
        return 0.0;
    }

    public void setBet(double bet) {
        this.currentBet = bet;
    }
    public double getBet() {
        return currentBet;
    }

}
