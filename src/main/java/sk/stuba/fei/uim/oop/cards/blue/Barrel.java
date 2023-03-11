package sk.stuba.fei.uim.oop.cards.blue;
import sk.stuba.fei.uim.oop.Player;
import sk.stuba.fei.uim.oop.cards.Card;

import java.util.List;

public class Barrel extends Card{
    //Constructor Start
    public Barrel(String name, String type) {
        super(name,type);
    }
    //Constructor End

    //Methods Start
    @Override
    public void cardAbility(Player playerOnTurn, List<Card> cardDeck,List<Player> pLayers) {
        if (playerOnTurn.checkCard(playerOnTurn.getBlueCards(),Barrel.class) != -1) {
            System.out.println("Player has already barrel in blue cards!");
        } else {
            System.out.println(playerOnTurn.getName() + " placed barrel!");
            playerOnTurn.getBlueCards().add(this);
            playerOnTurn.getPlayerCards().remove(this);
        }
    }
    @Override
    public void blueCardAbility(Player playerOnTurn, List<Card> cardDeck, List<Player> players) {

    }
    //Methods End
}
