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

    //sets up the beginning of the game
    public void beginGame(double bet){
        setBet(bet);
        this.playerHand = theDealer.dealHand();
        this.bankerHand = theDealer.dealHand();
    }
    public void setBet(double bet) {this.currentBet = bet;}
    public double getBet() {
        return currentBet;
    }
    public ArrayList<Card> getUserCards() {return playerHand;}
    public ArrayList<Card> getBankerCards() {return bankerHand;}


}
