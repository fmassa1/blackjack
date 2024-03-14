// Ricky Massa and Zakareah Hafeez
// 03/12/2024
// BlackjackGame.java
// class that takes care of all the main function of the game
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

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
        if(cards.size() != 2) {
            return false;
        }
        if(cards.get(0).getValue() == 1 && (cards.get(1).getValue() == 10 || cards.get(1).getValue() == 11 || cards.get(1).getValue() == 12 || cards.get(1).getValue() == 13)) {
            return true;
        }
        if(cards.get(1).getValue() == 1 && (cards.get(0).getValue() == 10 || cards.get(0).getValue() == 11 || cards.get(0).getValue() == 12 || cards.get(0).getValue() == 13)) {
            return true;
        }
        return false;
    }

    public double evaluateWinnings() {
        if((blackJackChecker(playerHand) && blackJackChecker(bankerHand))) {
            return totalWinnings/2;
        }
        if(blackJackChecker(bankerHand) || Objects.equals(winner(), "dealer")) {
            return 0.0;
        }
        if(blackJackChecker(playerHand)) {
            return 1.5 * totalWinnings;
        }
        if(Objects.equals(winner(), "push")) {
            return totalWinnings/2;
        }
        return totalWinnings;
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
    public void setBet(double bet) {
        this.currentBet = bet;
        this.totalWinnings = bet * 2;
    }
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

    //
    //these are used only for testing
    //
    public void setPlayerHand(Card c1, Card c2){
        playerHand = new ArrayList<>();
        playerHand.add(c1);
        playerHand.add(c2);
    }
    public void setBankerHand(Card c1, Card c2){
        bankerHand = new ArrayList<>();
        bankerHand.add(c1);
        bankerHand.add(c2);
    }
    public int deckSize() {
        return theDealer.deckSize();
    }
}
