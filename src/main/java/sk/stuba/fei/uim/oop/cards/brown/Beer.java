package sk.stuba.fei.uim.oop.cards.brown;

import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.cards.Card;

public class Beer extends Card {
    private static final String CARD_NAME = "Beer";
    private static final String CARD_TYPE = "Brown";
    private static final int HEAL_AMOUNT = 1;
    public Beer() {
        super(CARD_NAME,CARD_TYPE);
    }
    public int getHealAmount() { return HEAL_AMOUNT; }
    @Override
    public boolean canPlay(Player playerOnTurn) {
        return true;
    }
    @Override
    public void playCard(Player playerOnTurn) {
        super.playCard(playerOnTurn);
        this.cardAbility(playerOnTurn);
    }
    public void cardAbility(Player playerOnTurn) {
        playerOnTurn.addHealth(this.getHealAmount());
        System.out.println("Player: " + playerOnTurn.getName() +
                " added health to " + playerOnTurn.getHealth());
    }
}
