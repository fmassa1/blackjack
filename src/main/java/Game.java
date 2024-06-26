// Ricky Massa and Zakareah Hafeez
// 03/12/2024
// Game.java
// main file that supplies all the front end

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import java.util.ArrayList;
import java.util.Objects;

public class Game extends Application {
    //decalration of used variables
    private Button startB, hitB, standB, raiseB, menuB, menu2, betB, nextBet, rules;
    private TextField t1, t2, t3;
    private VBox v1, v2, bankerV, userV, betV, mainV, endHand;
    private HBox h1,h2;

    private Text money, curBet, gameStatus, winnings, balance, titleStatus;

    private BlackjackGame game;

    public ImageView getCardImage(String curCard) {
        Image curImage = new Image(curCard);
        ImageView curView = new ImageView(curImage);
        curView.setFitWidth(100);
        curView.setFitHeight(150);
        return curView;
    }

    public static void main(String[] args) {launch(args);}

    @Override
    public void start(Stage primaryStage) throws Exception {
        BlackjackGame game = new BlackjackGame();

        primaryStage.setTitle("Blackjack");
        BorderPane border = new BorderPane();
        border.setPadding(new Insets(12));

        //start of StartScreen code
        startB = new Button("Start Game");
        startB.setPrefWidth(150);
        rules = new Button("Rules");
        rules.setPrefWidth(150);

        t1 = new TextField();
        t1.setPrefWidth(150);
        t1.setPromptText("enter starting of money");
        t1.setAlignment(Pos.CENTER);
        border.setMargin(t1, new Insets(12,12,12,12));

        Text title = new Text();
        title.setText("Blackjack");
        title.setFont(Font.font("Arial", 64));
        BorderPane.setAlignment(title, Pos.CENTER);

        titleStatus = new Text("Try your luck!");
        titleStatus.setFont(Font.font("Arial", 24));
        BorderPane.setAlignment(titleStatus, Pos.CENTER);

        v1 = new VBox(20, title, titleStatus, rules, startB, t1);
        v1.setAlignment(Pos.CENTER);
        border.setCenter(v1);
        border.setMargin(v1, new Insets(12,450,12,450));

        Scene startScreen = new Scene(border,1200,700);
        //end of StartScreen code

        //start of bet screen
        BorderPane betBorder = new BorderPane();
        betBorder.setPadding(new Insets(12));

        balance = new Text();
        balance.setFont(Font.font("Arial", 24));

        betB = new Button("Begin");
        betB.setAlignment(Pos.CENTER);
        betB.setPrefWidth(150);
        Text betTitle = new Text();
        betTitle.setText("Place your bet");
        betTitle.setFont(Font.font("Arial", 48));
        BorderPane.setAlignment(betTitle, Pos.CENTER);

        t3 = new TextField();
        t3.setPromptText("enter bet amount");
        t3.setPrefWidth(150);
        t3.setAlignment(Pos.CENTER);

        betV = new VBox(20, betTitle, balance, betB, t3);
        betV.setAlignment(Pos.CENTER);
        betBorder.setCenter(betV);
        betBorder.setMargin(betV, new Insets(12,450,12,450));

        Scene betScreen = new Scene(betBorder,1200,700);
        //end of bet screen

        //start of firstScene code
        BorderPane border2 = new BorderPane();
        border2.setPadding(new Insets(12));

        hitB = new Button("Hit");
        standB = new Button("Stand");
        raiseB = new Button("Raise Bet");
        hitB.setPrefWidth(150);
        standB.setPrefWidth(150);
        raiseB.setPrefWidth(150);

        t2 = new TextField();
        t2.setPromptText("enter raise amount");
        t2.setPrefWidth(150);
        t2.setAlignment(Pos.CENTER);

        Text bankerLabel = new Text();
        bankerLabel.setText("Banker's Hand");
        bankerLabel.setFont(Font.font("Arial", 24));
        BorderPane.setAlignment(bankerLabel, Pos.CENTER);

        Text userLabel = new Text();
        userLabel.setText("Player's Hand");
        userLabel.setFont(Font.font("Arial", 24));
        BorderPane.setAlignment(userLabel, Pos.CENTER);

        gameStatus = new Text();
        gameStatus.setFont(Font.font("Arial", 32));
        winnings = new Text();
        gameStatus.setFont(Font.font("Arial", 16));
        nextBet = new Button("");
        nextBet.setText("Place Next Bet");
        nextBet.setPrefWidth(150);
        endHand = new VBox(10, gameStatus, winnings, nextBet);
        endHand.setAlignment((Pos.CENTER));

        menuB = new Button("Return to Menu");
        menuB.setPrefWidth(150);
        menu2 = new Button("Return to Menu");
        menu2.setPrefWidth(150);
        border2.setTop(menuB);
        border2.setCenter(bankerLabel);
        menuB.setAlignment((Pos.CENTER));

        //set background border (rough border)
        betBorder.setStyle(("-fx-background-color: green;"));
        border.setStyle(("-fx-background-color: green;"));
        border2.setStyle(("-fx-background-color: green;"));

        Scene firstScene = new Scene(border2,1200,700);

        startB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    String inputText = t1.getText();
                    double uMoney = Double.parseDouble(inputText);
                    if(uMoney <= 0) {
                        t1.clear();
                        t1.setPromptText("Balance too small!");
                    }
                    else {
                        t1.setPromptText("enter starting of money");
                        game.setUserMoney(Math.round(uMoney * 100.0) / 100.0);
                        money = new Text();
                        balance.setText("Current Balance: $" + game.getUserMoney());
                        money.setFont(Font.font("Arial", 12));
                        BorderPane.setAlignment(money, Pos.CENTER);
                        primaryStage.setScene(betScreen);
                    }

                } catch (NumberFormatException e) {
                    t1.clear();
                    t1.setPromptText("Error: enter a number");
                }
            }
        });
        betB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    String inputText = t3.getText();
                    double bet = Double.parseDouble(inputText);
                    endHand.setVisible(false);
                    if(bet > game.getUserMoney()) {
                        t3.clear();
                        t3.setPromptText("bet too big");
                    }
                    else if (bet <= 0) {
                        t3.clear();
                        t3.setPromptText("bet too small");
                    }
                    else {
                        t3.setPromptText("enter bet amount");
                        game.shuffleChecker();
                        game.beginGame();
                        game.setBet(Math.round(bet * 100.0) / 100.0);
                        game.setUserMoney(game.getUserMoney() - game.getBet());
                        standB.setDisable(false);
                        hitB.setDisable(false);
                        raiseB.setDisable(false);
                        t2.setEditable(true);
                        standB.setText("Stand");
                        hitB.setText("Hit");
                        raiseB.setText("Raise Bet");

                        curBet = new Text();
                        curBet.setText("Current Bet: $" + game.getBet());
                        money.setText("Current Balance: $" + game.getUserMoney());
                        curBet.setFont(Font.font("Arial", 12));
                        BorderPane.setAlignment(curBet, Pos.CENTER);

                        //display user cards
                        ArrayList<Card> userCards = game.getUserCards();
                        h1 = new HBox();
                        for (Card curCard : userCards) {
                            h1.getChildren().add(getCardImage(curCard.getValue() + curCard.getSuit() + ".png"));
                        }
                        h2 = new HBox();
                        h2.getChildren().add(getCardImage(game.getBankerCards().get(0).getValue() + game.getBankerCards().get(0).getSuit() + ".png"));
                        h2.getChildren().add(getCardImage("blank.png"));

                        h1.setAlignment(Pos.CENTER);
                        h2.setAlignment(Pos.CENTER);
                        v2 = new VBox(20, hitB, standB, raiseB, t2, money, curBet);
                        v2.setAlignment(Pos.BOTTOM_CENTER);

                        bankerV = new VBox(10, bankerLabel, h2);
                        bankerV.setAlignment(Pos.CENTER);
                        userV = new VBox(10, h1, userLabel);
                        userV.setAlignment(Pos.CENTER);

                        mainV = new VBox(50, bankerV, endHand, userV);

                        border2.setLeft(v2);
                        border2.setCenter(mainV);
                        border2.setMargin(v2, new Insets(12, 12, 12, 12));
                        border2.setMargin(mainV, new Insets(12, 12, 12, 12));

                        BorderPane.setAlignment(bankerV, Pos.CENTER);
                        BorderPane.setAlignment(userV, Pos.CENTER);

                        primaryStage.setScene(firstScene);
                        if(game.blackJackChecker(game.getBankerCards())) {
                            standB.fire();
                        }
                        else if(game.blackJackChecker(game.getUserCards())) {
                            standB.fire();
                        }
                    }
                } catch (NumberFormatException e) {
                    t3.clear();
                    t3.setPromptText("Error: enter a number");
                }
            }
        });

        //hit button event
        hitB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                game.playerHit();
                ArrayList<Card> userCards = game.getUserCards();
                h1.getChildren().clear();
                for (Card curCard : userCards) {
                    h1.getChildren().add(getCardImage(curCard.getValue() + curCard.getSuit() + ".png"));
                }
                raiseB.setDisable(true);
                raiseB.setText("");
                t2.clear();
                t2.setText("No more bets");
                //when user card value > 21 then disable hit button & disable stand button & output "Bust, Banker Won"
                if(game.getUserCardTotal() > 21){
                    hitB.setDisable(true);
                    hitB.setText("");
                    standB.setDisable(true);
                    standB.setText("");
                    endHand.setVisible(true);
                    gameStatus.setText("User Busted");
                    winnings.setText("Lost $" + game.getBet());
                }
                t2.setEditable(false);
            }
        });
        //raise button and text box event
        raiseB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    String inputText = t2.getText();
                    double raiseAmount = Double.parseDouble(inputText);
                    if(raiseAmount > game.getUserMoney()) {
                        t2.clear();
                        t2.setPromptText("Bet too big!");
                    }
                    else if(raiseAmount <= 0) {
                        t2.clear();
                        t2.setPromptText("Bet too small!");
                    }
                    else {
                        game.setBet(game.getBet()+raiseAmount);
                        game.setUserMoney(game.getUserMoney()-raiseAmount);
                        money.setText("Current Balance: $" + game.getUserMoney());
                        curBet.setText("Current Bet: $" + game.getBet());
                        raiseB.setDisable(true);
                        t2.clear();
                        t2.setText("No more bets");
                        t2.setEditable(false);
                    }
                } catch (NumberFormatException e) {
                    t2.clear();
                    t2.setPromptText("Error: enter a number");
                }
            }
        });

        //stand button event
        standB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!game.blackJackChecker(game.getBankerCards()) && !game.blackJackChecker(game.getUserCards())) {
                    game.bankerHit();
                }
                h2.getChildren().clear();
                for(Card curCard : game.getBankerCards()) {
                    h2.getChildren().add(getCardImage(curCard.getValue() + curCard.getSuit() + ".png"));
                }
                standB.setDisable(true);
                hitB.setDisable(true);
                t2.clear();
                t2.setText("No more bets");
                t2.setEditable(false);

                String winner = game.winner();
                double winMoney = game.evaluateWinnings();
                if(game.blackJackChecker(game.getBankerCards()) && game.blackJackChecker(game.getUserCards())) {
                    gameStatus.setText("Banker and User have Blackjack");
                    winnings.setText("No change in balance");
                }
                else if(game.blackJackChecker(game.getBankerCards())) {
                    gameStatus.setText("Banker has Blackjack");
                    winnings.setText("Lost $" + game.getBet());
                }
                else if(game.blackJackChecker(game.getUserCards())) {
                    gameStatus.setText("User has Blackjack");
                    winnings.setText("Gained $" + winMoney);
                }
                else if(Objects.equals(winner, "dealer")) {
                    gameStatus.setText("Banker has won");
                    winnings.setText("Lost $" + game.getBet());
                }
                else if(Objects.equals(winner, "player")) {
                    gameStatus.setText("User has won!");
                    winnings.setText("Gained $" + game.getBet());
                }
                else {
                    gameStatus.setText("User and Banker Tied");
                    winnings.setText("No change in balance");
                }
                game.setUserMoney(game.getUserMoney() + winMoney);
                endHand.setVisible(true);
            }
        });
        //enables button for continuous game play, redirecting user to betScreen
        nextBet.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                t1.clear();
                t3.clear();
                if(game.getUserMoney() == 0) {
                    primaryStage.setScene(startScreen);
                    titleStatus.setText("Ran out of money. Try again");
                }
                else {
                    balance.setText("Current Balance: $" + game.getUserMoney());
                    money.setText("Current Balance: $" + game.getUserMoney());
                    primaryStage.setScene(betScreen);
                }
            }
        });
        //provides return to menu option to escape gameplay, returns to startScreen
        menuB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(startScreen);
                t1.clear();
                t3.clear();
            }
        });
        //button directs user to screen for Rules
        rules.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Text ruleTitle = new Text("Rules");
                ruleTitle.setFont(Font.font("Arial", 64));
                TextArea allRules= new TextArea();
                allRules.setText("-The goal of blackjack is to beat the dealer's hand without going over 21.\n "
                        + "-Numbered cards (2-10) are worth their face value. Face cards are each worth 10 points.\n"
                        + "-Aces can be worth either 1 or 11 points, depending on which value benefits the player.\n "
                        + "-Gameplay: Player is dealt two cards face up, while the dealer receives one card face up and one card face down.\n "
                        + "-Hit: Player can request additional cards to improve their hand total. They can hit as many times as they like until they stand or bust.\n"
                        + "-Stand: Player can choose to keep their current hand total and not request any additional cards.\n"
                        + "-Bust: If the player's hand total exceeds 21, they bust and lose the round.\n"
                        + "-Dealer's Turn: After the player has completed their hand, the dealer reveals their face-down card and must hit until their hand total is above 16.\n"
                        + "-Winning: Player wins if their hand total is higher than the dealer's without busting. If the dealer busts, the player wins.\n"
                        + "-Push: If the player's hand total is the same as the dealer's, it's a push, and the player's bet is returned.\n"
                        + "-If the player wins with black jack, and Ace and a card with the value 10, they win 1.5x the winnings.\n"
                        + "-If the banker has black jack off in their initial hand it is revealed and the player loses, unless user also has black jack, then it is a push.");

                allRules.setWrapText(true);
                allRules.setEditable(false);
                BorderPane rBorder = new BorderPane();
                ScrollPane scroll = new ScrollPane();
                scroll.setContent(allRules);
                VBox rulesV = new VBox(20,ruleTitle,scroll, menu2);
                rulesV.setAlignment(Pos.CENTER);
                rBorder.setCenter(rulesV);
                rBorder.setStyle(("-fx-background-color: green;"));
                rBorder.setMargin(rulesV, new Insets(12, 342, 12, 342));

                Scene ruleScene = new Scene(rBorder,1200,700);
                primaryStage.setScene(ruleScene);
            }
        });
    // menu button 2 allows user to escape ruleScene and return to startScreen
        menu2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                primaryStage.setScene(startScreen);
                t1.clear();
            }
        });
        primaryStage.setScene(startScreen);
        primaryStage.show();
    }
}
