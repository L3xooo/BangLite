package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.KeyboardInput;

public abstract class Card {
    private final String name;
    private final String cardType;

    public Card(String name,String cardType) {
        this.name = name;
        this.cardType = cardType;
    }

    public String getName() {
        return this.name;
    }
    public String getCardType() { return this.cardType; }

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
    public void playCard(Player playerOnTurn) {
        System.out.println("Player: " + playerOnTurn.getName() + " played card " + this.getName());
    }
    public void cardAbility(Player playerOnTurn){}
    public boolean blueCardAbility(Player playerOnTurn){
        return true;
    }
}
