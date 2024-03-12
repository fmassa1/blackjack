import javafx.application.Application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
//Card Image
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
import javafx.util.converter.IntegerStringConverter;

import java.util.ArrayList;
import java.util.Objects;


public class Game extends Application {
    private Button startB, hitB, standB, raiseB, menuB, betB, nextBet;
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

        v1 = new VBox(20, title, titleStatus, startB, t1);
        border.setCenter(v1);
        v1.setAlignment(Pos.CENTER);
        border.setMargin(v1, new Insets(12,12,12,12));

        Scene startScreen = new Scene(border,700,700);
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

        betV = new VBox(20, balance, betB, t3);
        betV.setAlignment(Pos.CENTER);
        betBorder.setBottom(betV);
        betBorder.setCenter(betTitle);
        betBorder.setMargin(betV, new Insets(12,12,250,12));

        Scene betScreen = new Scene(betBorder,700,700);
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
        border2.setTop(menuB);
        border2.setCenter(bankerLabel);
        menuB.setAlignment((Pos.CENTER));

        //set background border (rough border)
        betBorder.setStyle(("-fx-background-color: green;"));
        border.setStyle(("-fx-background-color: green;"));
        border2.setStyle(("-fx-background-color: green;"));
        Scene firstScene = new Scene(border2,700,700);

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
                        money.setText("Current Balance: $" + game.getUserMoney());
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
                        standB.setDisable(false);
                        hitB.setDisable(false);
                        raiseB.setDisable(false);
                        t2.setEditable(true);
                        standB.setText("Stand");
                        hitB.setText("Hit");
                        raiseB.setText("Raise Bet");

                        curBet = new Text();
                        curBet.setText("Current Bet: $" + game.getBet());
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
                        //end card2
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
                    game.setUserMoney(game.getUserMoney() - game.getBet());
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
                    game.setBet(Double.parseDouble(inputText) + game.getBet());
                    curBet.setText("Current Bet: $" + game.getBet());
                    raiseB.setDisable(true);
                    t2.clear();
                    t2.setText("No more bets");
                    t2.setEditable(false);

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
                game.bankerHit();
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
                if(Objects.equals(winner, "dealer")) {
                    gameStatus.setText("Banker has won");
                    winnings.setText("Lost $" + game.getBet());
                    game.setUserMoney(game.getUserMoney() - game.getBet());
                }
                else if(Objects.equals(winner, "player")) {
                    gameStatus.setText("User has won!");
                    winnings.setText("Gained $" + game.getBet());
                    game.setUserMoney(game.getUserMoney() + game.getBet());
                }
                else {
                    gameStatus.setText("User and Banker Tied");
                    winnings.setText("No change in balance");

                }
                endHand.setVisible(true);
            }
        });

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
        menuB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(startScreen);
                t1.clear();
                t3.clear();
            }
        });

        primaryStage.setScene(startScreen);
        primaryStage.show();
    }
}
