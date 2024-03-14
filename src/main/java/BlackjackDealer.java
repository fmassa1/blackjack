// Ricky Massa and Zaki Hafeez
// 03/12/2024
// BlackjackDealer.java
// dealer class that takes care of everything involving the deck
import java.util.ArrayList;
import java.util.Collections;

public class BlackjackDealer {
    private ArrayList<Card> deck;
    private int cardsLeft;

    public BlackjackDealer() {
        shuffleDeck();
    }
    public void  generateDeck() {
        deck = new ArrayList<>();
        cardsLeft = 52;
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        for(String suit : suits) {
            for(int i = 1; i < 14; i++){
                Card newCard = new Card(suit, i);
                deck.add(newCard);
            }
        }
    }
    public void shuffleDeck(){
        generateDeck();
        Collections.shuffle(deck);
    }
    public ArrayList<Card> dealHand() {
        if(cardsLeft < 2) {
            return null;
        }
        ArrayList<Card> hand = new ArrayList<Card>();
        hand.add(drawOne());
        hand.add(drawOne());
        return hand;

    }
    public Card drawOne() {
        if(cardsLeft == 0) {
            return null;
        }
        Card drawn = deck.get(0);
        deck.remove(0);
        cardsLeft--;
        return drawn;
    }
    public int deckSize(){
        return cardsLeft;
    }
    public ArrayList<Card> allCardsInDeck() {
        return deck;
    }
}

