package sk.stuba.fei.uim.oop.cards.brown;

import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.cards.Card;

public class Missed extends Card {
    private static final String CARD_NAME = "Missed";
    private static final String CARD_TYPE = "Brown";
    public Missed(){
        super(CARD_NAME,CARD_TYPE);
    }


    @Override
    public boolean canPlay(Player playerOnTurn) {
        return false;
    }

    @Override
    public void playCard(Player playerOnTurn) {
        cardAbility(playerOnTurn);
        playerOnTurn.getDiscardCardDeck().add(this);
        playerOnTurn.getPlayerCards().remove(this);
    }
    @Override
    public void cardAbility(Player playerOnTurn) {
        System.out.println("Player: " + playerOnTurn.getName() + " used Missed and dodged your Bang!");
    }
}
