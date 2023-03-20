package sk.stuba.fei.uim.oop.cards.brown;

import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.cards.Card;

import java.util.List;

public class Beer extends Card {
    private static final String CARD_NAME = "Beer";
    private static final String CARD_TYPE = "Brown";
    private static final int HEAL_AMOUNT = 1;
    //Constructors Start
    public Beer() {
        super(CARD_NAME,CARD_TYPE);
    }
    //Constructors End

    //Getters Start
    public int getHealAmount() { return HEAL_AMOUNT; }
    //Getters End

    //Methods Start

    @Override
    public boolean canPlay(Player playerOnTurn) {
        return true;
    }

    public void playCard(Player playerOnTurn, List<Card> cardDeck) {
        super.playCard(playerOnTurn,cardDeck);
        this.cardAbility(playerOnTurn);
    }
    public void cardAbility(Player playerOnTurn) {
        playerOnTurn.addHealth(this.getHealAmount());
        System.out.println("Player: " + playerOnTurn.getName() +
                " added health to " + playerOnTurn.getHealth());
    }
    //Methods End
}
