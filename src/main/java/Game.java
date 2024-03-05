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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.converter.IntegerStringConverter;


public class Game extends Application {
    private Button b1, b2, b3, b4,b6;
    private TextField t1, t2;
    private VBox v1, v2;

    private BlackjackGame game;

    public static void main(String[] args) {launch(args);}

    @Override
    public void start(Stage primaryStage) throws Exception {
        BlackjackGame game = new BlackjackGame();

        primaryStage.setTitle("Blackjack");
        BorderPane border = new BorderPane();
        border.setPadding(new Insets(12));


        //start of scene 1 code
        b1 = new Button("Start Game");
        b1.setPrefWidth(150);

        t1 = new TextField();
        t1.setPrefWidth(150);
        t1.setPromptText("enter starting of money");
        t1.setAlignment(Pos.CENTER);
        border.setMargin(t1, new Insets(12,12,12,12));


        Text title = new Text();
        title.setText("Blackjack");
        title.setFont(Font.font("Arial", 48));
        BorderPane.setAlignment(title, Pos.CENTER);

        v1 = new VBox(20, b1, t1);
        border.setBottom(v1);
        border.setCenter(title);
        v1.setAlignment(Pos.CENTER);
        border.setMargin(v1, new Insets(12,12,250,12));

        Scene scene = new Scene(border,700,700);
        //end of scene 1 code

        //start of scene2 code
        BorderPane border2 = new BorderPane();
        border2.setPadding(new Insets(12));

        b2 = new Button("Hit");
        b3 = new Button("Stand");
        b4 = new Button("Raise Bet");
        b2.setPrefWidth(150);
        b3.setPrefWidth(150);
        b4.setPrefWidth(150);

        t2 = new TextField();
        t2.setPromptText("enter raise amount");
        t2.setPrefWidth(150);
        t2.setAlignment(Pos.CENTER);

        Text title2 = new Text();
        title2.setText("Banker Hand");
        title2.setFont(Font.font("Arial", 24));
        BorderPane.setAlignment(title2, Pos.CENTER);
        //testPublish image
        Image cardImage = new Image("1Clubs.png");
        ImageView imageView = new ImageView(cardImage);
        v2.getChildren().add(imageView);
        // endPublish image
        v2 = new VBox(20, b2, b3, b4, t2);
        border2.setLeft(v2);
        border2.setCenter(title2);
        v2.setAlignment(Pos.BOTTOM_CENTER);
        border2.setMargin(v2, new Insets(12,12,12,12));
        //end of scene 2 code
        Scene scene2 = new Scene(border2,700,700);
        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    String inputText = t1.getText();
                    game.beginGame(Integer.parseInt(inputText));
                    game.getUserCards().get(0).printCard();
                    game.getUserCards().get(1).printCard();
                    primaryStage.setScene(scene2);


                } catch (NumberFormatException e) {
                    t1.clear();
                    t1.setPromptText("Error: enter a number");
                }
            }
        });
        //hit button event
        b2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                game.playerHit();
                b4.setDisable(true);
                b4.setText("");
                t2.clear();
                t2.setText("No more bets");
                t2.setEditable(false);
            }
        });
        //raise button and text box event
        b4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    String inputText = t2.getText();
                    game.setBet(Integer.parseInt(inputText) + game.getBet());
                    b4.setDisable(true);
                    t2.clear();
                    t2.setText("Bet now $" + game.getBet());
                    t2.setEditable(false);

                } catch (NumberFormatException e) {
                    t2.clear();
                    t2.setPromptText("Error: enter a number");
                }
            }
        });



        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
