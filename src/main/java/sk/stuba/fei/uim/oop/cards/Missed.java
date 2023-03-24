package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;

public class Missed extends BrownCard {
    private static final String CARD_NAME = "Missed";
    public Missed(){
        super(CARD_NAME);
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
