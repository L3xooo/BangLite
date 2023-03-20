package sk.stuba.fei.uim.oop.cards.brown;

import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.cards.Card;

import java.util.List;

public class Missed extends Card {
    private static final String CARD_NAME = "Missed";
    private static final String CARD_TYPE = "Brown";
    //Constructors Start
    public Missed(){
        super(CARD_NAME,CARD_TYPE);
    }
    //Constructors End


    @Override
    public boolean canPlay(Player playerOnTurn) {
        return false;
    }

    //Methods Start
    public void playCard(Player playerOnTurn, List<Card> cardDeck) {
        cardAbility(playerOnTurn);
        playerOnTurn.getPlayerCards().remove(this);
        cardDeck.add(this);
    }
    @Override
    public void cardAbility(Player playerOnTurn) {
        System.out.println("Player: " + playerOnTurn.getName() + " used Missed and dodged your Bang!");
    }
    //Methods End
}
