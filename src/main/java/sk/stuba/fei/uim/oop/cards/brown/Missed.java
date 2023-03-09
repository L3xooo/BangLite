package sk.stuba.fei.uim.oop.cards.brown;

import sk.stuba.fei.uim.oop.Player;
import sk.stuba.fei.uim.oop.cards.Card;

import java.util.List;

public class Missed extends Card {

    public Missed(String name,String type){
        super(name,type);
    }

    @Override
    public void cardAbility(Player playerOnTurn, List<Card> cardsInStack) {
        System.out.println("Missed!");

    }
}
