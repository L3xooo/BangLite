package sk.stuba.fei.uim.oop.cards.blue;
import sk.stuba.fei.uim.oop.Player;
import sk.stuba.fei.uim.oop.cards.Card;

import java.util.List;

public class Barrel extends Card{

    public Barrel(String name, String type) {
        super(name,type);
    }

    @Override
    public void cardAbility(Player playerOnTurn, List<Card> cardsInStack) {
        System.out.println("Barrel!");
    }

    @Override
    public boolean blueCardAbility(Player playerOnTurn, List<Card> cardsInStack) {
        return this.cardProbabilityOfSuccess(0.25);
    }
}
