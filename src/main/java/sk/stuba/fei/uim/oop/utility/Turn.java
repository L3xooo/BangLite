package sk.stuba.fei.uim.oop.utility;

import sk.stuba.fei.uim.oop.Player;
import sk.stuba.fei.uim.oop.cards.Card;

import java.util.List;

public class Turn {
    Player playerOnTurn;

    public Turn(Player player) {
        this.playerOnTurn = player;
    }
    public Player getPlayerOnTurn() { return this.playerOnTurn; }
    public void setPlayerOnTurn(Player player) { this.playerOnTurn = player; }

    public void playerTurn(List<Card> cardDeck,List<Player> players){
        this.getPlayerOnTurn().checkDeath();    //toot tu asi nebude treba to spravit pri bang a to indiani
        if (this.getPlayerOnTurn().getDeath()) { //pokial niekoho vyhodnoti ze je dead
            this.getPlayerOnTurn().playerDied(cardDeck,players,this); // hrac skapal
            return;
        }


        System.out.println();
        this.getPlayerOnTurn().printPlayerStats();
        this.getPlayerOnTurn().checkBlueCards(cardDeck,players,"Dynamite");

        if (this.getPlayerOnTurn().getDeath()) {
            this.getPlayerOnTurn().playerDied(cardDeck,players,this); // hrac skapal
            return;
        }


        this.getPlayerOnTurn().checkBlueCards(cardDeck,players,"Prison");







        System.out.printf("%s drawing 2 cards!\n",this.getPlayerOnTurn().getName());
        this.getPlayerOnTurn().drawCard(2,cardDeck);
        while (true) {
            try {
                int input = KeyboardInput.readInt("Enter 0 - for continue turn, 1 - for end turn");
                if (input != 0 && input !=1) {
                    throw new IllegalArgumentException("Input must be 0 or 1");
                }
                if (input == 1) {
                    this.getPlayerOnTurn().checkCardCount(cardDeck);
                    int playerIndex = players.indexOf(this.getPlayerOnTurn());
                    int newPlayerIndex = playerIndex+1;
                    if (newPlayerIndex > players.size()-1) {
                        newPlayerIndex = 0;
                    }
                    this.setPlayerOnTurn(players.get(newPlayerIndex));
                    break;
                }
                this.getPlayerOnTurn().printCards();
                this.getPlayerOnTurn().cardChoose(cardDeck);
                System.out.println("HI");


            } catch (IllegalArgumentException e) {
                System.out.println("You entered wrong argument. Please try again!");
            }
            //playerOnTurn.cardChoose(cardDeck);

        }
    }
}
