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

    public int getNextPlayer(List<Player> players) {
        int activeIndex = players.indexOf(this.getPlayerOnTurn());
        int newIndex = activeIndex + 1;
        if (newIndex >= players.size()) {
            newIndex = 0;
        }
        return newIndex;
    }

    public void playerTurn(List<Card> cardDeck,List<Player> players){
        System.out.println();
        this.getPlayerOnTurn().printPlayerStats();
        if(this.getPlayerOnTurn().checkBlueCards(cardDeck,players,this)) {
            return;
        }
        /*if (this.getPlayerOnTurn().getDeath()) {
            int newIndex = getNextPlayer(players);

            Player nextPlayer = players.get(newIndex);
            this.getPlayerOnTurn().playerDied(cardDeck,players); // hrac skapal
            this.setPlayerOnTurn(nextPlayer);
            return;
        }

        this.getPlayerOnTurn().checkBlueCards(cardDeck,players,this);
        if (this.getPlayerOnTurn().getIsInPrison()) {
            int newIndex = getNextPlayer(players);

            this.getPlayerOnTurn().setIsInPrison(false);
            this.setPlayerOnTurn(players.get(newIndex));
            return;
        }*/

        System.out.printf("%s drawing 2 cards!\n",this.getPlayerOnTurn().getName());
        this.getPlayerOnTurn().drawCard(2,cardDeck);
        while (true) {
            int input = KeyboardInput.readInt("Enter 0 - for continue turn, 1 - for end turn");
            if (input != 0 && input !=1) {
                System.out.println("You entered wrong argument. Please try again!");
            }
            if (input == 1) {
                this.getPlayerOnTurn().checkCardCount(cardDeck);
                int newIndex = getNextPlayer(players);
                this.setPlayerOnTurn(players.get(newIndex));
                break;
            }
            this.getPlayerOnTurn().printCards();
            this.getPlayerOnTurn().cardChoose(cardDeck,players);
        }
    }
}
