package sk.stuba.fei.uim.oop.cards.brown;

import sk.stuba.fei.uim.oop.Player;
import sk.stuba.fei.uim.oop.cards.Card;

import java.util.List;

public class Missed extends Card {
    //Constructors Start
    public Missed(String name,String type){
        super(name,type);
    }
    //Constructors End

    //Methods Start
    @Override
    public void cardAbility(Player playerOnTurn, List<Card> cardDeck,List<Player> players) {
        System.out.println("You cannot play Missed card, choose another card or end turn!");
    }
    @Override
    public void blueCardAbility(Player playerOnTurn, List<Card> cardDeck, List<Player> players) {

    }
    //Methods End
}
