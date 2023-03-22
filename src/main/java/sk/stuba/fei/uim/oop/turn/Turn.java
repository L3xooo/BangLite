package sk.stuba.fei.uim.oop.turn;
import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.KeyboardInput;

import java.util.List;

public class Turn {
    private Player playerOnTurn;
    public Turn(){}
    public Player getPlayerOnTurn() { return this.playerOnTurn; }
    public void setPlayerOnTurn(Player player) { this.playerOnTurn = player; }

    public void playerTurn(List<Card> cardDeck){
        System.out.println("\nPlayer: " + this.getPlayerOnTurn() + " is on turn. ");
        if (this.getPlayerOnTurn().getBlueCards().size() != 0) {
            if(this.getPlayerOnTurn().checkBlueCards()) {
                return;
            }
        }

        System.out.printf("Player: %s is drawing 2 cards!\n",this.getPlayerOnTurn().getName());
        this.getPlayerOnTurn().drawCard(2,cardDeck);
        this.getPlayerOnTurn().printPlayerStats();
        while (true) {
            if (getPlayerOnTurn().getPlayableCardsCount() == 0) {
                System.out.println("You dont have any playable cards! Ending your turn!");
                this.getPlayerOnTurn().checkCardCount();
                break;
            }
            this.getPlayerOnTurn().printCards();
            int input = KeyboardInput.readInt("Enter 0 - for continue turn, 1 - for end turn");
            if (input != 0 && input !=1) {
                System.out.println("You entered wrong argument. Please try again!");
                continue;
            }
            if (input == 1) {
                this.getPlayerOnTurn().checkCardCount();
                break;
            }
            this.getPlayerOnTurn().playCard();

            if (this.getPlayerOnTurn().getEnemyPlayers().size() == 0){
                break;
            }
        }
    }
}
