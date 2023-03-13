package sk.stuba.fei.uim.oop.cards.blue;

import sk.stuba.fei.uim.oop.Player;
import sk.stuba.fei.uim.oop.cards.Card;

import java.util.List;

public class Dynamite extends Card {
    private static final String CARD_NAME = "Dynamite";
    private static final int DAMAGE = 1;

    //Constructor Start
    public Dynamite() {
        super(CARD_NAME);
    }
    //Constructor End

    //Getters Start
    public int getDamage() { return DAMAGE; }
    //Getters End

    //Methods Start
    @Override
    public void cardAbility(Player playerOnTurn, List<Card> cardDeck,List<Player> players) {
        if (playerOnTurn.checkCard(playerOnTurn.getBlueCards(),Dynamite.class) != -1) {
            System.out.println("Player has already barrel in blue cards!");
        } else {
            playerOnTurn.getBlueCards().add(this);
            playerOnTurn.getPlayerCards().remove(this);
        }
    }
    @Override
    public void blueCardAbility(Player playerOnTurn, List<Card> cardDeck,List<Player> players) {
        boolean result = this.cardProbabilityOfSuccess(0.125);
        if (result) {
            int activeIndex = players.indexOf(playerOnTurn);
            int newIndex = activeIndex-1;
            if(newIndex < 0){
                newIndex = players.size()-1;
            }
            Player newTargetPlayer = players.get(newIndex);
            newTargetPlayer.getBlueCards().add(this);
        } else {
            System.out.println("Dynamite exploded!");
            playerOnTurn.decreaseHealth(this.getDamage());
            cardDeck.add(this);
            playerOnTurn.checkDeath();
        }
        playerOnTurn.getBlueCards().remove(this);
    }
    //Methods End
}
