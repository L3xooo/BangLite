package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;

public class Beer extends BrownCard {
    private static final String CARD_NAME = "Beer";
    private static final int HEAL_AMOUNT = 1;
    public Beer() {
        super(CARD_NAME);
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
