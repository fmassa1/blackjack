// Ricky Massa and Zakareah Hafeez
// 03/12/2024
// Card.java
// class for the cards
public class Card {
    //sets properties to card (suit,value)
    private String suit;
    private int value;
    public Card(String suit, int value) {
        this.suit = suit;
        this.value = value;
    }
    //gets suit and val
    public String getSuit(){
        return this.suit;
    }
    public int getValue(){
        return this.value;
    }
}
