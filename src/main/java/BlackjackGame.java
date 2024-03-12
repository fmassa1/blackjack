import java.util.ArrayList;
import java.util.Collections;
public class BlackjackGame {
    private ArrayList<Card> playerHand;
    private ArrayList<Card> bankerHand;
    private BlackjackDealer theDealer;
    private BlackjackGameLogic gameLogic;
    private double currentBet;
    private double totalWinnings;

    private double userMoney;

    public BlackjackGame () {
        this.theDealer = new BlackjackDealer();
        this.gameLogic = new BlackjackGameLogic();
    }
    //checks if a hand has a blackjack
    public boolean blackJackChecker(ArrayList<Card> cards) {
        if(cards.get(0).getValue() == 1 && (cards.get(1).getValue() == 11 || cards.get(1).getValue() == 12 || cards.get(1).getValue() == 13)) {
            return true;
        }
        if(cards.get(1).getValue() == 1 && (cards.get(0).getValue() == 11 || cards.get(0).getValue() == 12 || cards.get(0).getValue() == 13)) {
            return true;
        }
        return false;
    }

    public double evaluateWinnings() {
        return 0.0;
    }

    //sets up the beginning of the game
    public void beginGame(){
        this.playerHand = new ArrayList<>();
        this.bankerHand = new ArrayList<>();
        this.playerHand = theDealer.dealHand();
        this.bankerHand = theDealer.dealHand();
    }
    public void playerHit() {this.playerHand.add(theDealer.drawOne());}
    public void bankerHit() {
        while (gameLogic.evaluateBankerDraw(bankerHand))
            this.bankerHand.add(theDealer.drawOne());
    }

    public int getUserCardTotal(){
        return gameLogic.handTotal(playerHand);
    };
    public void setUserMoney(double money) {this.userMoney = money;}
    public double getUserMoney() {return userMoney;}
    public void setBet(double bet) {this.currentBet = bet;}
    public double getBet() {
        return this.currentBet;
    }
    public ArrayList<Card> getUserCards() {return playerHand;}
    public ArrayList<Card> getBankerCards() {return bankerHand;}

    public String winner() {
        return gameLogic.whoWon(playerHand,bankerHand);
    }
    public void shuffleChecker(){
        if(theDealer.deckSize() < 26) {
            theDealer.shuffleDeck();
        }
    }

}
