package sk.stuba.fei.uim.oop.cards.brown;

import sk.stuba.fei.uim.oop.Player;
import sk.stuba.fei.uim.oop.cards.Card;

import java.util.List;

public class Beer extends Card {
    private static final String CARD_NAME = "Beer";
    private static final int HEAL_AMOUNT = 1;
    //Constructors Start
    public Beer() {
        super(CARD_NAME);
    }
    //Constructors End

    //Getters Start
    public int getHealAmount() { return HEAL_AMOUNT; }
    //Getters End

    //Methods Start
    @Override
    public void cardAbility(Player playerOnTurn, List<Card> cardDeck,List<Player> players) {
        System.out.println(playerOnTurn.getName() + " drink Beer and heal himself!");
        playerOnTurn.addHealth(this.getHealAmount());
        playerOnTurn.getPlayerCards().remove(this);
        cardDeck.add(this);
    }
    @Override
    public void blueCardAbility(Player playerOnTurn, List<Card> cardDeck, List<Player> players) {}
    //Methods End
}
