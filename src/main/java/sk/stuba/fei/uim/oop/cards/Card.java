package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.KeyboardInput;
import java.util.List;
import java.util.Random;

public abstract class Card {
    private String name;
    private String cardType;
    private Random rand;

    //Constructor Start
    public Card(String name,String cardType) {
        this.name = name;
        this.cardType = cardType;
        this.rand = new Random();
    }
    //Constructor End

    //Getters Start
    public String getName() {
        return this.name;
    }
    public String getCardType() { return this.cardType; }
    public Random getRand() { return this.rand; }
    //Getters End

    //Methods Start
    public abstract boolean canPlay(Player playerOnTurn);
    public int choosingPlayer (Player player) {
        int playerIndex;
        while(true) {
            playerIndex = KeyboardInput.readInt("Enter player index");
            if (playerIndex < 0 || playerIndex >= player.getEnemyPlayers().size()) {
                System.out.println("Index is out of range. Please enter correct index!");
            } else {
                break;
            }
        }
        return playerIndex;
    }
    public void playCard(Player playerOnTurn, List<Card> cardDeck) {
        System.out.println("Player: " + playerOnTurn.getName() + " played card " + this.getName());
    }
    public void cardAbility(Player playerOnTurn){}
    public boolean blueCardAbility(Player playerOnTurn){
        return true;
    }
    //Methods End
}
