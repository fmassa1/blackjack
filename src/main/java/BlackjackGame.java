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


}
